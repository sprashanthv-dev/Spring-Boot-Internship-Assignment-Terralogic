package com.sprashanthv.nodeanalysis.web;

import com.sprashanthv.nodeanalysis.response.ApiResponse;
import com.sprashanthv.nodeanalysis.validators.NodeValidator;
import com.sprashanthv.nodeanalysis.service.NodeService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.validation.Valid;

import com.sprashanthv.nodeanalysis.model.Node;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class NodeController {
    private final NodeService nodeService;
    private final NodeValidator nodeValidator;
    private static final Logger logger = Logger.getLogger(NodeController.class.getName());

    public NodeController(NodeService nodeService, NodeValidator nodeValidator) {
        this.nodeService = nodeService;
        this.nodeValidator = nodeValidator;
    }

    @GetMapping("/api/nodes")
    public ResponseEntity<ApiResponse<List<Node>>> getAllNodes() {
        ApiResponse<List<Node>> response;

        try {
            List<Node> nodes = this.nodeService.getAllNodes();
            response = new ApiResponse<>(HttpStatus.OK.name(), null, nodes);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());

            response = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.name(),
                    "Unknown error occurred",
                    null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/api/node")
    public ResponseEntity<ApiResponse<Node>> createNode(@RequestBody @Valid Node node) {
        ApiResponse<Node> response;

        try {
            boolean doFieldsHaveSpecialCharacters = this.nodeValidator.hasSpecialCharacters(node);

            if (doFieldsHaveSpecialCharacters) {
                response = new ApiResponse<>(HttpStatus.BAD_REQUEST.name(),
                        "One or more fields has occurrence of one of !@#$%^&*() characters. Please provide alphanumeric values for all fields.",
                        null);

                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            } else {
                Node newNode = this.nodeService.createNode(node);
                response = new ApiResponse<>(HttpStatus.CREATED.name(), null, newNode);

                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }
        } catch (Exception e) {

            logger.log(Level.SEVERE, e.getMessage());

            response = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.name(),
                    "Unknown error occurred while creating a node",
                    null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
