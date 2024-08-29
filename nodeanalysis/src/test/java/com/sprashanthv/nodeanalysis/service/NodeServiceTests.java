package com.sprashanthv.nodeanalysis.service;

import com.sprashanthv.nodeanalysis.model.Node;
import com.sprashanthv.nodeanalysis.repository.NodeRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NodeServiceTests {
    @Mock
    private NodeRepository nodeRepository;
    @InjectMocks
    private NodeService nodeService;
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
    public void NodeService_CreateNode_ReturnsANode() {
        when(nodeRepository.save(Mockito.any(Node.class))).thenReturn(node1);

        Node savedNode = nodeService.createNode(node1);

        Assertions.assertThat(savedNode).isNotNull();
    }

    @Test
    public void NodeService_GetAllNodes_ReturnsMoreThanOneNode() {
        when(nodeRepository.findAll()).thenReturn(List.of(node1, node2));

        List<Node> allNodes = nodeService.getAllNodes();

        Assertions.assertThat(allNodes).isNotNull();
        Assertions.assertThat(allNodes.size()).isEqualTo(2);
    }
}
