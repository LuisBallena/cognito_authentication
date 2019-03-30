package com.scrap.cognito.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * AWSProperties.
 *
 * @author Luis Alonso Ballena Garcia
 */
@ConfigurationProperties(prefix = "aws")
@Getter
@Setter
public class AWSProperties {

    private Credentials credentials;
    private Cognito cognito;

    @Getter
    @Setter
    public static class Credentials{
        private String accessKey;
        private String secretKey;
    }

    @Getter
    @Setter
    public static class Cognito{
        private String region;
        private String cliendId;
        private String userPoolId;
    }

}
