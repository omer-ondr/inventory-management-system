package com.inventory.patterns.strategy;

// Gerekçe: Fiyat hesaplama yöntemlerinin ortak atasıdır.
// Service katmanı, hesaplama yaparken bu arayüzü kullanır,
// böylece arka planda hangi stratejinin çalıştığını bilmesine gerek kalmaz.
public interface PricingStrategy {
    double calculate(double price);
}