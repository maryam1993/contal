package com.contal.challenge.util;

import com.contal.challenge.exception.AtmServiceException;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileUtil {

    public static List<String[]> readFromFile(String fileName) {
        List<String[]> allData;
        try {
            FileReader filereader = new FileReader(fileName, StandardCharsets.UTF_8);
            CSVParser parser = new CSVParserBuilder().withIgnoreLeadingWhiteSpace(true).build();
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withCSVParser(parser)
                    .build();
            allData = csvReader.readAll();

        } catch (IOException | CsvException e) {
            throw new AtmServiceException(e.getMessage(), e.getCause());
        }
        return allData;
    }

    public static Map<Integer, Integer> readFromFileInMap(String fileName) {
        List<String[]> allData = readFromFile(fileName);
        Map<Integer, Integer> dataMap = new HashMap<>();
        for (String[] row : allData) {
            dataMap.put(Integer.parseInt(row[0]), Integer.parseInt(row[1]));
        }
        return dataMap;
    }

    public static void writeToFile(String fileName, Map<Integer, Integer> data) {
        try (Writer writer = new FileWriter(fileName)) {
            for (Map.Entry<Integer, Integer> entry : data.entrySet()) {
                writer.append(entry.getKey().toString())
                        .append(',')
                        .append(entry.getValue().toString())
                        .append('\n');
            }
            writer.flush();
        } catch (IOException ex) {
            throw new AtmServiceException(ex.getMessage(), ex.getCause());
        }
    }
}
