package com.manudev.Trading.orderService.service.impl;

import com.manudev.Trading.orderService.client.UserClient;
import com.manudev.Trading.orderService.client.WalletClient;
import com.manudev.common.enums.OrderStatus;
import com.manudev.common.enums.OrderType;
import com.manudev.Trading.orderService.model.Order;
import com.manudev.Trading.orderService.model.OrderItem;
import com.manudev.Trading.orderService.repository.OrderItemRepository;
import com.manudev.Trading.orderService.repository.OrderRepository;
import com.manudev.Trading.orderService.service.OrderService;
import com.manudev.common.dto.CoinDTO;
import com.manudev.common.dto.UserDTO;
import com.manudev.common.dto.WalletDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private UserClient userClient;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private WalletClient walletClient;

    @GetMapping("/api/wallet")
    public ResponseEntity<WalletDTO> getUserWallet(@RequestHeader("Authorization") String jwt) {
        UserDTO userDTO = userClient.findUserProfileByJwt(jwt);
        WalletDTO walletDTO = walletClient.getUserWallet(jwt).getBody();

        return new ResponseEntity<>(walletDTO, HttpStatus.ACCEPTED);
    }


    @Override
    public Order createOrder(UserDTO userDTO, OrderItem orderItem, OrderType orderType) {
        double price = orderItem.getCoinDTO().getCurrentPrice()*orderItem.getQuantity();

        Order order = new Order();
        order.setUserDTO(userDTO);
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

    public OrderItem createOrderItem(CoinDTO coinDTO, double quantity, double buyPrice, double sellPrice) {
        OrderItem orderItem = new OrderItem();
        orderItem.setCoinDTO(coinDTO);
        orderItem.setQuantity(quantity);
        orderItem.setBuyPrice(buyPrice);
        orderItem.setSellPrice(sellPrice);
        return orderItemRepository.save(orderItem);
    }

    @Transactional
    public Order buyAsset(CoinDTO coinDTO, double quantity, UserDTO userDTO, String jwt) throws Exception {
        if(quantity <= 0){
            throw new Exception("quantity should be > 0");
        }
        double buyPrice = coinDTO.getCurrentPrice();

        OrderItem orderItem = createOrderItem(coinDTO,quantity,buyPrice,0);
        Order order = createOrder(userDTO, orderItem, OrderType.BUY);
        orderItem.setOrder(order);

        walletClient.payOrderPayment(jwt, order.getOrderId());

        order.setOrderStatus(OrderStatus.SUCCESS);
        order.setOrderType(OrderType.BUY);
        Order savedOrder = orderRepository.save(order);

        // create asset

        return savedOrder;
    }

    @Transactional
    public Order sellAsset(CoinDTO coinDTO, double quantity, UserDTO userDTO, String jwt) throws Exception {
        if(quantity <= 0){
            throw new Exception("quantity should be > 0");
        }
        double sellPrice = coinDTO.getCurrentPrice();
        double buyPrice = assetToSell.getPrice();

        OrderItem orderItem = createOrderItem(coinDTO,quantity,buyPrice,sellPrice);
        Order order = createOrder(userDTO, orderItem, OrderType.SELL);
        orderItem.setOrder(order);

        if(assetToSell.getQuantity()>=quantity){
            order.setOrderStatus(OrderStatus.SUCCESS);
            order.setOrderType(OrderType.SELL);
            Order savedOrder = orderRepository.save(order);
            walletClient.payOrderPayment(jwt, userDTO.getUserID());

            AssetDTO updatedAsset = assetClient.updateAsset(assetToSell.getId(),-quantity);
            if(updatedAsset.getQuantity()*coinDTO.getCurrentPrice()<=1){
                assetClient.deleteAsset(updatedAsset.getId());
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
    public Order processOrder(CoinDTO coinDTO, double quantity, OrderType orderType, UserDTO userDTO) throws Exception {
        if (orderType == null) {
            throw new IllegalArgumentException("Order type must not be null");
        }

        switch (orderType) {
            case BUY:
                return buyAsset(coinDTO, quantity, userDTO);
            case SELL:
                return sellAsset(coinDTO, quantity, userDTO);
            default:
                throw new IllegalArgumentException("Invalid order type:" + orderType);
        }
    }

}
