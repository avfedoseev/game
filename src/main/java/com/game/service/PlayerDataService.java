package com.game.service;

import com.game.controller.PlayerOrder;
import com.game.controller.PlayerRequest;
import com.game.controller.exceptions.BadRequestException;
import com.game.entity.Player;
import com.game.repository.PlayerRepository;
import com.game.repository.SpecificationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class PlayerDataService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerDataService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getPlayersList(PlayerRequest request, PlayerOrder order, int pageNumber, int pageSize) {
        if (request.isEmpty()) {
            return  playerRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(order.getFieldName()))).getContent();
        }
        return playerRepository.findAll(
                SpecificationBuilder.getSpecification(request),
                PageRequest.of(pageNumber, pageSize, Sort.by(order.getFieldName()))
        ).getContent();
    }

    public Player getById(Long id) {
        return playerRepository.findById(id).orElse(null);
    }

    public int getPlayersCount(PlayerRequest request) {
        if (request.isEmpty()) {
            return (int) playerRepository.count();
        }
        return  (int) playerRepository.count(SpecificationBuilder.getSpecification(request));
    }

    public Player createPlayer(Player player) {

        if (!isPlayerCorrect(player)) {
            throw new BadRequestException();
        }

        if (player.isBanned() == null) player.setBanned(false);

        player.setLevel(calcLevel(player.getExperience()));
        player.setUntilNextLevel(calcUntilNextLevel(player.getLevel(), player.getExperience()));

        playerRepository.saveAndFlush(player);
        return player;
    }

    public Player updatePlayer(long id, Player newPlayer) {
        if (!playerRepository.existsById(id)) {
            return null;
        }

        Player player = getById(id);

        if (newPlayer.getName() != null) player.setName(newPlayer.getName());
        if (newPlayer.getTitle() != null) player.setTitle(newPlayer.getTitle());
        if (newPlayer.getRace() != null) player.setRace(newPlayer.getRace());
        if (newPlayer.getProfession() != null) player.setProfession(newPlayer.getProfession());
        if (newPlayer.getExperience() != null) player.setExperience(newPlayer.getExperience());
        if (newPlayer.getBirthday() != null) player.setBirthday(newPlayer.getBirthday());
        if (newPlayer.isBanned() != null) player.setBanned(newPlayer.isBanned());

        if (!isPlayerCorrect(player)) {
            throw new BadRequestException();
        }

        player.setLevel(calcLevel(player.getExperience()));
        player.setUntilNextLevel(calcUntilNextLevel(player.getLevel(), player.getExperience()));

        playerRepository.saveAndFlush(player);

        return player;
    }

    public Player deletePlayer(long id) {
        Player player = getById(id);
        if (player != null)
            playerRepository.deleteById(id);
        return player;
    }

    private int calcLevel(int experience) {
        return (int) (Math.sqrt(2500 + 200 * experience) - 50) / 100;
    }

    private int calcUntilNextLevel(int level, int experience) {
        return 50 * (level + 1) * (level + 2) - experience;
    }

    private boolean isPlayerCorrect(Player player) {
        return player.getName() != null &&
                !player.getName().equals("") &&
                player.getTitle() != null &&
                player.getRace() != null &&
                player.getProfession() != null &&
                player.getExperience() != null &&
                player.getBirthday() != null &&
                player.getName().length() <= 12 &&
                player.getTitle().length() <= 30 &&
                player.getExperience() >= 0 &&
                player.getExperience() <= 10000000 &&
                !player.getBirthday().before(new Date(100, Calendar.JANUARY, 1)) &&
                !player.getBirthday().after(new Date(1100, Calendar.DECEMBER, 31));
    }
}
