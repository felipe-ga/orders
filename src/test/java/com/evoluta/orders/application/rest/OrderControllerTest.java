package com.evoluta.orders.application.rest;
import com.evoluta.orders.application.response.OrderDto;
import com.evoluta.orders.application.response.OrderLineDto;
import com.evoluta.orders.application.util.JsonUtil;
import com.evoluta.orders.domain.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;


    @MockBean
    OrderService orderService;

    static List<OrderDto> orders;
    static OrderDto orderUpdate;
    static OrderDto orderDtoNew;
    static List<OrderLineDto> ordersLineDto;
    @BeforeAll
    static void init() {
        orders = Arrays.asList(
                new OrderDto(1, "Simon", "Stay", "jstay@evoluta.com", null, 0.0),
                new OrderDto(2,"Philip","Key","pkey@evoluta.com",null,0.0),
                new OrderDto(3,"Gregory","Stand","gstand@evoluta.com",null,0.0),
                new OrderDto(4,"Steven","Gray","sgray@evoluta.com",null,0.0)
        );
        ordersLineDto = Arrays.asList(
                new OrderLineDto(1,1,1,"Scanner",10,10.6f,0f),
                new OrderLineDto(2,1,2,"Printer",10,10.6f,0f)
        );
        orderUpdate = new OrderDto(1, "Simon", "Stay", "jstay@evoluta.com", null, 0.0);
        orderDtoNew = new OrderDto(null,"Philip","Key","pkey@evoluta.com",null,0.0);
    }

    @Test
    public void listAllOrder_whenGetMethod() throws Exception {
        Mockito.when(orderService.findAll()).thenReturn(orders);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/order/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[2].lastName", is("Stand")));

        verify(orderService, times(1)).findAll();
        verifyNoMoreInteractions(orderService);
    }

    @Test
    public void should_response_with_empty_list_when_do_not_exist_orders() throws Exception {
        Mockito.when(orderService.findAll()).thenReturn(new ArrayList<OrderDto>());
        mockMvc.perform(
                MockMvcRequestBuilders
                    .get("/order/")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound());
    }

    @Test
    public void getOrderById_whenGetMethod() throws Exception {
        Mockito.when(orderService.findById(1)).thenReturn(orderUpdate);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/order/{id}",1)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());

        verify(orderService, times(1)).findById(1);
        verifyNoMoreInteractions(orderService);
    }

    @Test
    public void createOrder_whenPostMethod() throws Exception {
        orderDtoNew.setOrders(ordersLineDto);
        when(orderService.save(orderDtoNew)).thenReturn(orderDtoNew);
        mockMvc.perform(
                    MockMvcRequestBuilders
                        .post("/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(orderDtoNew)))
                        .andExpect(status().isCreated());
    }

    @Test
    public void should_response_badRequest_when_order_can_not_save() throws Exception {
        orderDtoNew.setOrders(ordersLineDto);
        when(orderService.save(null)).thenReturn(null);
        mockMvc.perform(
                    MockMvcRequestBuilders
                        .post("/order/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(null)))
                        .andExpect(status().isBadRequest());
    }


    @Test
    public void updateOrder_whenPutMethod() throws Exception {
        orderUpdate.setOrders(ordersLineDto);
        when(orderService.save(orderUpdate)).thenReturn(orderUpdate);
        mockMvc.perform(
                    MockMvcRequestBuilders
                        .put("/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(orderDtoNew)))
                        .andExpect(status().isCreated());
    }

    @Test
    public void should_response_badRequest_when_order_can_not_update() throws Exception {
        orderDtoNew.setOrders(ordersLineDto);
        when(orderService.save(null)).thenReturn(null);
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .put("/order/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JsonUtil.toJson(null)))
                                .andExpect(status().isBadRequest());
    }


    @Test
    public void deleteById_whenDeleteMethod() throws Exception {
        Mockito.when(orderService.deleteById(1)).thenReturn(true);
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/order/{id}",1)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(orderService, times(1)).findById(1);
        verifyNoMoreInteractions(orderService);
    }

}