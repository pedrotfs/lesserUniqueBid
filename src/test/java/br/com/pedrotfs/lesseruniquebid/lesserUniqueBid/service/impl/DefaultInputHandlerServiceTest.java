package br.com.pedrotfs.lesseruniquebid.lesserUniqueBid.service.impl;

import br.com.pedrotfs.lesseruniquebid.lesserUniqueBid.dto.Bid;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DefaultInputHandlerServiceTest {

    private DefaultInputHandlerService defaultInputHandlerService = new DefaultInputHandlerService();

    private static final String JSON = "{\n" +
            "  \"bids\": [\n" +
            "    {\n" +
            "      \"name\":\"Joao\",\n" +
            "      \"bid\": \"0.01\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\":\"Maria\",\n" +
            "      \"bid\": \"0.3\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\":\"Renata\",\n" +
            "      \"bid\": \"0.01\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\":\"Pedro\",\n" +
            "      \"bid\": \"12.34\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    private static final String INVALID_JSON = "{\n" +
            "  \"bids\": [\n" +
            "    {\n" +
            "      \"name\":\"Joao\",\n" +
            "      \"bid\": \"0.01\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\":\"Maria\",\n" +
            "      \"bid\": \"0.3\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\":\"Renata\",\n" +
            "      \"bid\": \"0.01\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\":\"Pedro\",\n" +
            "      \"bid\": \"12.345\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    @BeforeEach
    void setUp() {
        defaultInputHandlerService.setDecimalPlaces(2);
        defaultInputHandlerService.setIdentifier("bids");
    }

    @Test
    void handleInput() throws Exception {
        List<Bid> bids = defaultInputHandlerService.handleInput(JSON);
        Assertions.assertEquals(bids.size(), 4);

        Assertions.assertEquals(bids.get(0).getName(), "Joao");
        Assertions.assertEquals(bids.get(0).getBid(), new BigDecimal("0.01"));
        Assertions.assertEquals(bids.get(1).getName(), "Maria");
        Assertions.assertEquals(bids.get(1).getBid(), new BigDecimal("0.3"));
        Assertions.assertEquals(bids.get(2).getName(), "Renata");
        Assertions.assertEquals(bids.get(2).getBid(), new BigDecimal("0.01"));
        Assertions.assertEquals(bids.get(3).getName(), "Pedro");
        Assertions.assertEquals(bids.get(3).getBid(), new BigDecimal("12.34"));
    }

    @Test
    void handleInputInvalidScale() throws Exception {
        Assertions.assertThrows(Exception.class, () -> {defaultInputHandlerService.handleInput(INVALID_JSON); });
    }
}