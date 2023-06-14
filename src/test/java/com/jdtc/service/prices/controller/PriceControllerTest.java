package com.jdtc.service.prices.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jdtc.service.prices.TestCase;
import com.jdtc.service.prices.model.Price;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerTest {

    @Autowired
    MockMvc mockMvc;

    static Stream<Arguments> testCases() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        InputStream inputData = ClassLoader.getSystemResourceAsStream("testGetPriceRequest.json");
        List<TestCase> testCases = mapper.readValue(inputData, new TypeReference<>() {});

        return testCases.stream()
                .map(testCase ->
                        arguments(
                                testCase.getTitle(),
                                testCase.getInput().get("productId"),
                                testCase.getInput().get("brandId"),
                                testCase.getInput().get("date"),
                                mapper.convertValue(testCase.getExpected(), Price.class)
                        )
                );
    }


    @ParameterizedTest(name = "{0} - {3}")
    @MethodSource("testCases")
    void testGetPriceRequest(String title, int productId, int brandId, LocalDateTime date, Price expected) throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/price")
                .param("productId", String.valueOf(productId))
                .param("brandId", String.valueOf(brandId))
                .param("date", date.toString());

        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId", is(expected.getProductId())))
                .andExpect(jsonPath("$.brandId", is(expected.getBrandId())))
                .andExpect(jsonPath("$.priceList", is(expected.getPriceList())))
                .andExpect(jsonPath("$.price").value(expected.getPrice()))
                .andExpect(jsonPath("$.currency").value(expected.getCurrency()))
                .andExpect(jsonPath("$.startDate").value(formatter.format(expected.getStartDate())))
                .andExpect(jsonPath("$.endDate").value(formatter.format(expected.getEndDate())))
        ;
    }
}