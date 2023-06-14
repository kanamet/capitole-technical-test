package com.jdtc.service.prices;

import lombok.Data;

import java.util.HashMap;

@Data
public class TestCase {
    private String title;
    private HashMap<String, Object> input;
    private HashMap<String, Object> expected;
}
