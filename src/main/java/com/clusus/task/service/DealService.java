package com.clusus.task.service;

import com.clusus.task.model.dto.DealRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DealService {

    void saveDealData(DealRequestDTO dealRequestDTO);

    void saveAllDealData(List<DealRequestDTO> dealRequestDataList);

}
