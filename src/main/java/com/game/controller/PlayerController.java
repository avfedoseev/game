package com.game.controller;

import com.game.entity.Player;
import com.game.service.PlayerDataService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @ResponseBody
    public List<Player> getPlayersList() {
        return dataService.getAll();
    }

    @GetMapping("/count")
    @ResponseBody
    public Integer getPlayersCount() {
        return getPlayersList().size();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Player getPlayer(@PathVariable("id") long id) {
        return dataService.getById(id);
    }
}
