package com.evoluta.orders.domain.service;

import com.evoluta.orders.application.response.OrderDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> findAll();
    OrderDto save(OrderDto order);
    OrderDto findById(Integer id);
    boolean deleteById(Integer id);
}
