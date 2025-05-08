package com.manudev.Trading.orderService.client;

import com.manudev.common.dto.AssetDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "asset-service")
public interface AssetClient {

    @GetMapping("/{assetId}")
    public ResponseEntity<AssetDTO> get
}
