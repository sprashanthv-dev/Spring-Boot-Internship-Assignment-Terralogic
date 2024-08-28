package com.sprashanthv.nodeanalysis.web;

import com.sprashanthv.nodeanalysis.helpers.ApiResponse;
import com.sprashanthv.nodeanalysis.service.NodeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import com.sprashanthv.nodeanalysis.model.Node;

import java.util.List;

@RestController
public class NodeController {
    private final NodeService nodeService;

    public NodeController(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    @GetMapping("/api/nodes")
    public ResponseEntity<ApiResponse<List<Node>>> getAllNodes() {
        try {
            List<Node> nodes = this.nodeService.getAllNodes();
            ApiResponse<List<Node>> response = new ApiResponse<>(HttpStatus.OK.name(), null, nodes);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse<List<Node>> response = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.name(), "Unknown error occured", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
