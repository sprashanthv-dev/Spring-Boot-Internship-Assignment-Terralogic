package com.sprashanthv.nodeanalysis.service;

import com.sprashanthv.nodeanalysis.helpers.CsvHelper;
import org.springframework.stereotype.Service;

@Service
public class CsvService {

    private final CsvHelper csvHelper;

    public CsvService(CsvHelper csvHelper) {
        this.csvHelper = csvHelper;
    }

    public boolean createCSV() {
        // TODO: Move to application.properties
        csvHelper.generateCSV(10);

        return true;
    }

    public boolean validateCSVFields() {
        return true;
    }
}
