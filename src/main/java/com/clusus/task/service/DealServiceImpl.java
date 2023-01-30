package com.clusus.task.service;

import com.clusus.task.model.dto.DealRequestDTO;
import com.clusus.task.model.entity.DealDataEntity;
import com.clusus.task.repository.DealRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DealServiceImpl implements DealService {

    @Autowired
    private DealRepository dealRepository;

    @Override
    @Transactional
    public void saveDealData(DealRequestDTO dealRequestDTO) {
        DealDataEntity dealDataEntity = new DealDataEntity();
        dealDataEntity.setDealUniqueId(dealRequestDTO.getDealId());
        dealDataEntity.setFromCurrencyISOCode(dealRequestDTO.getFromCurrencyIsoCode());
        dealDataEntity.setToCurrencyISOCode(dealRequestDTO.getToCurrencyIsoCode());
        dealDataEntity.setDealTimestamp(dealRequestDTO.getDealTimestamp());
        dealDataEntity.setDealAmount(dealRequestDTO.getDealAmount());
        if(dealRepository.existsByDealUniqueId(dealRequestDTO.getDealId())){
            throw new RuntimeException(" Data with this deal id already exists");
        } else {
            dealRepository.save(dealDataEntity);
        }
    }

    @Override
    @Transactional(rollbackOn = {})
    public void saveAllDealData(List<DealRequestDTO> dealRequestDataList) {
        List<DealDataEntity> dealDataList = new ArrayList<>();
        for(DealRequestDTO dealRequestData: dealRequestDataList){
            DealDataEntity dealDataEntity = new DealDataEntity();
            dealDataEntity.setDealUniqueId(dealRequestData.getDealId());
            dealDataEntity.setFromCurrencyISOCode(dealRequestData.getFromCurrencyIsoCode());
            dealDataEntity.setToCurrencyISOCode(dealRequestData.getToCurrencyIsoCode());
            dealDataEntity.setDealTimestamp(dealRequestData.getDealTimestamp());
            dealDataEntity.setDealAmount(dealRequestData.getDealAmount());
            dealDataList.add(dealDataEntity);
        }
        dealRepository.saveAll(dealDataList);
    }
}