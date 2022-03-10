package com.evoluta.orders.domain.service;

import com.evoluta.orders.application.response.OrderDto;
import com.evoluta.orders.infrastructure.respository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class OrderServiceImpl  implements OrderService {
    @Autowired
    private OrderRepository orderRepository;


    @Override
    public List<OrderDto> findAll() {
        List<OrderDto> orders = orderRepository.findAll();
        orders.stream().filter(o -> o.getOrders().size() > 0).forEach(x -> x.getOrders().stream().forEach(o -> o.setTotal(o.getUnit() * o.getPrice())));
        orders.stream().filter(o -> o.getOrders().size() > 0).forEach(order -> order.setTotal(order.getOrders().stream().mapToDouble(o -> o.getTotal()).sum()));
        return orders;
    }

    @Override
    public OrderDto save(OrderDto order) {
        return orderRepository.save(order);
    }

    @Override
    public OrderDto findById(Integer id) {
        return  orderRepository.findById(id);
    }

    @Override
    public boolean deleteById(Integer id) {
        return orderRepository.deleteById(id);
    }
}
