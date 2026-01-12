package com.inventory.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "categories")
public class Category extends InventoryComponent {

    // COMPOSITE PATTERN'İN KALBİ BURASI
    // Bir kategori, içinde birden fazla InventoryComponent (Ürün veya Kategori) barındırabilir.
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category_id") // Veritabanında alt öğelerin yanına "hangi kategoriye ait olduğu" yazılacak.
    private List<InventoryComponent> components = new ArrayList<>();

    // Yardımcı metot: Listeye eleman ekleme
    public void add(InventoryComponent component) {
        components.add(component);
    }

    // Yardımcı metot: Listeden eleman çıkarma
    public void remove(InventoryComponent component) {
        components.remove(component);
    }
}