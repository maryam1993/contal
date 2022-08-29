package com.contal.challenge;

import com.contal.challenge.repository.impl.AtmRepositoryImpl;
import com.contal.challenge.service.impl.AtmServiceImpl;
import com.contal.challenge.exception.AtmServiceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DispenseMoneyUt {

    @InjectMocks
    AtmServiceImpl atmService;

    @Mock
    AtmRepositoryImpl atmRepository;

    Map<Integer, Integer> cashHoldingMap;

    @BeforeEach
    void setUp() {
        cashHoldingMap = new HashMap<>();
        cashHoldingMap.put(20, 4);
        cashHoldingMap.put(50, 2);
    }

    @Test
    void dispenseMoney_notAvailableAmount_throwException() {
        when(atmRepository.getCurrentCashHolding()).thenReturn(cashHoldingMap);
        when(atmRepository.getDispenseOptions()).thenCallRealMethod();
        when(atmRepository.getNotes()).thenCallRealMethod();

        AtmServiceException exception = Assertions.assertThrows(AtmServiceException.class,
                () -> atmService.dispenseMoney(150));

        assertEquals("Cash holding amount is not enough for dispensing", exception.getMessage(), "exception message failed");
    }

    @Test
    void dispenseMoney_validAmount_dispensed() {

        when(atmRepository.getCurrentCashHolding()).thenReturn(cashHoldingMap);
        when(atmRepository.getDispenseOptions()).thenCallRealMethod();
        when(atmRepository.getNotes()).thenCallRealMethod();

        atmService.dispenseMoney(100);

        verify(atmRepository, times(1)).removeNotes(anyMap());
    }

    @Test
    void dispenseMoney_invalidAmount_throwException() {
        AtmServiceException exception = Assertions.assertThrows(AtmServiceException.class,
                () -> atmService.dispenseMoney(85));

        assertEquals("This amount is not valid", exception.getMessage(), "exception message failed");
    }

    @Test
    void dispenseMoney_200amountValidCondition_dispensed() {
        cashHoldingMap.put(20, 8);
        cashHoldingMap.put(50, 3);

        when(atmRepository.getCurrentCashHolding()).thenReturn(cashHoldingMap);
        when(atmRepository.getDispenseOptions()).thenCallRealMethod();
        when(atmRepository.getNotes()).thenCallRealMethod();

        atmService.dispenseMoney(200);

        verify(atmRepository).removeNotes(anyMap());
    }

    @Test
    void dispenseMoney_200amountInvalidCondition_throwException() {
        when(atmRepository.getCurrentCashHolding()).thenReturn(cashHoldingMap);
        when(atmRepository.getDispenseOptions()).thenCallRealMethod();
        when(atmRepository.getNotes()).thenCallRealMethod();


        AtmServiceException exception = Assertions.assertThrows(AtmServiceException.class,
                () -> atmService.dispenseMoney(200));

        assertEquals("Cash holding is not suitable for 200", exception.getMessage(), "exception message failed");
    }
}
