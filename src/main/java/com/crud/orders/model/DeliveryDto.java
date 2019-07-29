package com.crud.orders.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryDto {
    private Long id;
    private LocalDate deliveryDate;
    private String productName;
    private String department;
}
