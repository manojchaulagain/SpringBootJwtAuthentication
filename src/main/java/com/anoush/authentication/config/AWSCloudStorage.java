package com.anoush.authentication.config;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

//@Component("awsCloudStorage")
public class AWSCloudStorage implements CloudStorage {

  private final String awsS3DataBucket;

  private AmazonS3 amazonS3;

  private AmazonDynamoDB amazonDynamoDB;

  public AWSCloudStorage(
      @Autowired Region awsRegion,
      @Autowired BasicSessionCredentials sessionCredentials,
      String awsS3DataBucket) {
    this.amazonS3 =
        AmazonS3ClientBuilder.standard()
            .withCredentials(new AWSStaticCredentialsProvider(sessionCredentials))
            .build();

    this.amazonDynamoDB =
        AmazonDynamoDBClientBuilder.standard()
            .withCredentials(new AWSStaticCredentialsProvider(sessionCredentials))
            .build();
    this.awsS3DataBucket = awsS3DataBucket;
  }

  public void uploadFile(String keyName, String filePath) {
    try {
      this.amazonDynamoDB.createTable(new CreateTableRequest("testTable", new ArrayList<>()));
      this.amazonS3.putObject(this.awsS3DataBucket, keyName, filePath);
    } catch (AmazonServiceException e) {
      System.err.println(e.getErrorMessage());
    }
  }
}
