package com.ufsm.portaldengue.config;

import com.ufsm.portaldengue.client.ExternalClient;
import feign.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;

@Configuration
public class ExternalApiConfig {
    @Value("${application.client.endpoint.external}")
    private String externalUrl;
    private Client client;

    @Autowired
    public ExternalApiConfig(Client client) {
        this.client = client;
    }

    @Bean
    ExternalClient getExternalClient() {
        return Feign.builder()
                .client(client)
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .logger(new Slf4jLogger(ExternalClient.class))
                .logLevel(Logger.Level.FULL)
                .retryer(Retryer.NEVER_RETRY)
                .options(new Request.Options(600000, 600000))
                .target(ExternalClient.class, externalUrl);
    }
}
