package com.evoluta.orders.application.rest;

import com.evoluta.orders.application.response.OrderDto;
import com.evoluta.orders.domain.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    Logger LOG = LoggerFactory.getLogger(OrderController.class);
    @GetMapping(value = "/")
    public ResponseEntity<List<OrderDto> > findAll(){
        try{
            return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
        }catch (Exception e){
            LOG.error("Error : " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<OrderDto> save(@RequestBody OrderDto order) {
        return new ResponseEntity<>(orderService.save(order),HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<OrderDto> update(@RequestBody OrderDto order) {
        return new ResponseEntity<>(orderService.save(order),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        try{
            orderService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            LOG.error("Error : " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable("id") Integer id) {
        return orderService.findById(id)
                .map(order -> new ResponseEntity<>(order,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
