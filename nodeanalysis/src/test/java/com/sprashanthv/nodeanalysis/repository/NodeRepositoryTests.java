package com.sprashanthv.nodeanalysis.repository;

import com.sprashanthv.nodeanalysis.config.TestConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;

import com.sprashanthv.nodeanalysis.model.Node;
import org.springframework.context.annotation.Import;


@DataJdbcTest
@Import(TestConfig.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class NodeRepositoryTests {
    @Autowired
    private NodeRepository nodeRepository;

    @Test
    public void NodeRepository_Save_ReturnsSavedNode() {
        // Arrange
        Node node = new Node();

        node.setNodeId("T1");
        node.setName("Node 1");
        node.setDescription("Node 1 Description");
        node.setMemo("Node 1 Memo");
        node.setType("TRANSPONDER");
        node.setParentGroupName("Group 1");
        node.setParentGroupId("G1");
        node.setParentName("Parent 1");

        // Act
        Node savedNode = nodeRepository.save(node);

        // Assert
        Assertions.assertThat(savedNode).isNotNull();
        Assertions.assertThat(savedNode.getNodeId()).isEqualTo("T1");
    }


}
