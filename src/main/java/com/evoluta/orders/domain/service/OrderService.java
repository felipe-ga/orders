package com.evoluta.orders.domain.service;

import com.evoluta.orders.application.response.OrderDto;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<OrderDto> findAll();
    OrderDto save(OrderDto order);
    Optional<OrderDto> findById(Integer id);
    void deleteById(Integer id);
}
