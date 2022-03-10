package com.evoluta.orders.domain.repository;

import com.evoluta.orders.infrastructure.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepositoryCrud extends CrudRepository<OrderEntity, Integer> {
    List<OrderEntity> findAll();
    OrderEntity save(OrderEntity order);
}
