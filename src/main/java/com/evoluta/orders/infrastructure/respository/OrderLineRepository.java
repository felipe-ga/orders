package com.evoluta.orders.infrastructure.respository;

import com.evoluta.orders.application.response.OrderDto;
import com.evoluta.orders.application.response.OrderLineDto;

import java.util.List;

public interface OrderLineRepository {
    List<OrderLineDto> findAll();
    OrderLineDto save(OrderLineDto orderLine);
    boolean findById(Long id);
    boolean deleteById(Long id);
}
