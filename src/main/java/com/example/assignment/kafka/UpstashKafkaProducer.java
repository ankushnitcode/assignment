package com.example.assignment.kafka;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UpstashKafkaProducer {
    private final RestTemplate restTemplate;
    private String userName;
    private String password;
    private String url;

    public UpstashKafkaProducer() {
        this.restTemplate = new RestTemplate();
        Properties confInfo = new Properties();

        try (InputStream inStream =
                     getClass().getClassLoader().getResourceAsStream("config.properties")) {
            confInfo.load(inStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        userName = confInfo.getProperty("uname", "");
        password = confInfo.getProperty("pass", "");
        url = confInfo.getProperty("url", "");
    }


    public void produceMessage(String topic, String message) throws IOException {
        String completeUrl = url +"/produce/"+ topic + "/" + message;
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(userName, password);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                completeUrl,
                HttpMethod.GET,
                entity,
                String.class
        );

        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Failed to consume messages. Status code: " + responseEntity.getStatusCode());
        }

    }
}
