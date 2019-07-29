package com.crud.orders.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WasteDto {
    private Long id;
    private Date date;
    private long breakfastChefs;
    private long breakfastWaiters;
    private long totalBreakfast;
    private long lunchChefs;
    private long lunchWaiters;
    private long lunchTotal;
    private long ktChefs;
    private long ktWaiters;
    private long ktTotal;
}
