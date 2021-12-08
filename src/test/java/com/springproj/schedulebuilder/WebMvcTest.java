package com.springproj.schedulebuilder;


import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class WebMvcTest {
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void test() throws Exception {
        mockMvc.perform(get("/public/sign-up"))
            .andExpect(view().name("sign-up"))
            .andExpect(model().attributeExists("user"));
    }
}
