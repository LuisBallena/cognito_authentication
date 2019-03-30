package com.scrap.cognito.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * AWSConfig.
 *
 * @author Luis Alonso Ballena Garcia
 */
@Configuration
@EnableConfigurationProperties(AWSProperties.class)
public class AWSConfig {

    private AWSProperties awsProperties;

    public AWSConfig(AWSProperties awsProperties) {
        this.awsProperties = awsProperties;
    }

    @Bean
    public AWSCredentialsProvider createAWSCredentialsProvider() {
        AWSCredentials awsCredentials = new BasicAWSCredentials(awsProperties.getCredentials().getAccessKey()
                , awsProperties.getCredentials().getSecretKey());
        return new AWSStaticCredentialsProvider(awsCredentials);
    }

    @Bean
    public AWSCognitoIdentityProvider createAWSCognitoIdentityProvider(AWSCredentialsProvider awsCredentialsProvider) {
        return AWSCognitoIdentityProviderClientBuilder.standard()
                .withCredentials(awsCredentialsProvider)
                .withRegion(awsProperties.getCognito().getRegion())
                .build();
    }

}
