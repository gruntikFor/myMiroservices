package com.gruntik.microservices.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Request {
    private UUID id;
    private UUID personId;
    private String stockCode;
    private int stockCount;
    private LocalDateTime requestDate;
}
