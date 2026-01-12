package com.inventory.patterns.observer;

import com.inventory.model.Product;

// Gerekçe: Stok değişikliklerinde haber verilecek nesnelerin ortak sözleşmesi.
// Admin, Log sistemi veya Müşteri... Kim dinleyecekse bu interface'i kullanmalı.
public interface StockObserver {
    void update(Product product);
}