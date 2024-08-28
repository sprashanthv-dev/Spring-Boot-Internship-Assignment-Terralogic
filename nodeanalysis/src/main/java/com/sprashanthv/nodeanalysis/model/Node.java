package com.sprashanthv.nodeanalysis.model;

import com.opencsv.bean.CsvBindByName;

public class Node {
    @CsvBindByName(column = "id")
    private String id;
    @CsvBindByName(column = "name")
    private String name;
    @CsvBindByName(column = "description")
    private String description;
    @CsvBindByName(column = "memo")
    private String memo;
    @CsvBindByName(column = "type")
    private String type;
    @CsvBindByName(column = "parentGroupName")
    private String parentGroupName;
    @CsvBindByName(column = "parentGroupId")
    private String parentGroupId;
    @CsvBindByName(column = "parentName")
    private String parentName;

    public Node() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParentGroupName() {
        return parentGroupName;
    }

    public void setParentGroupName(String parentGroupName) {
        this.parentGroupName = parentGroupName;
    }

    public String getParentGroupId() {
        return parentGroupId;
    }

    public void setParentGroupId(String parentGroupId) {
        this.parentGroupId = parentGroupId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", memo='" + memo + '\'' +
                ", type='" + type + '\'' +
                ", parentGroupName='" + parentGroupName + '\'' +
                ", parentGroupId='" + parentGroupId + '\'' +
                ", parentName='" + parentName + '\'' +
                '}';
    }
}
