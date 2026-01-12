package com.inventory.model;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;

/**
 * COMPOSITE PATTERN - BASE COMPONENT
 * @Entity: Bu sınıfın veritabanında bir karşılığı olacağını söyler.
 * @Inheritance: Miras alma stratejisini belirler. JOINED stratejisi en profesyonelidir.
 * Ürünler ve Kategoriler için ayrı tablolar oluşturur ama ortak ID kullanır.
 */
@Data // Lombok: Getter, Setter, toString hepsini otomatik oluşturur.
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "inventory_components")
public abstract class InventoryComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "İsim boş bırakılamaz!")
    private String name;

    // Composite Pattern gereği ortak operasyonlar burada tanımlanabilir
    // Ancak JPA Entity'lerinde iş mantığı (method) yerine veri yapısına odaklanırız.
}