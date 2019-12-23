package com.sajeeva.coursework;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "Car")
public class Car {


    @DynamoDBHashKey
    private String name;

    @DynamoDBAttribute
    private String options;
    private int price;

    public Car(String name, String options, int price) {
        this.name = name;
        this.options = options;
        this.price = price;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public Car() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOptions() {
        return options;
    }
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }




}

