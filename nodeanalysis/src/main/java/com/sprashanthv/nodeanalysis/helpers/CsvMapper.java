package com.sprashanthv.nodeanalysis.helpers;

import org.springframework.stereotype.Component;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;

import com.sprashanthv.nodeanalysis.model.Node;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

@Component
public class CsvMapper {

    private final CsvHelper csvHelper;

    public CsvMapper(CsvHelper csvHelper) {
        this.csvHelper = csvHelper;
    }

    public void convertNodeObjectsToCSV(String filePath, List<Node> nodes) throws IOException {

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

    public List<Node> convertCSVToNodeObjects(String filePath) throws IOException {
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
