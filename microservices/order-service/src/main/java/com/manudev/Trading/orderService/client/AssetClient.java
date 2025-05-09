package com.manudev.Trading.orderService.client;

import com.manudev.common.dto.AssetDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "asset-service")
public interface AssetClient {

    @GetMapping("/{assetId}")
    public ResponseEntity<AssetDTO> getAssetById(@PathVariable Long assetId);

    @GetMapping("/coin/{coinId}/user")
    public ResponseEntity<AssetDTO> getAssetByUserIdAndCoinId(@PathVariable String coinId, @RequestHeader("Authorization") String jwt);

    @GetMapping()
    public ResponseEntity<List<AssetDTO>> getAssetsForUser(@RequestHeader("Authorization") String jwt);
}
