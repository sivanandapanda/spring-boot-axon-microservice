package com.example.orders.core.data;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "orders")
public class OrderEntity implements Serializable {
    private static final long serialVersionUID = -6709173287846011851L;

    @Id
    @Column(unique = true)
    public String orderId;

    private String productId;
    private String userId;
    private int quantity;
    private String addressId;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
