package com.jdtc.service.prices.repository;

import com.jdtc.service.prices.model.Price;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
public class PriceRepositoryImpl implements PriceRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public Price getPriceByDate(
            Integer productId,
            Integer brandId,
            LocalDateTime dateTime
    ) {
        String sql = "SELECT * FROM prices" +
                " WHERE product_id = :productId AND brand_id = :brandId AND :date BETWEEN start_date AND end_date" +
                " ORDER BY priority DESC" +
                " LIMIT 1";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("productId", productId);
        params.addValue("brandId", brandId);
        params.addValue("date", dateTime);

        return jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> Price.builder()
                .productId(rs.getInt("PRODUCT_ID"))
                .brandId(rs.getInt("BRAND_ID"))
                .startDate(rs.getTimestamp("START_DATE").toLocalDateTime())
                .endDate(rs.getTimestamp("END_DATE").toLocalDateTime())
                .priceList(rs.getInt("PRICE_LIST"))
                .price(rs.getBigDecimal("PRICE"))
                .currency(rs.getString("CURR"))
                .build()
        );
    }

}
