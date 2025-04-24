package com.manudev.Trading.OrderService.service.impl;

import com.manudev.Trading.OrderService.domain.OrderStatus;
import com.manudev.Trading.OrderService.domain.OrderType;
import com.manudev.Trading.OrderService.model.Order;
import com.manudev.Trading.OrderService.model.OrderItem;
import com.manudev.Trading.OrderService.repository.OrderItemRepository;
import com.manudev.Trading.OrderService.repository.OrderRepository;
import com.manudev.Trading.OrderService.service.OrderService;
import com.manudev.Trading.coinService.model.Coin;
import com.manudev.Trading.userService.mapper.UserMapper;
import com.manudev.Trading.userService.model.UserEntity;
import com.manudev.Trading.walletService.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private WalletService walletService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public Order createOrder(UserEntity userEntity, OrderItem orderItem, OrderType orderType) {
        double price = orderItem.getCoin().getCurrentPrice()*orderItem.getQuantity();

        Order order = new Order();
        order.setUser(userEntity);
        order.setOrderItem(orderItem);
        order.setOrderType(orderType);
        order.setPrice(BigDecimal.valueOf(price));
        order.setTimestamp(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.PENDING);

        return orderRepository.save(order);
    }

    @Override
    public Order getOrderByID(Long orderId) throws Exception {
        return orderRepository.findById(orderId).orElseThrow(() -> new Exception("Order not found"));
    }

    public OrderItem createOrderItem(Coin coin, double quantity, double buyPrice, double sellPrice) {
        OrderItem orderItem = new OrderItem();
        orderItem.setCoin(coin);
        orderItem.setQuantity(quantity);
        orderItem.setBuyPrice(buyPrice);
        orderItem.setSellPrice(sellPrice);
        return orderItemRepository.save(orderItem);
    }

    @Transactional
    public Order buyAsset(Coin coin, double quantity, UserEntity userEntity) throws Exception {
        if(quantity <= 0){
            throw new Exception("quantity should be > 0");
        }
        double buyPrice = coin.getCurrentPrice();

        OrderItem orderItem = createOrderItem(coin,quantity,buyPrice,0);
        Order order = createOrder(userEntity, orderItem, OrderType.BUY);
        orderItem.setOrder(order);

        walletService.payOrderPayment(order, userEntity);

        order.setOrderStatus(OrderStatus.SUCCESS);
        order.setOrderType(OrderType.BUY);
        Order savedOrder = orderRepository.save(order);

        // create asset

        return savedOrder;
    }

    @Transactional
    public Order sellAsset(Coin coin, double quantity, UserEntity userEntity) throws Exception {
        if(quantity <= 0){
            throw new Exception("quantity should be > 0");
        }
        double sellPrice = coin.getCurrentPrice();
        double buyPrice = assetToSell.getPrice();

        OrderItem orderItem = createOrderItem(coin,quantity,buyPrice,sellPrice);
        Order order = createOrder(userEntity, orderItem, OrderType.SELL);
        orderItem.setOrder(order);

        if(assetToSell.getQuantity()>=quantity){
            order.setOrderStatus(OrderStatus.SUCCESS);
            order.setOrderType(OrderType.SELL);
            Order savedOrder = orderRepository.save(order);
            walletService.payOrderPayment(order, userEntity);

            Asset updatedAsset = assetService.updateAsset(assetToSell.getId(),-quantity);
            if(updatedAsset.getQuantity()*coin.getCurrentPrice()<=1){
                assetService.deleteAsset(updatedAsset.getId());
            }
            return savedOrder;
        }
        throw new Exception("Insufficient Quantity to sell");

        // create asset

        return savedOrder;
    }

    @Override
    public List<Order> getAllOrdersOfUser(Long userId, OrderType orderType, String asset_symbol) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    @Transactional
    public Order processOrder(Coin coin, double quantity, OrderType orderType, UserEntity userEntity) throws Exception {
        if (orderType == null) {
            throw new IllegalArgumentException("Order type must not be null");
        }

        switch (orderType) {
            case BUY:
                return buyAsset(coin, quantity, userEntity);
            case SELL:
                return sellAsset(coin, quantity, userEntity);
            default:
                throw new IllegalArgumentException("Invalid order type:" + orderType);
        }
    }

}
