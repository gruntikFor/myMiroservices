package com.gruntik.microservices.model.command;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CreateRequest {
    UUID id;
    UUID personId;
    String stockCode;
    int stockCount;
    LocalDateTime requestDate;

}
