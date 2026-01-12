package com.inventory.patterns.observer;

import com.inventory.model.Notification;
import com.inventory.model.Product;
import com.inventory.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DatabaseLoggingObserver implements StockObserver {

    // Observer, veritabanı ile konuşmak için Repository'i kullanacak
    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    @Transactional // Veritabanı işlemi olduğu için Transactional ekliyoruz
    public void update(Product product) {
        // 1. Yeni bir bildirim nesnesi oluştur
        String logMessage = "KRİTİK STOK UYARISI: Stok seviyesi " + product.getStock() + " adede düştü.";
        Notification notification = new Notification(logMessage, product.getName());

        // 2. Veritabanına kaydet (INSERT INTO notifications...)
        notificationRepository.save(notification);

        // 3. İstersen yine konsola bilgi ver (Geliştirici görsün diye)
        System.out.println("💾 [DB LOG]: Stok uyarısı veritabanına kaydedildi -> ID: " + notification.getId());
    }
}