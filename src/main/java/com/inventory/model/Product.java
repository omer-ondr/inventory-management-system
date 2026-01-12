package com.inventory.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;        // YENİ
import jakarta.validation.constraints.NotBlank;   // YENİ
import jakarta.validation.constraints.NotNull;    // YENİ
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true) // Ata sınıftaki (Component) ID ve Name'i de dikkate al
@Entity
@Table(name = "products")
public class Product extends InventoryComponent {
    @Min(value = 0, message = "Fiyat 0'dan küçük olamaz!") // KURAL 1
    private double price;

    @Min(value = 0, message = "Stok negatif olamaz!")      // KURAL 2
    private int stock;

    // Product bir "Leaf" (Yaprak) olduğu için altına başka component eklenemez.
    // Bu yüzden burada ekstra bir ilişki tanımlamıyoruz.
}