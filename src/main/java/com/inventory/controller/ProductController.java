package com.inventory.controller;

import com.inventory.model.Product;
import com.inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // POST: Yeni ürün ekle
    // Parametreleri URL üzerinden alacağız (Basitlik için)
    // Örnek: POST /api/products?categoryId=1&name=Laptop&price=15000&stock=10
    @PostMapping
    public Product addProduct(@RequestParam Long categoryId,
                              @RequestParam String name,
                              @RequestParam double price,
                              @RequestParam int stock) {
        return productService.addProduct(categoryId, name, price, stock);
    }
    
    // PUT: Stok güncelle (Observer burada tetiklenecek)
    // Örnek: PUT /api/products/1/stock?newStock=3
    @PutMapping("/{id}/stock")
    public void updateStock(@PathVariable Long id, @RequestParam int newStock) {
        productService.updateStock(id, newStock);
    }
}