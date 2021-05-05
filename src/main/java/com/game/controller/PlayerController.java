package com.game.controller;

import com.game.controller.exceptions.BadRequestException;
import com.game.controller.exceptions.NotFoundException;
import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.service.PlayerDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/rest/players")
public class PlayerController {

    private final PlayerDataService dataService;

    @Autowired
    public PlayerController(PlayerDataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping()
    public ResponseEntity<List<Player>> getPlayersList(
            @RequestParam(required = false, value = "name") String name,
            @RequestParam(required = false, value = "title") String title,
            @RequestParam(required = false, value = "race") Race race,
            @RequestParam(required = false, value = "profession") Profession profession,
            @RequestParam(required = false, value = "after") Long after,
            @RequestParam(required = false, value = "before") Long before,
            @RequestParam(required = false, value = "banned") Boolean banned,
            @RequestParam(required = false, value = "minExperience") Integer minExperience,
            @RequestParam(required = false, value = "maxExperience") Integer maxExperience,
            @RequestParam(required = false, value = "minLevel") Integer minLevel,
            @RequestParam(required = false, value = "maxLevel") Integer maxLevel,
            @RequestParam(required = false, value = "order") PlayerOrder order,
            @RequestParam(required = false, value = "pageNumber") Integer pageNumber,
            @RequestParam(required = false, value = "pageSize") Integer pageSize
    ) {
        if (order == null) order = PlayerOrder.ID;
        if (pageNumber == null) pageNumber = 0;
        if (pageSize == null) pageSize = 3;

        PlayerRequest request = new PlayerRequest(
                name,
                title,
                race,
                profession,
                after,
                before,
                banned,
                minExperience,
                maxExperience,
                minLevel,
                maxLevel
        );

        List<Player> players = dataService.getPlayersList(request, order, pageNumber, pageSize);
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @GetMapping("/count")
    @ResponseBody
    public Integer getPlayersCount(
            @RequestParam(required = false, value = "name") String name,
            @RequestParam(required = false, value = "title") String title,
            @RequestParam(required = false, value = "race") Race race,
            @RequestParam(required = false, value = "profession") Profession profession,
            @RequestParam(required = false, value = "after") Long after,
            @RequestParam(required = false, value = "before") Long before,
            @RequestParam(required = false, value = "banned") Boolean banned,
            @RequestParam(required = false, value = "minExperience") Integer minExperience,
            @RequestParam(required = false, value = "maxExperience") Integer maxExperience,
            @RequestParam(required = false, value = "minLevel") Integer minLevel,
            @RequestParam(required = false, value = "maxLevel") Integer maxLevel
    ) {
        return dataService.getPlayersCount(new PlayerRequest(
                name,
                title,
                race,
                profession,
                after,
                before,
                banned,
                minExperience,
                maxExperience,
                minLevel,
                maxLevel
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayer(@PathVariable("id") String id) {
        long numId = parseId(id);
        Player player = dataService.getById(numId);
        if (player == null) {
            throw new NotFoundException();
        }
        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Player> createPlayer(@RequestBody Player player) {
        player = dataService.createPlayer(player);
        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable String id, @RequestBody Player newPlayer) {
        long numId = parseId(id);
        Player updatedPlayer = dataService.updatePlayer(numId, newPlayer);
        if (updatedPlayer == null) {
            throw new NotFoundException();
        }
        return new ResponseEntity<>(updatedPlayer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable String id) {
        long numId = parseId(id);
        Player deletedPlayer = dataService.deletePlayer(numId);
        if (deletedPlayer == null) {
            throw new NotFoundException();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private long parseId(String id) {
        long numId;
        try {
            numId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new BadRequestException();
        }
        if (numId < 1) throw new BadRequestException();
        return numId;
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Void> handleNotFoundException() {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Void> handleBadRequestException() {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
