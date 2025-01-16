package com.nathuncorp.spring6restmvc.controller;

import com.nathuncorp.spring6restmvc.service.BeerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest
@WebMvcTest(BeerController.class)
// NOTE : With above @WebMvcTest, it indicates that it is a TEST SPLICE.
// We are also limiting that only BeerController should be brought up.
class BeerControllerTest {
    // @Autowired
    // BeerController beerController;
    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    BeerService beerService;

    @Test
    void getBeerById() throws Exception {
        mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        // System.out.println(beerController.getBeerById(UUID.randomUUID()));
    }
}