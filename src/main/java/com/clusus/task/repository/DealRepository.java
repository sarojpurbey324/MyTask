package com.clusus.task.repository;

import com.clusus.task.model.entity.DealDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealRepository extends JpaRepository<DealDataEntity, Long> {
    boolean existsByDealUniqueId(long dealUniqueId);

}
