package com.evoluta.orders.infrastructure.respository.mapper;

import com.evoluta.orders.application.response.OrderDto;
import com.evoluta.orders.application.response.OrderLineDto;
import com.evoluta.orders.infrastructure.entity.OrderEntity;
import com.evoluta.orders.infrastructure.entity.OrderLineEntity;
import com.evoluta.orders.infrastructure.respository.OrderRepositoryImpl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderEntity toOrderEntity(OrderDto orderEntity);
    OrderDto toOrderDto(OrderEntity orderEntity);
    List<OrderDto> toListOrdersDto(List<OrderEntity> orders);
    List<OrderEntity> toListOrdersEntity(List<OrderDto> orders);
}
