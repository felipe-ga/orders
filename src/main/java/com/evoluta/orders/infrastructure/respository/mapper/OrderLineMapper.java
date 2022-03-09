package com.evoluta.orders.infrastructure.respository.mapper;

import com.evoluta.orders.application.response.OrderDto;
import com.evoluta.orders.application.response.OrderLineDto;
import com.evoluta.orders.infrastructure.entity.OrderEntity;
import com.evoluta.orders.infrastructure.entity.OrderLineEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderLineMapper {
    OrderLineDto toOrderLineDto(OrderLineEntity orderLineEntity);
    OrderLineEntity toOrderLineEntity(OrderLineDto orderLineDto);
    List<OrderLineDto> toOrdersLineDto(List<OrderLineEntity> orders);
    List<OrderLineEntity> toOrdersLineEntity(List<OrderLineDto> orders);
}