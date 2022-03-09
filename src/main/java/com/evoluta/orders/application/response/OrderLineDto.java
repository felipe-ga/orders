package com.evoluta.orders.application.response;

import com.evoluta.orders.infrastructure.entity.OrderEntity;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderLineDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer orderLineId;
    private Integer orderId;
    private Integer productId;
    private String productDescription;
    private Integer unit;
    private Float price;
    private Float total;
}
