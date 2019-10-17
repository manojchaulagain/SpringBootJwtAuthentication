package com.anoush.authentication.controller;

import com.anoush.authentication.config.CloudStorage;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class S3BucketUploader implements CommandLineRunner {

  private final CloudStorage awsCloudStorage;

  public S3BucketUploader(CloudStorage awsCloudStorage) {
    this.awsCloudStorage = awsCloudStorage;
  }

  @Override
  public void run(String... args) throws Exception {
    this.awsCloudStorage.uploadFile("message1234.txt", "/home/support/Documents/corpus.lm");
  }
}
