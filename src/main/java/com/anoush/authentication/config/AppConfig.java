package com.anoush.authentication.config;

import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClient;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClientBuilder;
import com.amazonaws.services.securitytoken.model.Credentials;
import com.amazonaws.services.securitytoken.model.GetSessionTokenRequest;
import com.amazonaws.services.securitytoken.model.GetSessionTokenResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
// @PropertySource("classpath:application.properties")
public class AppConfig {

  private static final Integer TEMPORARY_CREDENTIALS_DURATION_DEFAULT = 7200;

  @Value("${aws.region}")
  String awsRegion;

  @Value("${aws.s3.data.bucket}")
  String awsS3DataBucket;

  @Value("${aws.temporary.credentials.validity.duration}")
  String credentialsValidityDuration;

  @Bean(name = "awsRegion")
  public Region awsRegion() {
    return Region.getRegion(Regions.fromName(awsRegion));
  }

  @Bean(name = "sessionCredentials")
  public BasicSessionCredentials sessionCredentials() {
    AWSSecurityTokenServiceClient awsSecurityTokenServiceClient =
        (AWSSecurityTokenServiceClient) AWSSecurityTokenServiceClientBuilder.standard().build();
    GetSessionTokenRequest sessionTokenRequest = new GetSessionTokenRequest();
    if (this.credentialsValidityDuration == null
        || this.credentialsValidityDuration.trim().equals("")) {
      sessionTokenRequest.setDurationSeconds(TEMPORARY_CREDENTIALS_DURATION_DEFAULT);
    } else {
      sessionTokenRequest.setDurationSeconds(Integer.parseInt(this.credentialsValidityDuration));
    }
    GetSessionTokenResult sessionTokenResult =
        awsSecurityTokenServiceClient.getSessionToken(sessionTokenRequest);
    Credentials credentials = sessionTokenResult.getCredentials();
    log.info(
        "Session Access Key: {}, Session Secret Key: {}",
        credentials.getAccessKeyId(),
        credentials.getSecretAccessKey());

    return new BasicSessionCredentials(
        credentials.getAccessKeyId(),
        credentials.getSecretAccessKey(),
        credentials.getSessionToken());
  }

  @Bean(name = "awsS3DataBucket")
  public String awsS3DataBucket() {
    return awsS3DataBucket;
  }
}
