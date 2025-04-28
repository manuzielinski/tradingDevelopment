package com.manudev.Trading.orderService.service;

import com.manudev.Trading.orderService.domain.OrderType;
import com.manudev.Trading.orderService.model.Order;
import com.manudev.Trading.orderService.model.OrderItem;
import com.manudev.common.dto.UserDTO;

import java.util.List;

public interface OrderService {
    Order createOrder(UserDTO userDTO, OrderItem orderItem, OrderType orderType);
    Order getOrderByID(Long orderId) throws Exception;
    List<Order> getAllOrdersOfUser(Long userId, OrderType orderType, String asset_symbol);
    Order processOrder(Coin coin, double quantity, OrderType orderType, UserDTO userDTO) throws Exception;
    Order buyAsset(Coin coin, double quantity, UserDTO userDTO) throws Exception;
}
