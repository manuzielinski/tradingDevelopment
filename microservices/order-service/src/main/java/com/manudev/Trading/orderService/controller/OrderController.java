package com.manudev.Trading.orderService.controller;

import com.manudev.Trading.orderService.service.OrderService;
import com.manudev.Trading.orderService.model.CreateOrderRequest;
import com.manudev.Trading.orderService.model.Order;
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
    private UserService userService;

    @Autowired
    private CoinService coinService;

    @Autowired
    private UserMapper userMapper;

//     @Autowired
//    private WalletTransactionService walletTransactionService;

    @PostMapping("/pay")
    public ResponseEntity<Order> payOrderPayment(@RequestHeader("Authorization") String jwt, @RequestBody CreateOrderRequest req)throws Exception{

        UserDTO userDTO = userService.findUserProfileByJwt(jwt);
        UserEntity userEntity = userMapper.dtoToUser(userDTO);

        Coin coin = coinService.findById(req.getCoinId());

        Order order = orderService.processOrder(coin,req.getQuantity(), req.getOrderType(), userEntity);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@RequestHeader("Authorization") String jwt, @PathVariable Long orderId) throws Exception {

        UserDTO userDTO = userService.findUserProfileByJwt(jwt);

        Order order = orderService.getOrderByID(orderId);
        if (order.getUser().getUserID().equals(userDTO.getUserID())){
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

        UserDTO userDTO = userService.findUserProfileByJwt(jwt);
        UserEntity userEntity = userMapper.dtoToUser(userDTO);

        List<Order> userOrders = orderService.getAllOrdersOfUser(userEntity.getUserID(), order_type, asset_symbol);
        return ResponseEntity.ok(userOrders);
    }
}
