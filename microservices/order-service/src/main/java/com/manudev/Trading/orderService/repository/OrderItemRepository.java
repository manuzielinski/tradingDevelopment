package com.manudev.Trading.orderService.repository;

import com.manudev.Trading.OrderService.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
