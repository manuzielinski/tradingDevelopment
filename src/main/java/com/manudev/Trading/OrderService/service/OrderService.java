package com.manudev.Trading.OrderService.service;

import com.manudev.Trading.OrderService.domain.OrderType;
import com.manudev.Trading.OrderService.model.Order;
import com.manudev.Trading.OrderService.model.OrderItem;
import com.manudev.Trading.coinService.model.Coin;
import com.manudev.Trading.userService.model.UserEntity;
import org.apache.catalina.User;

import java.util.List;

public interface OrderService {
    Order createOrder(UserEntity userEntity, OrderItem orderItem, OrderType orderType);
    Order getOrderByID(Long orderId) throws Exception;
    List<Order> getAllOrdersOfUser(Long userId, OrderType orderType, String asset_symbol);
    Order processOrder(Coin coin, double quantity, OrderType orderType, UserEntity userEntity) throws Exception;
    Order buyAsset(Coin coin, double quantity, UserEntity userEntity) throws Exception;
}
