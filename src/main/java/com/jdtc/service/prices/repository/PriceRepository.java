package com.jdtc.service.prices.repository;

import com.jdtc.service.prices.model.Price;

import java.time.LocalDateTime;

public interface PriceRepository {
    Price getPriceByDate(Integer productId, Integer brandId, LocalDateTime dateTime);
}
