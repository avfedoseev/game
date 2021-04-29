package com.game.service;

import com.game.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerDataService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerDataService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }


}
