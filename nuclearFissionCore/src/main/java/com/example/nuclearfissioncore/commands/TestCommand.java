package com.example.nuclearfissioncore.commands;

import com.example.nuclearfissioncore.models.Planet;
import com.example.nuclearfissioncore.repositoryies.PlanetRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;


@Component
public class TestCommand implements CommandLineRunner {

    private static final String COMMAND_NAME = "kevin:testCommand";
    private final PlanetRepository planetRepository;
    private static final String FILE_PATH = "data/planetsDistanceToEachOther.xlsx";

    public TestCommand(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    private void createPlanets() throws IOException {
        ClassPathResource resource = new ClassPathResource(FILE_PATH);
        try (InputStream input = resource.getInputStream();
             XSSFWorkbook workbook = new XSSFWorkbook(input)) {

            Sheet sheet = workbook.getSheetAt(0);
            System.out.println("SheetName: " + sheet.getSheetName());

            for (Row row : sheet) {
                int rowNumber = row.getRowNum();

                if (rowNumber == 0) {
                    continue;
                }

                Planet planet = new Planet();
                for (Cell cell : row) {
                    int columnIndex = cell.getColumnIndex();
                    if (columnIndex == 0) {
                        planet.setName(cell.getStringCellValue());
                    } else {
                        planet.setNode(cell.getStringCellValue());
                    }
                }
                planet = planetRepository.save(planet);
                System.out.println(planet.getId());
            }
        }
    }

    @Override
    public void run(String... args) throws Exception {
        this.createPlanets();
    }
}
