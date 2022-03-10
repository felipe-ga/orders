package com.evoluta.orders.application.rest;

import com.evoluta.orders.application.response.OrderDto;
import com.evoluta.orders.application.util.Log;
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
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private static final String ERROR = "Error : %s";
    @Autowired
    private OrderService orderService;
    @GetMapping(value = "/")
    @ApiOperation("Get all orders")
    @ApiResponses({
            @ApiResponse(code = 200,message = "ok"),
            @ApiResponse(code = 404,message = "Orders not found"),
            @ApiResponse(code = 400,message = "Bad Request")
    })
    public ResponseEntity<List<OrderDto> > findAll(){
        try{
            List<OrderDto> orders = orderService.findAll();
            HttpStatus httpStatus = HttpStatus.OK;
            if(orders.size() == 0){
                httpStatus = HttpStatus.NOT_FOUND;
            }
            return new ResponseEntity<>(orders, httpStatus);
        }catch (Exception e){
            Log.info(String.format(ERROR,e.getMessage()));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "orders not found", e);
        }
    }

    @PostMapping()
    @ApiOperation("save one order")
    @ApiResponses({
            @ApiResponse(code = 201,message = "created"),
            @ApiResponse(code = 400,message = "Bad request")
    })
    public ResponseEntity<OrderDto> save(@RequestBody OrderDto order) {
        try{
            return new ResponseEntity<>(orderService.save(order),HttpStatus.CREATED);
        }catch(Exception e){
            Log.info(String.format(ERROR,e.getMessage()));
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping()
    @ApiOperation("update one order")
    @ApiResponses({
            @ApiResponse(code = 201,message = "Created"),
            @ApiResponse(code = 400,message = "Bad request")
    })
    public ResponseEntity<OrderDto> update(@RequestBody OrderDto order) {
        try{
            return new ResponseEntity<>(orderService.save(order),HttpStatus.CREATED);
        }catch(Exception e){
            Log.info(String.format(ERROR,e.getMessage()));
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
            Log.info(String.format(ERROR,e.getMessage()));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "orders not found", e);
        }
    }
    @GetMapping("/{id}")
    @ApiOperation("get one order with an id")
    @ApiResponses({
            @ApiResponse(code = 200,message = "ok"),
            @ApiResponse(code = 404,message = "Not found")
    })
    public ResponseEntity<OrderDto> getOrderById(@PathVariable("id") Integer id) {
        try{
            return new ResponseEntity<>(orderService.findById(id),HttpStatus.OK);
        }catch(Exception e){
            Log.info(String.format(ERROR,e.getMessage()));
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
