package com.example.nuclearfissioncore.commands;

import com.example.nuclearfissioncore.models.Planet;
import com.example.nuclearfissioncore.models.Route;
import com.example.nuclearfissioncore.repositoryies.PlanetRepository;
import com.example.nuclearfissioncore.repositoryies.RouteRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;


@Component
public class TestCommand implements CommandLineRunner {

    private static final String COMMAND_NAME = "kevin:testCommand";
    private final PlanetRepository planetRepository;
    private final RouteRepository routeRepository;
    private static final String FILE_PATH = "data/planetsDistanceToEachOther.xlsx";
    private static final Integer PLANET_SHEET_INDEX = 0;
    private static final Integer ROUTE_SHEET_INDEX = 1;
    private static final Integer TRAFFIC_SHEET_INDEX = 2;

    public TestCommand(PlanetRepository planetRepository, RouteRepository routeRepository) {
        this.planetRepository = planetRepository;
        this.routeRepository = routeRepository;
    }

    private void createPlanets() throws IOException {
        ClassPathResource resource = new ClassPathResource(FILE_PATH);
        try (InputStream input = resource.getInputStream();
             XSSFWorkbook workbook = new XSSFWorkbook(input)) {

            Sheet sheet = workbook.getSheetAt(PLANET_SHEET_INDEX);

            int headerRowNumber = 0;
            int planetNodeColumnIndex = 0;
            int planetNameColumnIndex = 1;

            for (Row row : sheet) {
                int rowNumber = row.getRowNum();

                if (rowNumber == headerRowNumber) {
                    continue;
                }

                Planet planet = new Planet();
                for (Cell cell : row) {
                    int columnIndex = cell.getColumnIndex();
                    if (columnIndex == planetNodeColumnIndex) {
                        planet.setNode(cell.getStringCellValue());
                    } else if(columnIndex == planetNameColumnIndex) {
                        planet.setName(cell.getStringCellValue());
                    }
                }
                planet = planetRepository.save(planet);
            }
        }
    }

    private void createRoutes() throws IOException {
        ClassPathResource resource = new ClassPathResource(FILE_PATH);
        try (InputStream input = resource.getInputStream();
             XSSFWorkbook workbook = new XSSFWorkbook(input)) {

            Sheet sheet = workbook.getSheetAt(ROUTE_SHEET_INDEX);

            int headerRowNumber = 0;
            int routeIdColumnIndex = 0;
            int planetOriginColumnIndex = 1;
            int planetDestinationColumnIndex = 2;
            int distanceColumnIndex = 3;

            for (Row row : sheet) {
                int rowNumber = row.getRowNum();

                if (rowNumber == headerRowNumber) {
                    continue;
                }

                Route route = new Route();
                for (Cell cell : row) {
                    int columnIndex = cell.getColumnIndex();
                    if (columnIndex == planetOriginColumnIndex) {
                         String originPlanetNode = cell.getStringCellValue();
                         Optional<Planet> planetQuery = planetRepository.findByNode(originPlanetNode);

                         Planet planet = null;

                         if(planetQuery.isEmpty()) {
                             System.out.println("Origin Planet Node: " + originPlanetNode);
                             planet = new Planet("Unknown-" + originPlanetNode, originPlanetNode);
                             planet = planetRepository.save(planet);
                         } else {
                             planet = planetQuery.get();
                         }

                         route.setOriginPlanetId(planet.getId());

                    } else if(columnIndex == planetDestinationColumnIndex) {
                         String destinationPlanetNode = cell.getStringCellValue();
                         Optional<Planet> planetQuery = planetRepository.findByNode(destinationPlanetNode);

                        Planet planet = null;

                        if(planetQuery.isEmpty()) {
                            planet = new Planet("Unknown-" + destinationPlanetNode, destinationPlanetNode);
                            planet = planetRepository.save(planet);
                        } else {
                            planet = planetQuery.get();
                        }

                         route.setDestinationPlanetId(planet.getId());

                    } else if(columnIndex == distanceColumnIndex) {
                         Double routeDistance = cell.getNumericCellValue();
                         route.setDistance(routeDistance);
                    } else if(columnIndex == routeIdColumnIndex) {
                        double routeId = cell.getNumericCellValue();
                        route.setId((int) routeId);
                    }
                }
                routeRepository.save(route);
                route = new Route();
            }
        }
    }

    private void addTrafficDelayToRoutes() throws IOException {
        ClassPathResource resource = new ClassPathResource(FILE_PATH);
        try (InputStream input = resource.getInputStream();
             XSSFWorkbook workbook = new XSSFWorkbook(input)) {

            Sheet sheet = workbook.getSheetAt(TRAFFIC_SHEET_INDEX);

            int headerRowNumber = 0;
            int routeIdColumnIndex = 0;
            int trafficDelayColumnIndex = 3;

            for (Row row : sheet) {
                int rowNumber = row.getRowNum();

                if (rowNumber == headerRowNumber) {
                    continue;
                }

                double routeId = row.getCell(routeIdColumnIndex).getNumericCellValue();
                Optional<Route> routeQuery = routeRepository.findById((int)routeId);

                if(routeQuery.isEmpty()) {
                    continue;
                }

                Route route = routeQuery.get();

                double trafficDelay = row.getCell(trafficDelayColumnIndex).getNumericCellValue();
                route.setTrafficDelay(trafficDelay);

                routeRepository.save(route);
            }
        }
    }

    @Override
    public void run(String... args) throws Exception {
        this.createPlanets();
        this.createRoutes();
        this.addTrafficDelayToRoutes();
//        Route route = new Route();

//        route.setOriginPlanetId(1);
//        route.setDestinationPlanetId(2);
//        route.setDistance(5.0);
//        route.setId(1);
//
//        routeRepository.save(route);
//        this.createPlanets();
//        this.createRoutes();

//        Planet planet = new Planet();
//        planet.setNode("A");
//        planet.setName("Earth");
//        Planet newPlanet = planetRepository.save(planet);
//
//        System.out.println(newPlanet.getNode());
//        System.out.println(newPlanet.getName());

//        System.out.println(newPlanet);


//        Optional<Planet> planetQuery = planetRepository.findByNode("A");
//
//        if(planetQuery.isPresent()) {
//            Planet planet = planetQuery.get();
//            System.out.println("Planet id: " + planet.getId());
//        } else {
//            System.out.println("Planet not found");
//        }
    }
}
