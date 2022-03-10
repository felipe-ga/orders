package com.evoluta.orders.infrastructure.respository;
import com.evoluta.orders.application.response.OrderDto;

import java.util.List;

public interface OrderRepository {
    List<OrderDto> findAll();
    OrderDto save(OrderDto order);
    OrderDto findById(Integer id);
    boolean deleteById(Integer id);
}
