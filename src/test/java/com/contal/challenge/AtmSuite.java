package com.contal.challenge;


import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        GetCurrentCashHoldingUT.class,
        DispenseMoneyUt.class
})
public class AtmSuite {
}
