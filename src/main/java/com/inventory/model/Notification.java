package com.inventory.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;
    private LocalDateTime date;

    // Hangi ürünle ilgili? (Opsiyonel ama şık durur)
    private String productName;

    public Notification() {
        this.date = LocalDateTime.now();
    }

    public Notification(String message, String productName) {
        this.message = message;
        this.productName = productName;
        this.date = LocalDateTime.now();
    }
}