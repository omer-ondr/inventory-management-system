package com.inventory.patterns.strategy;
import org.springframework.stereotype.Component;

@Component
public class DiscountPricingStrategy implements PricingStrategy {
    @Override
    public double calculate(double price) {
        return price * 0.90; // %10 İndirim
    }
}