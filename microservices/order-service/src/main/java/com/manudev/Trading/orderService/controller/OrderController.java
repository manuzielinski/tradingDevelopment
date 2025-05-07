package com.manudev.Trading.orderService.controller;

import com.manudev.Trading.orderService.client.CoinClient;
import com.manudev.Trading.orderService.client.UserClient;
import com.manudev.common.enums.OrderType;
import com.manudev.Trading.orderService.service.OrderService;
import com.manudev.Trading.orderService.model.CreateOrderRequest;
import com.manudev.Trading.orderService.model.Order;
import com.manudev.common.dto.CoinDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.manudev.common.dto.UserDTO;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CoinClient coinClient;

    @Autowired
    private UserClient userClient;

    @PostMapping("/pay")
    public ResponseEntity<Order> payOrderPayment(@RequestHeader("Authorization") String jwt, @RequestBody CreateOrderRequest req)throws Exception{

        // obtener datos del user desde su microservicio usando Feign
        UserDTO userDTO = userClient.findUserProfileByJwt(jwt);

        // obtener datos de la coin desde su mc usando feign
        CoinDTO coinDTO = coinClient.findById(req.getCoinId());

        Order order = orderService.processOrder(coinDTO,req.getQuantity(), req.getOrderType(), userDTO);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@RequestHeader("Authorization") String jwt, @PathVariable Long orderId) throws Exception {

        UserDTO userDTO = userClient.findUserProfileByJwt(jwt);

        Order order = orderService.getOrderByID(orderId);
        if (order.getUserId().equals(userDTO.getUserID())){
            return ResponseEntity.ok(order);
        }else{
            throw new Exception("you don't have access");
        }
    }

    @GetMapping()
    public ResponseEntity<List<Order>> getAllOrdersForUser(@RequestHeader("Authorization") String jwt,
                                                           @RequestParam(required = false) OrderType order_type,
                                                           @RequestParam(required = false) String asset_symbol) throws Exception {
        if(jwt == null) {
            throw new Exception("Missing token...");
        }

        UserDTO userDTO = userClient.findUserProfileByJwt(jwt);
        List<Order> userOrders = orderService.getAllOrdersOfUser(userDTO.getUserID(), order_type, asset_symbol);
        return ResponseEntity.ok(userOrders);
    }
}
