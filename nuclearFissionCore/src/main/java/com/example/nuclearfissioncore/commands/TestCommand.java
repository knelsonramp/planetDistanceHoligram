package com.example.nuclearfissioncore.commands;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Component
public class TestCommand implements CommandLineRunner {

    private static final String COMMAND_NAME = "kevin:testCommand";
    private static final String FILE_PATH =
            "src/main/java/com/example/nuclearfissioncore/data/planetsDistanceToEachOther.xlsx";

    @Override
    public void run(String... args) throws Exception {
        Path path = Paths.get(FILE_PATH);
        try (InputStream input = Files.newInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(input)) {
            Sheet sheet = workbook.getSheetAt(0);
            System.out.println("SheetName: " + sheet.getSheetName());
            for (Row row : sheet) {
                StringBuilder rowText = new StringBuilder();
                for (Cell cell : row) {
                    rowText.append(cell).append("\t");
                }
                System.out.println(rowText);
            }
        }
    }
}
