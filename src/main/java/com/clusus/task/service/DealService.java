package com.clusus.task.service;

import com.clusus.task.model.dto.DealRequestDTO;
import com.clusus.task.model.entity.DealDataEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DealService {

    DealDataEntity saveDealData(DealRequestDTO dealRequestDTO);

    List<DealDataEntity> saveAllDealData(List<DealRequestDTO> dealRequestDataList);

}
