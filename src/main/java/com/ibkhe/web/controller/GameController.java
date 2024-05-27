package com.ibkhe.web.controller;

import com.ibkhe.BaseBallGame;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {
    @GetMapping("/baseball")
    public String baseball() {
        BaseBallGame game = new BaseBallGame();

        return game.getScore(new int[]{1,2,3}, new int[]{3,2,1});
    }
}
