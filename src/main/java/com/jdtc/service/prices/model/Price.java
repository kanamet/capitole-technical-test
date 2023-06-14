package com.jdtc.service.prices.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Price {
    Integer productId;
    Integer brandId;
    Integer priceList;
    LocalDateTime startDate;
    LocalDateTime endDate;
    BigDecimal price;
    String currency;
}
