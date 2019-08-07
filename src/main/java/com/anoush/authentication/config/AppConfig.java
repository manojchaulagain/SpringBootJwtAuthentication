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
import org.springframework.context.annotation.PropertySource;

@Configuration
@Slf4j
//@PropertySource("classpath:application.properties")
public class AppConfig {

    private static final Integer TEMPORARY_CREDENTIALS_DURATION_DEFAULT = 7200;

    @Value("${aws.region}") String awsRegion;
    @Value("${aws.s3.data.bucket}") String awsS3DataBucket;
    @Value("${aws.temporary.credentials.validity.duration}") String credentialsValidityDuration;


    @Bean(name = "awsRegion")
    public Region awsRegion() {
        return Region.getRegion(Regions.fromName(awsRegion));
    }

    @Bean(name = "sessionCredentials")
    public BasicSessionCredentials sessionCredentials() {
        //
        // Create an instance of AWSSecurityTokenServiceClient
        //
        AWSSecurityTokenServiceClient sts_client = (AWSSecurityTokenServiceClient) AWSSecurityTokenServiceClientBuilder.defaultClient();
        //
        // Get session token request object; Set credentials validity duration
        //
        GetSessionTokenRequest session_token_request = new GetSessionTokenRequest();
        if(this.credentialsValidityDuration == null || this.credentialsValidityDuration.trim().equals("")) {
            session_token_request.setDurationSeconds(TEMPORARY_CREDENTIALS_DURATION_DEFAULT);
        } else {
            session_token_request.setDurationSeconds(Integer.parseInt(this.credentialsValidityDuration));
        }
        //
        // Create an instance of GetSessionTokenResult using session token object
        //
        GetSessionTokenResult session_token_result =
                sts_client.getSessionToken(session_token_request);
        Credentials session_creds = session_token_result.getCredentials();
        //
        // Create an instance of BasicSessionCredentials
        //
        log.info("Session Access Key: {}, Session Secret Key: {}", session_creds.getAccessKeyId(), session_creds.getSecretAccessKey());
        return new BasicSessionCredentials(
                session_creds.getAccessKeyId(),
                session_creds.getSecretAccessKey(),
                session_creds.getSessionToken());
    }

    @Bean(name = "awsS3DataBucket")
    public String awsS3DataBucket() {
        return awsS3DataBucket;
    }
}