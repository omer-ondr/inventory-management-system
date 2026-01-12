package com.inventory.repository;

import com.inventory.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // Bir kullanıcının tüm siparişlerini getir:
    List<Order> findByUser_Id(Long userId);
}