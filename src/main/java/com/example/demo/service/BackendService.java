package com.example.demo.service;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class BackendService {

  public BackendService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  private static final String ENDPOINT_AVAILABLE_PATH = "http://localhost:8080/test/available";
  private static final String ENDPOINT_NOT_AVAILABLE_PATH = "http://localhost:8080/test/not-available";

  private final RestTemplate restTemplate;

  @CircuitBreaker(name = "DEMO", fallbackMethod = "sendSecondary")
  public HttpStatusCode send() {
    return sendToEndpoint(ENDPOINT_NOT_AVAILABLE_PATH);
  }

  @SuppressWarnings("unused")
  //Method is called by Resilience4J
  private HttpStatusCode sendSecondary(Exception e) {
    return sendToEndpoint(ENDPOINT_AVAILABLE_PATH);
  }

  private HttpStatusCode sendToEndpoint(String endpoint) {
    var responseResponseEntity = restTemplate.getForEntity(endpoint, Void.class);
    return responseResponseEntity.getStatusCode();
  }
}
