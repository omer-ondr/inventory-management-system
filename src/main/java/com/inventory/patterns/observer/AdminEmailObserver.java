package com.inventory.patterns.observer;

import com.inventory.model.Product;
import org.springframework.stereotype.Component;

@Component
public class AdminEmailObserver implements StockObserver {

    @Override
    public void update(Product product) {
        // Gerçek mail sunucusu olmadığı için konsola basarak simüle ediyoruz
        System.out.println("--------------------------------------------------");
        System.out.println("📧 [ADMİN'E E-POSTA GÖNDERİLİYOR...]");
        System.out.println("Kime: admin@inventory.com");
        System.out.println("Konu: ACİL STOK UYARISI: " + product.getName());
        System.out.println("Mesaj: " + product.getName() + " ürününün stoğu kritik seviyeye (" + product.getStock() + ") düştü!");
        System.out.println("--------------------------------------------------");
    }
}