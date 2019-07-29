package com.crud.orders.mapper;

import com.crud.orders.model.Delivery;
import com.crud.orders.model.DeliveryDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DeliveryMapper {
    public Delivery mapToDelivery(final DeliveryDto deliveryDto){
        return new Delivery(
                deliveryDto.getId(),
                deliveryDto.getDeliveryDate(),
                deliveryDto.getProductName(),
                deliveryDto.getDepartment()
        );
    }
    public DeliveryDto mapToDeliveryDto(final Delivery delivery){
        return new DeliveryDto(
                delivery.getId(),
                delivery.getDeliveryDate(),
                delivery.getProductName(),
                delivery.getDepartment()
        );
    }
    public List<DeliveryDto> mapToDeliveryDtoList(final List<Delivery> deliveryList){
        return deliveryList.stream()
                .map(m->new DeliveryDto(
                        m.getId(), m.getDeliveryDate(), m.getProductName(), m.getDepartment()
                )).collect(Collectors.toList());
    }
}
