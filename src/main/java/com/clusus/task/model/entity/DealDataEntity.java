package com.clusus.task.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "dealdata")
@Data
public class DealDataEntity {

    @Id
    private long dealUniqueId;
    @Column(length = 3)
    @NotNull
    private String fromCurrencyISOCode;
    @Column(length = 3)
    @NotNull
    private String toCurrencyISOCode;
    @NotNull
    private Timestamp dealTimestamp;
    @NotNull
    private BigDecimal dealAmount;

}


