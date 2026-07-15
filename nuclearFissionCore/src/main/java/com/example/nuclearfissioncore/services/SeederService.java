package com.example.nuclearfissioncore.services;

import com.example.nuclearfissioncore.models.Planet;
import com.example.nuclearfissioncore.models.Route;
import com.example.nuclearfissioncore.repositoryies.PlanetRepository;
import com.example.nuclearfissioncore.repositoryies.RouteRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;


@Service
public class SeederService {

    private final PlanetRepository planetRepository;
    private final RouteRepository routeRepository;
    private static final String FILE_PATH = "data/planetsDistanceToEachOther.xlsx";
    private static final Integer PLANET_SHEET_INDEX = 0;
    private static final Integer ROUTE_SHEET_INDEX = 1;
    private static final Integer TRAFFIC_SHEET_INDEX = 2;

    public SeederService(PlanetRepository planetRepository, RouteRepository routeRepository) {
        this.planetRepository = planetRepository;
        this.routeRepository = routeRepository;
    }

    public void createPlanets() throws IOException {
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

                String planetName = row.getCell(planetNameColumnIndex).getStringCellValue();
                String planetNode = row.getCell(planetNodeColumnIndex).getStringCellValue();
                Planet planet = new Planet(planetName, planetNode);
                planetRepository.save(planet);
            }
        }
    }

    public void createRoutes() throws IOException {
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

                int routeId = (int)row.getCell(routeIdColumnIndex).getNumericCellValue();
                double distance = row.getCell(distanceColumnIndex).getNumericCellValue();
                double trafficDelay = 0.0;

                String originPlanetNode = row.getCell(planetOriginColumnIndex).getStringCellValue();
                Optional<Planet> originPlanetQuery = planetRepository.findByNode(originPlanetNode);
                Planet originPlanet;
                if(originPlanetQuery.isEmpty()) {
                    originPlanet = new Planet("Unknown-" + originPlanetNode, originPlanetNode);
                    originPlanet = planetRepository.save(originPlanet);
                } else {
                    originPlanet = originPlanetQuery.get();
                }

                String destinationPlanetNode = row.getCell(planetDestinationColumnIndex).getStringCellValue();
                Optional<Planet> destinationPlanetQuery = planetRepository.findByNode(destinationPlanetNode);
                Planet destinationPlanet;
                if(destinationPlanetQuery.isEmpty()) {
                    destinationPlanet = new Planet("Unknown-" + destinationPlanetNode, destinationPlanetNode);
                    destinationPlanet = planetRepository.save(destinationPlanet);
                } else {
                    destinationPlanet = destinationPlanetQuery.get();
                }

                Route route = new Route(
                        routeId,
                        originPlanet.getId(),
                        destinationPlanet.getId(),
                        distance,
                        trafficDelay
                );

                routeRepository.save(route);
            }
        }
    }

    public void addTrafficDelayToRoutes() throws IOException {
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
}