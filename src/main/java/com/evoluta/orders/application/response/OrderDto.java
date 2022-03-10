package com.evoluta.orders.application.response;

import lombok.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer orderId;
    private String firstName;
    private String lastName;
    private String customerEmail;
    private List<OrderLineDto> orders;
    private Double total;
}
