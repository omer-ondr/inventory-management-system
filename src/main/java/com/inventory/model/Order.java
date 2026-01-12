package com.inventory.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderDate;
    private double totalAmount;

    // Sipariş kime ait? (User ile ilişki)
    @ManyToOne 
    @JoinColumn(name = "user_id")
    private User user;

    // Siparişin içinde hangi ürünler var?
    // Not: Gerçek hayatta ManyToMany kullanılır ama şimdilik basit tutuyoruz.
    // Sipariş içeriğini String olarak tutmak (örn: "Kalem, Defter") ödev için yeterli olabilir
    // veya basit bir ilişki kurabiliriz. Şimdilik basit tutalım:
    
    @ManyToMany
    @JoinTable(
        name = "order_products",
        joinColumns = @JoinColumn(name = "order_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    public Order() {
        this.orderDate = LocalDateTime.now();
    }
}