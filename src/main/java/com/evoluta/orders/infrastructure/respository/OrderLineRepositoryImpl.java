package com.evoluta.orders.infrastructure.respository;

import com.evoluta.orders.application.response.OrderLineDto;

import java.util.List;

public class OrderLineRepositoryImpl implements OrderLineRepository{
    @Override
    public List<OrderLineDto> findAll() {
        return null;
    }

    @Override
    public OrderLineDto save(OrderLineDto orderLine) {
        return null;
    }

    @Override
    public boolean findById(Long id) {
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }
}
