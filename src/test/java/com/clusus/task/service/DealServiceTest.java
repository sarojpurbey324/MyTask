package com.clusus.task.service;

import com.clusus.task.model.entity.DealDataEntity;
import com.clusus.task.repository.DealRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DealServiceTest {

    @Mock
    private DealRepository dealDataRepository;

    @InjectMocks
    private DealServiceImpl dealServiceImpl;


    @Test
    public void testSaveDealData_WithValidData() {
        DealDataEntity dealDataEntity = new DealDataEntity();
        dealDataEntity.setDealUniqueId(123);
        dealDataEntity.setFromCurrencyISOCode("GBP");
        dealDataEntity.setToCurrencyISOCode("USD");
        dealDataEntity.setDealTimestamp(new Timestamp(System.currentTimeMillis()));
        dealDataEntity.setDealAmount(new BigDecimal(100));
        given(dealDataRepository.save(dealDataEntity)).willReturn(dealDataEntity);
        DealDataEntity savedDealDataEntity = dealDataRepository.save(dealDataEntity);
        assertThat(savedDealDataEntity).isNotNull();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSaveDealData_WithInvalidData() {
        DealDataEntity dealDataEntity = new DealDataEntity();
        dealDataEntity.setDealUniqueId(123);
        dealDataEntity.setDealTimestamp(Timestamp.valueOf(String.valueOf(System.currentTimeMillis())));
        dealDataEntity.setDealAmount(new BigDecimal(-100));

        when(dealDataRepository.existsByDealUniqueId(dealDataEntity.getDealUniqueId()))
                .thenReturn(true);

        assertThrows(RuntimeException.class, () -> dealDataRepository.save(dealDataEntity));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testSaveAllDealData_WithInValidData() {
        List<DealDataEntity> dealDataEntities = new ArrayList<>();
        DealDataEntity dealDataEntity = new DealDataEntity();
        dealDataEntity.setDealUniqueId(123);
        dealDataEntity.setDealTimestamp(Timestamp.valueOf(String.valueOf(System.currentTimeMillis())));
        dealDataEntity.setDealAmount(new BigDecimal(100));
        dealDataEntities.add(dealDataEntity);
        verify(dealDataRepository, times(0)).saveAll(dealDataEntities);
    }

    @Test
    public void testSaveAllDealData_WithValidData() {
        List<DealDataEntity> dealDataEntities = new ArrayList<>();
        DealDataEntity dealDataEntity1 = new DealDataEntity();
        dealDataEntity1.setDealUniqueId(666);
        dealDataEntity1.setFromCurrencyISOCode("GBP");
        dealDataEntity1.setToCurrencyISOCode("USD");
        dealDataEntity1.setDealTimestamp(new Timestamp(System.currentTimeMillis()));
        dealDataEntity1.setDealAmount(new BigDecimal(100));

        DealDataEntity dealDataEntity2 = new DealDataEntity();
        dealDataEntity2.setDealUniqueId(555);
        dealDataEntity2.setFromCurrencyISOCode("GBP");
        dealDataEntity2.setToCurrencyISOCode("USD");
        dealDataEntity2.setDealTimestamp(new Timestamp(System.currentTimeMillis()));
        dealDataEntity2.setDealAmount(new BigDecimal(100));
        dealDataEntities.add(dealDataEntity1);
        dealDataEntities.add(dealDataEntity2);

        given(dealDataRepository.saveAll(dealDataEntities)).willReturn(dealDataEntities);
        List<DealDataEntity> savedDealDataEntityList = dealDataRepository.saveAll(dealDataEntities);
        assertThat(savedDealDataEntityList).isNotNull();

    }
}
