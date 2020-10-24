package com.test.mintbank.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.mintbank.controllers.MintController;
import com.test.mintbank.dtos.ApiResponse;
import com.test.mintbank.dtos.MintResponse;
import com.test.mintbank.services.MIntService;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@WebMvcTest(MintController.class)
@AutoConfigureMockMvc
public class MintControllerTest {

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    MintController mintController;

    @MockBean
    MIntService mIntService;


    public void setUp() throws Exception{
        this.mockMvc = standaloneSetup(this.mintController).build();
        MintResponse mintResponse = new MintResponse();
        mintResponse.setScheme("gfgfg");
        mintResponse.setBank("hdhdhdh");
        mintResponse.setBrand("hdhdhd");
        mintResponse.setPrepaid(Boolean.TRUE);
        mintResponse.setType("jdjdj");
        ApiResponse apiResponse = ApiResponse.builder().success(Boolean.TRUE).payload(mintResponse).build();
    }
}
