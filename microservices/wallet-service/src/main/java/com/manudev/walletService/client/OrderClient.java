package com.manudev.walletService.client;

import com.manudev.common.dto.OrderDTO;
import com.manudev.common.enums.OrderType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "order-service")
public interface OrderClient {

    @GetMapping("/api/orders/{orderId}")
    OrderDTO getOrderById(@RequestHeader("Authorization") String jwt, @PathVariable("orderId") Long orderId);

    @GetMapping("/api/orders")
    List<OrderDTO> getAllOrdersForUser(
            @RequestHeader("Authorization") String jwt,
            @RequestParam(name = "order_type", required = false) OrderType orderType,
            @RequestParam(name = "asset_symbol", required = false) String assetSymbol
    );

    @PostMapping("/api/orders/pay")
    OrderDTO payOrder(@RequestHeader("Authorization") String jwt, @RequestBody Object createOrderRequest);
}
