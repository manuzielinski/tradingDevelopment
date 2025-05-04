package com.manudev.Trading.orderService.client;

import com.manudev.common.dto.CoinDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "coin-service")
public interface CoinClient {

    @GetMapping("api/v1/coins/{id}")
    CoinDTO findbyId(@PathVariable("id") String id);
}
