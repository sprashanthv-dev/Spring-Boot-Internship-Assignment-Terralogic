package com.sprashanthv.nodeanalysis.validators;

import org.springframework.stereotype.Component;

import com.sprashanthv.nodeanalysis.model.Node;

@Component
public class NodeValidator {

    private static final String SPECIAL_CHARS_REGEX = ".*[!@#$%^&*()].*";

    public boolean hasSpecialCharacters(Node node) {
        String[] nodeInfo = this.getNodeFieldValues(node);
        return doFieldsHaveSpecialCharacters(nodeInfo);
    }

    private String[] getNodeFieldValues(Node node) {
        String nodeId = node.getNodeId();
        String name = node.getName();
        String description = node.getDescription();
        String memo = node.getMemo();
        String type = node.getType();
        String parentGroupName = node.getParentGroupName();
        String parentGroupId = node.getParentGroupId();
        String parentName = node.getParentName();

        return new String[]{nodeId, name, description, memo, type, parentGroupName, parentGroupId, parentName};
    }


    public boolean doFieldsHaveSpecialCharacters(String[] fields) {

        for (String field : fields) {
            if (field.matches(SPECIAL_CHARS_REGEX)) {
                return true;
            }
        }

        return false;
    }
}
