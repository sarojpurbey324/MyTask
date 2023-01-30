package com.clusus.task.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DealRequestDTO {

    @Positive
    private long dealId;

    @NotBlank(message = "from currency ISO code is required and is of 3 characters")
    @Size(min=3, max = 3)
    private String fromCurrencyIsoCode;

    @NotBlank(message = "to currency ISO code is required and is of 3 characters")
    @Size(min=3, max = 3)
    private String toCurrencyIsoCode;

    private Timestamp dealTimestamp;

    @Positive
    private BigDecimal dealAmount;

}
