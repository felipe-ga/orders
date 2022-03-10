package com.evoluta.orders.infrastructure.respository.mapper;

import com.evoluta.orders.application.response.OrderLineDto;
import com.evoluta.orders.infrastructure.entity.OrderLineEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderLineMapper {
    OrderLineDto toOrderLineDto(OrderLineEntity orderLineEntity);
    OrderLineEntity toOrderLineEntity(OrderLineDto orderLineDto);
}