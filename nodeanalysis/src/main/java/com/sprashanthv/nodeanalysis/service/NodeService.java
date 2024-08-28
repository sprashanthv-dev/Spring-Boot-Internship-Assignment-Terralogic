package com.sprashanthv.nodeanalysis.service;

import com.sprashanthv.nodeanalysis.model.Node;
import com.sprashanthv.nodeanalysis.repository.NodeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class NodeService {
    private final NodeRepository nodeRepository;

    public NodeService(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    public List<Node> getAllNodes() {
        List<Node> nodes = new ArrayList<>();
        Iterable<Node> nodesIterable = this.nodeRepository.findAll();

        for (Node node : nodesIterable) {
            nodes.add(node);
        }

        return nodes;
    }
}
