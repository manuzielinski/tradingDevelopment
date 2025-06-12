package com.manudev.Trading.orderService.service;

import com.manudev.common.enums.OrderType;
import com.manudev.Trading.orderService.model.Order;
import com.manudev.Trading.orderService.model.OrderItem;
import com.manudev.common.dto.CoinDTO;
import com.manudev.common.dto.UserDTO;

import java.util.List;

public interface OrderService {
    Order createOrder(UserDTO userDTO, OrderItem orderItem, OrderType orderType);
    Order getOrderByID(Long orderId) throws Exception;
    List<Order> getAllOrdersOfUser(Long userId, OrderType orderType, String asset_symbol);
    Order processOrder(CoinDTO coinDTO, double quantity, OrderType orderType, UserDTO userDTO, String jwt) throws Exception;
    Order buyAsset(CoinDTO coinDTO, double quantity, UserDTO userDTO, String jwt) throws Exception;
}
