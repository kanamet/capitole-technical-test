package com.jdtc.service.prices.controller;

import com.jdtc.service.prices.model.Price;
import com.jdtc.service.prices.service.PriceService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class PriceController {

    private final PriceService priceService;

    @GetMapping("/api/price")
    public Price getPriceByDate(
            @PathParam("productId") Integer productId,
            @PathParam("brandId") Integer brandId,
            @PathParam("date") LocalDateTime date
    ) {
       return priceService.getPriceByDate(productId, brandId, date);
    }

}
