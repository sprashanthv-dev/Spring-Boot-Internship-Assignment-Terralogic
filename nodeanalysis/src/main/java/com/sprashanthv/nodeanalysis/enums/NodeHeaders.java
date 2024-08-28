package com.sprashanthv.nodeanalysis.enums;

public enum NodeHeaders {
    NODE_ID("nodeId"),
    NAME("name"),
    DESCRIPTION("description"),
    MEMO("memo"),
    TYPE("type"),
    PARENT_GROUP_NAME("parentGroupName"),
    PARENT_GROUP_ID("parentGroupId"),
    PARENT_NAME("parentName");

    private final String value;

    NodeHeaders(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
