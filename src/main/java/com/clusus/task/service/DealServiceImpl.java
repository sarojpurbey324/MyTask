package com.clusus.task.service;

import com.clusus.task.model.dto.DealRequestDTO;
import com.clusus.task.model.entity.DealDataEntity;
import com.clusus.task.repository.DealRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DealServiceImpl implements DealService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DealServiceImpl.class);

    @Autowired
    private DealRepository dealRepository;

    @Override
    @Transactional
    public DealDataEntity saveDealData(DealRequestDTO dealRequestDTO) {
        LOGGER.info("Saving single deal data");
        DealDataEntity dealDataEntity = new DealDataEntity();
        dealDataEntity.setDealUniqueId(dealRequestDTO.getDealId());
        dealDataEntity.setFromCurrencyISOCode(dealRequestDTO.getFromCurrencyIsoCode());
        dealDataEntity.setToCurrencyISOCode(dealRequestDTO.getToCurrencyIsoCode());
        dealDataEntity.setDealTimestamp(dealRequestDTO.getDealTimestamp());
        dealDataEntity.setDealAmount(dealRequestDTO.getDealAmount());
        if (dealRepository.existsByDealUniqueId(dealRequestDTO.getDealId())) {
            throw new RuntimeException(" Data with this deal id "+ dealRequestDTO.getDealId() +" already exists");
        } else {
            return dealRepository.save(dealDataEntity);
        }
    }

    @Override
    @Transactional(rollbackOn = {})
    public List<DealDataEntity> saveAllDealData(List<DealRequestDTO> dealRequestDataList) {
        LOGGER.info("Saving list of deal data");
        List< DealRequestDTO > filteringDuplicateData = getNonDuplicateData( dealRequestDataList );
        List<DealDataEntity> dealDataList = new ArrayList<>();
        for (DealRequestDTO dealRequestData : filteringDuplicateData) {
            DealDataEntity dealDataEntity = new DealDataEntity();
            dealDataEntity.setDealUniqueId(dealRequestData.getDealId());
            dealDataEntity.setFromCurrencyISOCode(dealRequestData.getFromCurrencyIsoCode());
            dealDataEntity.setToCurrencyISOCode(dealRequestData.getToCurrencyIsoCode());
            dealDataEntity.setDealTimestamp(dealRequestData.getDealTimestamp());
            dealDataEntity.setDealAmount(dealRequestData.getDealAmount());
            dealDataList.add(dealDataEntity);
        }

        return dealRepository.saveAll(dealDataList);
    }

    public List< DealRequestDTO > getNonDuplicateData( List< DealRequestDTO > membersFromFile ) {
        Set<DealRequestDTO> members = new HashSet<>(membersFromFile);
        return new ArrayList<>(members);
    }
}
