package com.inventory.model;

import java.util.ArrayList;
import java.util.List;

// DİKKAT: Başında @Entity YOK.
// Çünkü sepeti veritabanında tutmuyoruz, satış bitince Order'a dönüşüyor.
// Bu sadece hafızada (RAM) yaşayan geçici bir taşıyıcıdır.
public class ShoppingCart {
    
    private List<Product> items = new ArrayList<>();

    public void addItem(Product product) {
        items.add(product);
    }

    public void removeItem(Product product) {
        items.remove(product);
    }

    public List<Product> getItems() {
        return items;
    }

    public void clear() {
        items.clear();
    }
}