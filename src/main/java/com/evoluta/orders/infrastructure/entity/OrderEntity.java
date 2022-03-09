package com.evoluta.orders.infrastructure.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "order_")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "customer_email")
    private String customerEmail;

    @OneToMany(mappedBy = "orderEntity")
    private Set<OrderLineEntity> orders;
}
