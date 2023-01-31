package com.clusus.task.controller;

import com.clusus.task.model.dto.DealRequestDTO;
import com.clusus.task.service.DealService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DealDataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DealService dealService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testSaveDealData_ValidInput() throws Exception {
        DealRequestDTO dealDataDTO = new DealRequestDTO(1, "EUR", "USD", new Timestamp(System.currentTimeMillis()),
                new BigDecimal(100));

        this.mockMvc.perform(MockMvcRequestBuilders.post("/save-data")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content( objectMapper.writeValueAsString(dealDataDTO))
                        .header("Accept", MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        verify(dealService, times(1)).saveDealData(dealDataDTO);
    }

    @Test
    public void testSaveDealData_InvalidInput() throws Exception {
        DealRequestDTO dealDataDTO = new DealRequestDTO(1L, "ABC", "USD",  new Timestamp(System.currentTimeMillis()), new BigDecimal(-100));
        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = objectMapper.writeValueAsString(dealDataDTO);

        mockMvc.perform(post("/save-data")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isBadRequest());

        verify(dealService, times(0)).saveDealData(dealDataDTO);
    }

    @Test
    public void testSaveAllDealData() throws Exception {
        List<DealRequestDTO> dealRequestDTOS = new ArrayList<>();
        dealRequestDTOS.add(new DealRequestDTO(22, "USD", "EUR", new Timestamp(System.currentTimeMillis()), new BigDecimal(1000)));
        dealRequestDTOS.add(new DealRequestDTO(233, "EUR", "USD", new Timestamp(System.currentTimeMillis()), new BigDecimal(2000)));

        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = objectMapper.writeValueAsString(dealRequestDTOS);

        mockMvc.perform(post("/save-all-data")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk());

        verify(dealService, times(1)).saveAllDealData(dealRequestDTOS);

    }
}

