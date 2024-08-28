package com.sprashanthv.nodeanalysis;

import com.sprashanthv.nodeanalysis.service.CsvService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
public class NodeAnalysisApplication implements CommandLineRunner {

    private final CsvService csvService;

    private static final Logger logger = Logger.getLogger(NodeAnalysisApplication.class.getName());

    public NodeAnalysisApplication(CsvService csvService) {
        this.csvService = csvService;
    }

    public static void main(String[] args) {
        SpringApplication.run(NodeAnalysisApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        String csvDirPath = this.csvService.getCsvPath();
        String csvFileName = this.csvService.getCsvFileName();

        try {
            Path path = Paths.get(csvDirPath, csvFileName);

            // Checks if csv file exists at the path, if not generates it with random seeded values
            if (!Files.exists(path)) {
                logger.log(Level.INFO, "CSV file does not exist locally. Creating it at --> " + path);
                boolean csvResult = this.csvService.createCSV();

                if (csvResult) {
                    logger.log(Level.INFO, "Csv generated successfully");
                } else {
                    logger.log(Level.SEVERE, "Error in creating the csv file");
                }
            } else {
                logger.log(Level.INFO, "CSV file already exists");
            }

            // Imports the csv into the H2 database
			boolean importResult = this.csvService.importCSVToDatabase();

			if (!importResult) {
				logger.log(Level.SEVERE, "CSV import failed");
				throw new RuntimeException("Failed to import CSV to database");
			} else {
				logger.log(Level.INFO, "Table records exists / Table seeded successfully!");
			}

        } catch (Exception e) {
			logger.log(Level.SEVERE, "CSV File Path not found");
			throw new RuntimeException("CSV File Path not found!");
		}
    }
}
