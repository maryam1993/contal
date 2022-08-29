package com.contal.challenge.service;


import java.util.List;
import java.util.Map;

public interface AtmService {

    List<Integer> getAtmDispenseMoneyOptions();

    void dispenseMoney(Integer amount);

    Map<Integer, Integer> getCurrentCashHolding();

    List<Integer> getNotes();
}
