package com.inventory.service;

import com.inventory.exception.ResourceNotFoundException;
import com.inventory.model.Category;
import com.inventory.model.Product;
import com.inventory.patterns.observer.StockObserver;
import com.inventory.repository.CategoryRepository;
import com.inventory.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired private ProductRepository productRepository;
    @Autowired private CategoryRepository categoryRepository;

    // Observer Listesi (Spring Bean'leri de buraya inject edilebilir)
    @Autowired private List<StockObserver> observers; 
    // Not: Spring, StockObserver implement eden tüm sınıfları (AdminEmail, DatabaseLogger)
    // otomatik bulup bu listeye doldurur! (Müthiş bir Spring özelliğidir)

    @Transactional
    public Product addProduct(Long categoryId, String name, double price, int stock) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Kategori bulunamadı!"));

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);
        
        // Ürünü listeye ekle
        category.add(product);
        
        // Kategoriyi kaydederken, dönen GÜNCEL nesneyi yakalıyoruz
        Category savedCategory = categoryRepository.save(category); 
        
        // Kaydedilmiş kategorinin içindeki ürün listesinden, eklediğimiz ürünü buluyoruz.
        // (Son eklenen veya ismi eşleşen)
        // Burada basitçe listedeki son elemanı veya isminden eşleşeni alabiliriz.
        // Stream API ile ismi eşleşen son ürünü bulmak en garantisidir:
        
        return (Product) savedCategory.getComponents().stream()
                .filter(c -> c instanceof Product && c.getName().equals(name))
                .reduce((first, second) -> second) // Son ekleneni al (Aynı isimde varsa)
                .orElse(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // --- OBSERVER MANTIĞI BURADA ---

    @Transactional
    public void updateStock(Long productId, int newStock) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("ID'si " + productId + " olan ürün sistemde kayıtlı değil!"));

        int oldStock = product.getStock();
        product.setStock(newStock);
        productRepository.save(product);

        // Kritik stok kontrolü
        if (newStock < 5 && newStock < oldStock) {
            notifyObservers(product);
        }
    }

    private void notifyObservers(Product product) {
        for (StockObserver observer : observers) {
            observer.update(product);
        }
    }
}