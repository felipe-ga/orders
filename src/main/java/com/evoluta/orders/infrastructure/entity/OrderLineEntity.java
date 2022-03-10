package com.evoluta.orders.infrastructure.entity;


import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "order_line")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderLineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_line_id")
    private Integer orderLineId;

    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "product_description")
    private String productDescription;

    private Integer unit;
    private Float price;

    @ManyToOne
    @JoinColumn(name="order_id", nullable = false, insertable = false, updatable = false)
    private OrderEntity orderEntity;
}

