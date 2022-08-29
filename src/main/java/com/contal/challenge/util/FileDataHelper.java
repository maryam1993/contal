package com.contal.challenge.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileDataHelper {

    public static Map<Integer, Integer> convertToMap(List<String[]> fileData) {
        Map<Integer, Integer> dataMap = new HashMap<>();
        for (String[] row : fileData) {
            dataMap.put(Integer.parseInt(row[0]), Integer.parseInt(row[1]));
        }
        return dataMap;
    }
}
