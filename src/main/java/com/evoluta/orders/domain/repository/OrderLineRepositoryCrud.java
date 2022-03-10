package com.evoluta.orders.domain.repository;

import com.evoluta.orders.infrastructure.entity.OrderLineEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderLineRepositoryCrud extends CrudRepository<OrderLineEntity, Integer> {
    List<OrderLineEntity> findAll();
    OrderLineEntity save(OrderLineEntity orderLine);
}
