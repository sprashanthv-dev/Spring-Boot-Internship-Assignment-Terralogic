package com.sprashanthv.nodeanalysis.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.dao.DataIntegrityViolationException;

import com.sprashanthv.nodeanalysis.repository.NodeRepository;

import com.sprashanthv.nodeanalysis.helpers.CsvHelper;
import com.sprashanthv.nodeanalysis.helpers.CsvMapper;

import com.sprashanthv.nodeanalysis.model.Node;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class CsvService {

    private static final Logger logger = Logger.getLogger(CsvService.class.getName());
    private final CsvHelper csvHelper;
    private final CsvMapper csvMapper;
    private final NodeRepository nodeRepository;

    //TODO: Move @Value annotations to a Config class
    @Value("${csv.path}")
    private String csvPath;

    @Value("${csv.file.name}")
    private String csvFileName;

    @Value("${csv.max.records}")
    private int csvMaxRecords;

    public CsvService(CsvHelper csvHelper, CsvMapper csvMapper, NodeRepository nodeRepository) {
        this.csvHelper = csvHelper;
        this.csvMapper = csvMapper;
        this.nodeRepository = nodeRepository;
    }

    public boolean createCSV() {
        List<Node> nodes = this.csvHelper.generateNodeRecords(csvMaxRecords);
        String path = Paths.get(csvPath, csvFileName).toString();

        try {
            this.csvMapper.convertNodeObjectsToCSV(path, nodes);
            logger.log(Level.INFO, "Csv file of " + csvMaxRecords + " records written to " + path + " successfully!");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error occurred while creating csv " + e.getMessage());
            return false;
        }

        return true;
    }

    public boolean importCSVToDatabase() {
        List<Node> nodes = this.readCSV();

        if (!nodes.isEmpty()) {
            try {

                nodeRepository.saveAll(nodes);

                logger.log(Level.INFO, "Successfully inserted " + nodes.size() + " csv records into the database!");
            } catch (DataIntegrityViolationException e) {
                logger.log(Level.SEVERE, "Error while importing csv to database " + e.getMessage());
                return false;
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Unknown error occurred while importing csv to database " + e.getMessage());
                return false;
            }
        } else {
            logger.log(Level.SEVERE, "No records present in the CSV to insert");
            return false;
        }

        return true;
    }

    public boolean validateCSVFields() {
        return true;
    }

    private List<Node> readCSV() {
        List<Node> nodes = new ArrayList<>();
        String path = Paths.get(csvPath, csvFileName).toString();

        try {
            nodes = this.csvMapper.convertCSVToNodeObjects(path);
            logger.log(Level.INFO, "Csv file of " + csvMaxRecords + " records converted to " + nodes.size() + " node objects successfully!");
        } catch (IOException e) {
            logger.log(Level.SEVERE,"Error occurred while converting csv to node objects" + e.getMessage());
        }

        return nodes;
    }
}