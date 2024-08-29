package com.sprashanthv.nodeanalysis.repository;

import com.sprashanthv.nodeanalysis.config.TestConfig;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;

import com.sprashanthv.nodeanalysis.model.Node;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@DataJdbcTest
@Import(TestConfig.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class NodeRepositoryTests {
    @Autowired
    private NodeRepository nodeRepository;

    private Node node1;
    private Node node2;

    @BeforeEach
    public void setup() {
        // Arrange
        node1 = new Node();

        node1.setNodeId("T1");
        node1.setName("Node 1");
        node1.setDescription("Node 1 Description");
        node1.setMemo("Node 1 Memo");
        node1.setType("TRANSPONDER");
        node1.setParentGroupName("Group 1");
        node1.setParentGroupId("G1");
        node1.setParentName("Parent 1");

        node2 = new Node();

        node2.setNodeId("T2");
        node2.setName("Node 2");
        node2.setDescription("Node 2 Description");
        node2.setMemo("Node 2 Memo");
        node2.setType("ROADM");
        node2.setParentGroupName("Group 2");
        node2.setParentGroupId("G2");
        node2.setParentName("Parent 2");
    }

    @Test
    public void NodeRepository_Save_ReturnsSavedNode() {
        // Act
        Node savedNode = nodeRepository.save(node1);

        // Assert
        Assertions.assertThat(savedNode).isNotNull();
        Assertions.assertThat(savedNode.getNodeId()).isEqualTo("T1");
    }

    @Test
    public void NodeRepository_FindAll_ReturnsMoreThanOneNode() {
        nodeRepository.save(node1);
        nodeRepository.save(node2);

        Iterable<Node> nodeIterable = nodeRepository.findAll();
        Iterator<Node> nodeIterator = nodeIterable.iterator();

        List<Node> nodes = new ArrayList<>();

        while (nodeIterator.hasNext()) {
            nodes.add(nodeIterator.next());
        }

        Assertions.assertThat(nodes).isNotNull();
        Assertions.assertThat(nodes.size()).isEqualTo(2);
    }

    @Test
    public void NodeRepository_SaveAll_ReturnsAllSavedNodes() {
        // Act
        List<Node> nodesToBeSaved = new ArrayList<>(List.of(node1, node2));
        Iterable<Node> savedNodes = nodeRepository.saveAll(nodesToBeSaved);

        Iterator<Node> nodeIterator = savedNodes.iterator();

        List<Node> nodes = new ArrayList<>();

        while (nodeIterator.hasNext()) {
            nodes.add(nodeIterator.next());
        }

        Assertions.assertThat(nodes).isNotNull();
        Assertions.assertThat(nodes.size()).isEqualTo(2);
    }



}
