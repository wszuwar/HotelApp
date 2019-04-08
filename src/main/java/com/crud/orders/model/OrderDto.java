package com.crud.orders.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;




import java.util.Date;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private Date date;
    private String department;
    private String product;
    private String supplier;
    private String status;
}
