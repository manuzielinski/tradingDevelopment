package com.manudev.Trading.orderService.repository;

import com.manudev.Trading.orderService.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserId(Long userId);
}
