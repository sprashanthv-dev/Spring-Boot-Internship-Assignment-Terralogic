package com.sprashanthv.nodeanalysis.service;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.opencsv.CSVWriter;

import com.sprashanthv.nodeanalysis.helpers.CsvHelper;
import com.sprashanthv.nodeanalysis.model.Node;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

//TODO: Move @Value annotations to a Config class?
@Service
public class CsvService {

    private final CsvHelper csvHelper;

    @Value("${csv.path}")
    private String csvPath;

    @Value("${csv.file.name}")
    private String csvFileName;

    @Value("${csv.max.records}")
    private int csvMaxRecords;

    public CsvService(CsvHelper csvHelper) {
        this.csvHelper = csvHelper;
    }

    public boolean createCSV() {
        List<Node> nodes = this.csvHelper.generateNodeRecords(csvMaxRecords);
        String path = Paths.get(csvPath, csvFileName).toString();

        try {
            this.convertNodeObjectsToCSV(path, nodes);
            System.out.println("Csv file of " + csvMaxRecords + " records written to " + path + " successfully!");
        } catch (IOException e) {
            System.out.println("Error occurred while creating csv " + e.getMessage());
            return false;
        }

        return true;
    }

    public List<Node> readCSV() {
        List<Node> nodes = new ArrayList<>();
        String path = Paths.get(csvPath, csvFileName).toString();

        try {
            nodes = this.convertCSVToNodeObjects(path);
            System.out.println("Csv file of " + csvMaxRecords + " records converted to " + nodes.size() + " node objects successfully!");
        } catch (IOException e) {
            System.out.println("Error occurred while converting csv to node objects" + e.getMessage());
        }

        return nodes;
    }

    public boolean validateCSVFields() {
        return true;
    }

    private void convertNodeObjectsToCSV(String filePath, List<Node> nodes) throws IOException {

        String[] headers = this.csvHelper.getAllHeaderValues();

        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            // Csv header information
            writer.writeNext(headers);

            // Csv data
            for (Node node : nodes) {
                String[] row = this.csvHelper.getEachRow(node);
                writer.writeNext(row);
            }
        } catch (IOException e) {
            throw new IOException("Error in converting node objects to CSV " + e.getMessage());
        }
    }

    private List<Node> convertCSVToNodeObjects(String filePath) throws IOException {
        try (Reader reader = new FileReader(filePath)) {

            return new CsvToBeanBuilder<Node>(reader)
                    .withType(Node.class)
                    .build()
                    .parse();
        } catch (IOException e) {
            throw new IOException("Error in converting CSV to node objects" + e.getMessage());
        }
    }
}