package com.jdtc.service.prices.service;

import com.jdtc.service.prices.model.Price;
import com.jdtc.service.prices.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {
    private final PriceRepository priceRepository;

    public Price getPriceByDate(Integer productId, Integer brandId,LocalDateTime dateTime) {
        return priceRepository.getPriceByDate(productId, brandId, dateTime);
    }
}
