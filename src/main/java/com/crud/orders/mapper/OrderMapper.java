package com.crud.orders.mapper;

import com.crud.orders.model.Order;
import com.crud.orders.model.OrderDto;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {
    public Order mapToOrder(final OrderDto orderDto){
        return new Order(
                orderDto.getId(),
                orderDto.getDate(),
                orderDto.getDepartment(),
                orderDto.getProduct(),
                orderDto.getSupplier(),
                orderDto.getStatus()
        );
    }
    public OrderDto mapToOrderDto(final Order order){
        return new OrderDto(
                order.getId(),
                order.getDate(),
                order.getDepartment(),
                order.getProduct(),
                order.getSupplier(),
                order.getStatus()
        );
    }

    public List<OrderDto> mapToOrderDtoList(final List<Order> orderList){
        return orderList.stream()
                .map(m-> new OrderDto(
                        m.getId(),m.getDate(),m.getDepartment(),m.getProduct(),m.getSupplier(),m.getStatus()))
                .collect(Collectors.toList());
    }
}
