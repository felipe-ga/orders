package com.evoluta.orders.infrastructure.respository;

import com.evoluta.orders.application.response.OrderDto;
import com.evoluta.orders.application.rest.OrderController;
import com.evoluta.orders.domain.repository.OrderRepositoryCrud;
import com.evoluta.orders.infrastructure.entity.OrderEntity;
import com.evoluta.orders.infrastructure.entity.OrderLineEntity;
import com.evoluta.orders.infrastructure.respository.mapper.OrderLineMapper;
import com.evoluta.orders.infrastructure.respository.mapper.OrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class OrderRepositoryImpl implements OrderRepository{
    Logger LOG = LoggerFactory.getLogger(OrderRepositoryImpl.class);
    @Autowired
    private OrderRepositoryCrud orderRepositoryCrud;

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderLineMapper orderLineMapper;

    @Override
    public List<OrderDto> findAll() {
        return orderMapper.toListOrdersDto(orderRepositoryCrud.findAll());
    }

    @Override
    public OrderDto save(OrderDto order) {
        try{
            OrderEntity orderEntity = orderMapper.toOrderEntity(order);
            orderEntity.setOrders(order.getOrders().stream().map(x -> orderLineMapper.toOrderLineEntity(x)).collect(Collectors.toList()));
            return orderMapper.toOrderDto(orderRepositoryCrud.save(orderEntity));
        }catch(Exception e){
            LOG.info("error : " + e.getMessage());
            return null;
        }
    }

    @Override
    public OrderDto findById(Integer id) {
        return orderRepositoryCrud.findById(id).map(o -> orderMapper.toOrderDto(o)).get();
    }

    @Override
    public boolean deleteById(Integer id) {
        try{
            orderRepositoryCrud.deleteById(id);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
