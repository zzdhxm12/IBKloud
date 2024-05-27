package com.ibkhe;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request. MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest
public class GameControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void baseball() throws Exception {
        int[][] game_given = {{1,2,3},{1,2,3},{1,2,3},{1,2,3}};
        int[][] game_input = {{3,2,1},{1,2,5},{1,2,3},{4,5,6}};
        String[] game_is = {"1S2B","2S","3S","null"};

        BaseBallGame game = new BaseBallGame();

        mvc.perform(get("/baseball"))
                .andExpect(status().isOk())
                .andExpect(content().string("1S2B"));
    }
}
