package com.sprashanthv.nodeanalysis.helpers;

import com.sprashanthv.nodeanalysis.enums.NodeType;
import com.sprashanthv.nodeanalysis.model.Node;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CsvHelper {

    public boolean generateCSV(int noOfRecords) {

        List<Node> nodes = new ArrayList<>();

        for (int i = 0; i < noOfRecords; i++) {
            Node node = this.setupNode(i);
            nodes.add(node);
        }

        return nodes.size() == noOfRecords;
    }

    public boolean validateFields() {
        return true;
    }

    private Node setupNode(int nodeId) {
        Node node = new Node();

        // TODO: Move this to a constants file ?
        int groupId = (nodeId % 3) + 1;

        node.setId("T" + (nodeId + 1));
        node.setName("Node " + (nodeId + 1));
        node.setDescription("Node " + (nodeId + 1) + " description");
        node.setMemo("Node " + (nodeId + 1) + " memo");

        if (nodeId % 2 == 0) {
            node.setType(NodeType.ROADM.toString());
        } else {
            node.setType(NodeType.TRANSPONDER.toString());
        }

        node.setParentGroupName("Group " + groupId);
        node.setParentGroupId("G" + groupId);
        node.setParentName("Parent " + groupId);

        return node;
    }

}
