package com.evoluta.orders.infrastructure.entity;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderEntity")
    @OnDelete( action = OnDeleteAction.CASCADE )
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private List<OrderLineEntity> orders;
}
