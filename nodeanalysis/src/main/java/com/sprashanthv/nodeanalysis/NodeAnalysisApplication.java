package com.sprashanthv.nodeanalysis;

import com.sprashanthv.nodeanalysis.service.CsvService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NodeAnalysisApplication implements CommandLineRunner {

	private final CsvService csvService;

    public NodeAnalysisApplication(CsvService csvService) {
        this.csvService = csvService;
    }

    public static void main(String[] args) {
		SpringApplication.run(NodeAnalysisApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		this.csvService.createCSV();
//		this.csvService.importCSVToDatabase();
	}
}
