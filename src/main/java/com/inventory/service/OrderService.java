package com.inventory.service;

import com.inventory.model.Order;
import com.inventory.model.Product;
import com.inventory.model.ShoppingCart;
import com.inventory.model.User;
import com.inventory.patterns.strategy.PricingStrategy;
import com.inventory.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class OrderService {

    @Autowired private OrderRepository orderRepository;
    @Autowired private ProductService productService; // Stok düşmek için ProductService'i çağırıyoruz

    /**
     * Fiyat Hesapla (Strategy Pattern Kullanımı)
     */
    public double calculateTotal(ShoppingCart cart, PricingStrategy strategy) {
        double rawTotal = 0;
        for (Product p : cart.getItems()) {
            rawTotal += p.getPrice();
        }
        // Seçilen stratejiye (Normal veya İndirimli) göre hesapla
        return strategy.calculate(rawTotal);
    }

    /**
     * Satın Al (Checkout)
     */
    @Transactional
    public Order checkout(User user, ShoppingCart cart, PricingStrategy strategy) {
        if (cart.getItems().isEmpty()) return null;

        // 1. Fiyatı stratejiye göre hesapla
        double finalAmount = calculateTotal(cart, strategy);

        // 2. Stokları düşür (ProductService üzerinden)
        for (Product p : cart.getItems()) {
            // Stok güncelleme metodu Observer'ı tetikleyecektir
            productService.updateStock(p.getId(), p.getStock() - 1);
        }

        // 3. Siparişi veritabanına kaydet
        Order order = new Order();
        order.setUser(user);
        order.setTotalAmount(finalAmount);
        order.setProducts(new ArrayList<>(cart.getItems()));
        
        return orderRepository.save(order);
    }
}