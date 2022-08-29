package com.contal.challenge.repository.impl;

import com.contal.challenge.constant.AtmConfig;
import com.contal.challenge.repository.AtmRepository;
import com.contal.challenge.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class AtmRepositoryImpl implements AtmRepository {

    @Value("${atm.file.name}")
    private String fileName;


    @Override
    public Map<Integer, Integer> getCurrentCashHolding() {
        return FileUtil.readFromFile(fileName);
    }

    @Override
    public List<Integer> getDispenseOptions() {
        return AtmConfig.DISPENSE_OPTIONS;
    }

    @Override
    public List<Integer> getNotes() {
        return AtmConfig.NOTES;
    }

    @Override
    public void removeNotes(Map<Integer, Integer> atmNotes) {
        Map<Integer, Integer> availableMoney = getCurrentCashHolding();
        for (Integer key : atmNotes.keySet()) {
            availableMoney.put(key, availableMoney.get(key) - atmNotes.get(key));
        }
        FileUtil.writeToFile(fileName, availableMoney);
    }
}
