package com.inventory.controller;

import com.inventory.model.Order;
import com.inventory.model.Product;
import com.inventory.model.ShoppingCart;
import com.inventory.model.User;
import com.inventory.patterns.strategy.DiscountPricingStrategy;
import com.inventory.patterns.strategy.PricingStrategy;
import com.inventory.patterns.strategy.RegularPricingStrategy;
import com.inventory.repository.OrderRepository;
import com.inventory.repository.ProductRepository;
import com.inventory.repository.UserRepository;
import com.inventory.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired private OrderRepository orderRepository;
    @Autowired private OrderService orderService;
    @Autowired private ProductRepository productRepository;
    @Autowired private UserRepository userRepository;
    
    // Stratejileri doğrudan çağırıyoruz (Spring bunları otomatik yönetiyor)
    @Autowired private RegularPricingStrategy regularStrategy;
    @Autowired private DiscountPricingStrategy discountStrategy;

    // POST: Satın Al
    @PostMapping("/checkout")
    public Order checkout(@RequestBody CheckoutRequest request) {
        // 1. Kullanıcıyı bul (Veritabanında önceden ekli olmalı)
        User user = userRepository.findById(request.userId)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

        // 2. Sepeti Hazırla (Gelen ürün ID'lerinden)
        ShoppingCart cart = new ShoppingCart();
        for (Long productId : request.productIds) {
            Product p = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Ürün yok: " + productId));
            cart.addItem(p);
        }

        // 3. Stratejiyi Seç (Client'tan gelen string'e göre)
        PricingStrategy strategy;
        if ("DISCOUNT".equalsIgnoreCase(request.strategyType)) {
            strategy = discountStrategy;
        } else {
            strategy = regularStrategy;
        }

        // 4. Servisi Çağır ve İşi Bitir
        return orderService.checkout(user, cart, strategy);
    }

    // --- İstek Yapısını Karşılayan DTO Sınıfı ---
    // (Bunu dosyanın en altına ekleyebilirsin)
    public static class CheckoutRequest {
        public Long userId;           // Kim alıyor?
        public List<Long> productIds; // Hangi ürünleri alıyor?
        public String strategyType;   // "REGULAR" veya "DISCOUNT"
    }

    
    
    // YENİ METOD: Tüm siparişleri getir (En yeniden en eskiye)
    @GetMapping
    public List<Order> getAllOrders() {
        // OrderRepository'de findAll() zaten var.
        // Listeyi ters çevirmek veya tarihe göre sıralamak şık olur ama düz de dönebiliriz.
        return orderRepository.findAll(); 
    }

    // YENİ: Belirli bir kullanıcının siparişlerini getir
    @GetMapping("/user/{userId}")
    public List<Order> getOrdersByUser(@PathVariable Long userId) {
        return orderRepository.findByUser_Id(userId);
    }
}