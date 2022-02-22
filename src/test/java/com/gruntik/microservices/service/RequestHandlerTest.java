package com.gruntik.microservices.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gruntik.microservices.Application;
import com.gruntik.microservices.model.StockInfo;
import com.gruntik.microservices.model.command.CreateRequest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

//import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withBadRequest;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
class RequestHandlerTest {

    @Autowired
    private RequestHandler requestHandler;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${service.stock.url}")
    private String serviceUrl;

    @Test
    public void createRequest() throws Exception {

        String stockCode = "GMK";
        MockRestServiceServer mockServer = MockRestServiceServer.bindTo(restTemplate).ignoreExpectOrder(true).build();
        mockServer.expect(ExpectedCount.once(), requestTo(new URI(serviceUrl + "/api/v1/info/" + stockCode)))
                .andRespond(withSuccess(objectMapper.writeValueAsString(
                        new StockInfo(stockCode, 50, BigDecimal.valueOf(10_000))), APPLICATION_JSON
                ));

        requestHandler.createRequest(new CreateRequest(UUID.randomUUID(), UUID.randomUUID(), stockCode, 5, LocalDateTime.now()));
        mockServer.verify();
    }

}