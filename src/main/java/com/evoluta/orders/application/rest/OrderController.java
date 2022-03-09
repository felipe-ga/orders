package com.evoluta.orders.application.rest;

import com.evoluta.orders.application.response.OrderDto;
import com.evoluta.orders.domain.service.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation("Get all orders")
    @ApiResponses({
            @ApiResponse(code = 200,message = "ok"),
            @ApiResponse(code = 404,message = "Orders not found")
    })
    public ResponseEntity<List<OrderDto> > findAll(){
        try{
            return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
        }catch (Exception e){
            LOG.error("Error : " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    @ApiOperation("save one order with an id")
    @ApiResponses({
            @ApiResponse(code = 200,message = "ok"),
            @ApiResponse(code = 400,message = "Bad request")
    })
    public ResponseEntity<OrderDto> save(@RequestBody OrderDto order) {
        try{
            return new ResponseEntity<>(orderService.save(order),HttpStatus.OK);
        }catch(Exception e){
            LOG.error("Error : " + e.getMessage());
            return new ResponseEntity<>(orderService.save(order),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping()
    @ApiOperation("update one order with an id")
    @ApiResponses({
            @ApiResponse(code = 200,message = "ok"),
            @ApiResponse(code = 400,message = "Bad request")
    })
    public ResponseEntity<OrderDto> update(@RequestBody OrderDto order) {
        try{
            return new ResponseEntity<>(orderService.save(order),HttpStatus.OK);
        }catch(Exception e){
            LOG.error("Error : " + e.getMessage());
            return new ResponseEntity<>(orderService.save(order),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation("delete one order with an id")
    @ApiResponses({
            @ApiResponse(code = 200,message = "ok"),
            @ApiResponse(code = 400,message = "Bad request")
    })
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        try{
            orderService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            LOG.error("Error : " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/{id}")
    @ApiOperation("get one order with an id")
    @ApiResponses({
            @ApiResponse(code = 200,message = "ok"),
            @ApiResponse(code = 404,message = "Not found")
    })
    public ResponseEntity<OrderDto> getOrderById(@PathVariable("id") Integer id) {
        return orderService.findById(id)
                .map(order -> new ResponseEntity<>(order,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
