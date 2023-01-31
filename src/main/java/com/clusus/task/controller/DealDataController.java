package com.clusus.task.controller;

import com.clusus.task.model.dto.DealRequestDTO;
import com.clusus.task.service.DealService;
import com.clusus.task.service.DealServiceImpl;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DealDataController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DealServiceImpl.class);

    @Autowired
    private DealService dealService;

    @PostMapping("/save-data")
    public ResponseEntity<?> saveDealData(@Valid @RequestBody DealRequestDTO dealDataDTO) {
        LOGGER.info("Receiving request at endpoint /save-data");
        dealService.saveDealData(dealDataDTO);
        return new ResponseEntity<>(dealService.saveDealData(dealDataDTO), HttpStatus.OK);

    }

    @PostMapping("/save-all-data")
    public ResponseEntity<?> saveDealData(@Valid @RequestBody List<DealRequestDTO> dealDataDTO) {
        LOGGER.info("Receiving request at endpoint /save-all-data");
        dealService.saveAllDealData(dealDataDTO);
        return new ResponseEntity<>(dealService.saveAllDealData(dealDataDTO), HttpStatus.OK);
    }


}
