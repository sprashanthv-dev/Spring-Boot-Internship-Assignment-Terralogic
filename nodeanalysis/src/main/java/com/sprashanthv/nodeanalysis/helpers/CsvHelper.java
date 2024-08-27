package com.sprashanthv.nodeanalysis.helpers;

import com.sprashanthv.nodeanalysis.model.Node;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CsvHelper {

    public boolean generateCSV(int noOfRecords) {

        List<Node> nodes = new ArrayList<>();

        for (int i = 0; i < noOfRecords; i++) {
            Node node = this.setupNode();
            nodes.add(node);
        }

        for (Node node : nodes) {
            System.out.println(node);
        }

        return true;
    }

    public boolean validateFields() {
        return true;
    }

    private Node setupNode() {
        Node node = new Node();

        node.setId("T300_001");
        node.setName("Node 1");
        node.setDescription("Sample Text");
        node.setMemo("Sample Text");
        node.setType("Transponder");
        node.setParentGroupName("Subgroup NE_1");
        node.setParentGroupId("Sample Text");
        node.setParentName("Sample Text");

        return node;
    }

}
