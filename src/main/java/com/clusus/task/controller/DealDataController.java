package com.clusus.task.controller;

import com.clusus.task.model.dto.DealRequestDTO;
import com.clusus.task.service.DealService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("deal-data")
public class DealDataController {


    @Autowired
    private DealService dealService;

    @PostMapping("/save-data")
    public ResponseEntity<?> saveDealData(@Valid @RequestBody DealRequestDTO dealDataDTO) {
        dealService.saveDealData(dealDataDTO);
        return ResponseEntity.ok().body("Deal Data saved successfully.");

    }

    @PostMapping("/save-all-data")
    public ResponseEntity<?> saveDealData(@Valid @RequestBody List<DealRequestDTO> dealDataDTO) {
        dealService.saveAllDealData(dealDataDTO);
        return ResponseEntity.ok().body("Deal Data saved successfully.");
    }


}
