package com.manudev.Trading.orderService.service;

import com.manudev.Trading.orderService.domain.OrderType;
import com.manudev.Trading.orderService.model.Order;
import com.manudev.Trading.orderService.model.OrderItem;

import java.util.List;

public interface git OrderService {
    Order createOrder(UserEntity userEntity, OrderItem orderItem, OrderType orderType);
    Order getOrderByID(Long orderId) throws Exception;
    List<Order> getAllOrdersOfUser(Long userId, OrderType orderType, String asset_symbol);
    Order processOrder(Coin coin, double quantity, OrderType orderType, UserEntity userEntity) throws Exception;
    Order buyAsset(Coin coin, double quantity, UserEntity userEntity) throws Exception;
}
