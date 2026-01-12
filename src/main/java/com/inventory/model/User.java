package com.inventory.model;

import jakarta.persistence.*;
import lombok.Data;

@Data // Getter, Setter, ToString otomatik oluşturur
@Entity
@Table(name = "users") // Veritabanında "users" tablosu olacak
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    
    // Admin mi Müşteri mi? Basitçe boolean tutabiliriz.
    private boolean isAdmin; 

    // Spring Boot için boş constructor gereklidir (Lombok @Data bunu bazen kapsamazsa eklemek iyidir)
    public User() {}

    public User(String username, String email, boolean isAdmin) {
        this.username = username;
        this.email = email;
        this.isAdmin = isAdmin;
    }
}