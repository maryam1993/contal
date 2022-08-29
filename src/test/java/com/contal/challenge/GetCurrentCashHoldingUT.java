package com.contal.challenge;

import com.contal.challenge.repository.impl.AtmRepositoryImpl;
import com.contal.challenge.service.impl.AtmServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetCurrentCashHoldingUT {

    @InjectMocks
    AtmServiceImpl atmService;

    @Mock
    AtmRepositoryImpl atmRepository;

    Map<Integer, Integer> cashHoldingMap;

    @BeforeEach
    void setUp() {
        cashHoldingMap = new HashMap<>();
        cashHoldingMap.put(20, 40);
        cashHoldingMap.put(50, 20);
    }

    @Test
    void getCurrentCashHolding_returned() {
        when(atmRepository.getCurrentCashHolding()).thenReturn(cashHoldingMap);

        Map<Integer,Integer> result = atmService.getCurrentCashHolding();

        assertEquals(cashHoldingMap.size(), result.size(), "cashHoldingMap size failed.");
    }

}
