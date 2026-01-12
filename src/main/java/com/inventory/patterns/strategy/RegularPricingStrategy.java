package com.inventory.patterns.strategy;
import org.springframework.stereotype.Component;

// @Component: Spring'in bu sınıfı tanımasını ve yönetmesini sağlar (Bean).
@Component
public class RegularPricingStrategy implements PricingStrategy {
    @Override
    public double calculate(double price) {
        return price; // İndirim yok, düz fiyat
    }
}