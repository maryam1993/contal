package com.contal.challenge.repository.impl;

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
        return FileUtil.readFromFileInMap(fileName);
    }

    @Override
    public List<Integer> getDispenseOptions() {
        return List.of(20, 40, 50, 60, 70, 80, 100, 110, 150, 200);
    }

    @Override
    public List<Integer> getNotes() {
        return List.of(20, 50);
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
