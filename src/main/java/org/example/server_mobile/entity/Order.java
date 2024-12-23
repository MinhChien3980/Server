package org.example.server_mobile.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    @ManyToOne
    @JoinColumn(name = "discount_id")
    Discount discount;
    @JoinColumn(name = "cart_id")
    Cart cart;
    @Column(name = "order_status")
    String status;
    @Column(name = "total_price")
    Double totalPrice;
    @Column(name = "total_discount")
    Double totalDiscount;
    @Column(name = "grand_total")
    Double grandTotal;
    @JoinColumn(name = "address_id")
    Address address;
}
