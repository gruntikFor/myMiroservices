package com.gruntik.microservices.model;

import com.gruntik.microservices.model.command.CreateRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Request {
    UUID id;
    UUID personId;
    String stockCode;
    int stockCount;
    LocalDateTime requestDate;

    public static Request from(CreateRequest createRequest) {
        return new Request(createRequest.getId(), createRequest.getPersonId(),
                createRequest.getStockCode(), createRequest.getStockCount(), createRequest.getRequestDate());
    }

}
