package com.jongyun.book.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


// RunWith : 테스트를 진행할 때 Junit 에 내장된 실행자 외에 다른 실행자를 실행 시킵니다.
@RunWith(SpringRunner.class)
@WebMvcTest()
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void returnHello() throws Exception {
        //given
        String hello = "hello";

        //when
        ResultActions perform = mvc.perform(get("/hello"));

        //then
        perform
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }
}
