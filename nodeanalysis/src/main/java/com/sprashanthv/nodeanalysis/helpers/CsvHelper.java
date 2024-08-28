package com.sprashanthv.nodeanalysis.helpers;

import org.springframework.stereotype.Component;

import com.sprashanthv.nodeanalysis.enums.NodeHeaders;
import com.sprashanthv.nodeanalysis.enums.NodeType;
import com.sprashanthv.nodeanalysis.model.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CsvHelper {

    public List<Node> generateNodeRecords(int noOfRecords) {

        List<Node> nodes = new ArrayList<>();

        for (int i = 0; i < noOfRecords; i++) {
            Node node = this.setupNode(i);
            nodes.add(node);
        }

        return nodes;
    }

    public boolean validateFields() {
        return true;
    }

    public String[] getAllHeaderValues() {
        return Arrays.stream(NodeHeaders.values())
                .map(NodeHeaders::getValue)
                .toArray(String[]::new);
    }

    public String[] getEachRow(Node node) {
        return new String[]{
                node.getNodeId(),
                node.getName(),
                node.getDescription(),
                node.getMemo(),
                node.getType(),
                node.getParentGroupName(),
                node.getParentGroupId(),
                node.getParentName()
        };
    }

    private Node setupNode(int nodeId) {
        Node node = new Node();

        // TODO: Move this to a constants file ?
        int groupId = (nodeId % 3) + 1;

        node.setNodeId("T" + (nodeId + 1));
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
