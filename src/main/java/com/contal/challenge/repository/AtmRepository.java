package com.contal.challenge.repository;

import java.util.List;
import java.util.Map;

public interface AtmRepository {

    Map<Integer,Integer> getCurrentCashHolding();

    List<Integer> getDispenseOptions();

    List<Integer> getNotes();

    void removeNotes(Map<Integer,Integer> atmNotes);
}
