package com.inventory.repository;

import com.inventory.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// JpaRepository<EntityTürü, IDTürü>
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    // SQL yazmadan özel sorgular türetebiliriz:
    
    // "Stok miktarı şundan az olanları getir"
    List<Product> findByStockLessThan(int stockLimit);
    
    // "İsminde şu geçenleri getir"
    List<Product> findByNameContaining(String name);
}