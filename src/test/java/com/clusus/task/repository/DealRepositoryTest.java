package com.clusus.task.repository;

import com.clusus.task.model.entity.DealDataEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DealRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;
    @Autowired
    private DealRepository dealRepository;

    @Test
    public void testForSaveDealData() {
        // Given
        DealDataEntity dealDataEntity = new DealDataEntity();
        dealDataEntity.setDealUniqueId(555);
        dealDataEntity.setFromCurrencyISOCode("GBP");
        dealDataEntity.setToCurrencyISOCode("USD");
        dealDataEntity.setDealTimestamp(new Timestamp(System.currentTimeMillis()));
        dealDataEntity.setDealAmount(new BigDecimal(100));

        dealDataEntity = testEntityManager.persistAndFlush(dealDataEntity);
        DealDataEntity searchEntity = dealRepository.findById(dealDataEntity.getDealUniqueId()).get();
        assertEquals(searchEntity.getDealUniqueId(), dealDataEntity.getDealUniqueId());

    }

    @Test
    public void testSaveAllDealData() {
        List<DealDataEntity> dealDataEntities = new ArrayList<>();
        DealDataEntity dealDataEntity1 = new DealDataEntity();
        dealDataEntity1.setDealUniqueId(777);
        dealDataEntity1.setFromCurrencyISOCode("GBP");
        dealDataEntity1.setToCurrencyISOCode("USD");
        dealDataEntity1.setDealTimestamp(new Timestamp(System.currentTimeMillis()));
        dealDataEntity1.setDealAmount(new BigDecimal(100));

        DealDataEntity dealDataEntity2 = new DealDataEntity();
        dealDataEntity2.setDealUniqueId(778);
        dealDataEntity2.setFromCurrencyISOCode("GBP");
        dealDataEntity2.setToCurrencyISOCode("USD");
        dealDataEntity2.setDealTimestamp(new Timestamp(System.currentTimeMillis()));
        dealDataEntity2.setDealAmount(new BigDecimal(100));
        dealDataEntities.add(dealDataEntity1);
        dealDataEntities.add(dealDataEntity2);

        dealRepository.saveAll(dealDataEntities);

        List<DealDataEntity> result = dealRepository.findAll();
        assertThat(dealRepository.existsByDealUniqueId(777));
        assertThat(dealRepository.existsByDealUniqueId(778));

    }

}
