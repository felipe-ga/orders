package com.evoluta.orders.infrastructure.respository.mapper;

import com.evoluta.orders.application.response.OrderDto;
import com.evoluta.orders.infrastructure.entity.OrderEntity;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderEntity toOrderEntity(OrderDto orderEntity);
    OrderDto toOrderDto(OrderEntity orderEntity);
    List<OrderDto> toListOrdersDto(List<OrderEntity> orders);
}
