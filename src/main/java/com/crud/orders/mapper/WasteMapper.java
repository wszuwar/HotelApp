package com.crud.orders.mapper;

import com.crud.orders.model.Waste;
import com.crud.orders.model.WasteDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WasteMapper {

    public Waste mapToWaste(final WasteDto wasteDto){
        return new Waste(
                wasteDto.getId(),
                wasteDto.getDate(),
                wasteDto.getBreakfastChefs(),
                wasteDto.getBreakfastWaiters(),
                wasteDto.getTotalBreakfast(),
                wasteDto.getLunchChefs(),
                wasteDto.getLunchWaiters(),
                wasteDto.getLunchTotal(),
                wasteDto.getKtChefs(),
                wasteDto.getKtWaiters(),
                wasteDto.getKtTotal()
        );
    }

    public WasteDto mapToWasteDto(final Waste waste){
        return new WasteDto(
                waste.getId(),
                waste.getDate(),
                waste.getBreakfastChefs(),
                waste.getBreakfastWaiters(),
                waste.getTotalBreakfast(),
                waste.getLunchChefs(),
                waste.getLunchWaiters(),
                waste.getLunchTotal(),
                waste.getKtChefs(),
                waste.getKtWaiters(),
                waste.getKtTotal()
        );
    }
    public List<WasteDto> mapToWasteDtoList(final List<Waste> wasteList){
        return wasteList.stream()
                .map(m-> new WasteDto(
                        m.getId(), m.getDate(), m.getBreakfastChefs(), m.getBreakfastWaiters(),m.getTotalBreakfast(),
                        m.getLunchChefs(), m.getLunchWaiters(), m.getLunchTotal(),
                        m.getKtChefs(), m.getKtWaiters(), m.getKtTotal()
                )).collect(Collectors.toList());
    }
}
