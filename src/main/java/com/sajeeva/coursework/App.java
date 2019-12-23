package com.sajeeva.coursework;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.amazonaws.services.lambda.runtime.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
        */
    public class App
    {
        static final Logger log  = LoggerFactory.getLogger(App.class);
        public static Object handleRequest(Request request, Context context) throws ResourceNotFoundException {

            log.debug("HandleRequest is invoked!");

            AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
            DynamoDBMapper mapper = new DynamoDBMapper(client);
            Car car = null;
            switch(request.getHttpMethod()) {
                case "GET":
                    car = mapper.load(Car.class,request.getName());
                    if(car==null) {
                        throw new ResourceNotFoundException("Resource not found:"+request.getName());
                    }
                    return car.getOptions();
                case "POST":
                    car = request.getCar();
                    mapper.save(car);
                    return car;

                default:
                    //Throw exception if called method is not implemented
                    break;
            }
            return null;
        }

}
