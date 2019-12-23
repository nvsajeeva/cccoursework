package com.sajeeva.coursework;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.amazonaws.services.lambda.runtime.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class ListAll {
    static final Logger log  = LoggerFactory.getLogger(App.class);
    public static List<String>  handleRequest(Request request, Context context) throws ResourceNotFoundException {

        log.debug("HandleRequest is invoked!");

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
        DynamoDBMapper mapper = new DynamoDBMapper(client);
        Car car = null;
        switch(request.getHttpMethod()) {
            case "GET":
                List<Car> cars = mapper.scan(Car.class, new DynamoDBScanExpression());

                List<String> names = cars
                        .stream()
                        .map(x -> x.getName())
                        .collect(Collectors.toList());

                if(cars==null) {
                    throw new ResourceNotFoundException("Resource not found:"+request.getName());
                }
                return names;


            default:
                //Throw exception if called method is not implemented
                break;
        }
        return null;
    }
}
