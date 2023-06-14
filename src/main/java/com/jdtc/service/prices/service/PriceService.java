package com.jdtc.service.prices.service;

import com.jdtc.service.prices.model.Price;

import java.time.LocalDateTime;

public interface PriceService {
    Price getPriceByDate(Integer productId, Integer brandId, LocalDateTime dateTime);
}
