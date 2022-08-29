package com.contal.challenge.util;

import com.contal.challenge.exception.FileException;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class FileUtil {

    public static Map<Integer, Integer> readFromFile(String fileName) {
        try {
            FileReader filereader = new FileReader(fileName, StandardCharsets.UTF_8);
            CSVParser parser = new CSVParserBuilder().withIgnoreLeadingWhiteSpace(true).build();
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withCSVParser(parser)
                    .build();
            return FileDataHelper.convertToMap(csvReader.readAll());
        } catch (IOException | CsvException e) {
            throw new FileException(e.getMessage(), e.getCause());
        }
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
            throw new FileException(ex.getMessage(), ex.getCause());
        }
    }
}
