package com.evoluta.orders.infrastructure.respository;
import com.evoluta.orders.application.response.OrderDto;
import com.evoluta.orders.infrastructure.entity.OrderEntity;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    List<OrderDto> findAll();
    OrderDto save(OrderDto order);
    Optional<OrderDto> findById(Integer id);
    void deleteById(Integer id);
}
