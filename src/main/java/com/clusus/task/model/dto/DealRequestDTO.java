package com.clusus.task.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DealRequestDTO that)) return false;
        return getDealId() == that.getDealId() && getFromCurrencyIsoCode().equals(that.getFromCurrencyIsoCode()) && getToCurrencyIsoCode().equals(that.getToCurrencyIsoCode()) && getDealTimestamp().equals(that.getDealTimestamp()) && getDealAmount().equals(that.getDealAmount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDealId(), getFromCurrencyIsoCode(), getToCurrencyIsoCode(), getDealTimestamp(), getDealAmount());
    }
}
