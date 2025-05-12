package com.manudev.Trading.orderService.client;

import com.manudev.common.dto.AssetDTO;
import com.manudev.common.dto.CreateAssetRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(name = "asset-service")
public interface AssetClient {

    @GetMapping("/{assetId}")
    public ResponseEntity<AssetDTO> getAssetById(@PathVariable Long assetId);

    @GetMapping("/user/{userId}/coin/{coinId}")
    AssetDTO findAssetByUserIdAndCoinId(@PathVariable("userId") Long userId, @PathVariable("coinId") String coinId);

    @GetMapping()
    public ResponseEntity<List<AssetDTO>> getAssetsForUser(@RequestHeader("Authorization") String jwt);

    @PostMapping
    ResponseEntity<AssetDTO> createAsset(@RequestBody CreateAssetRequest request);

    @DeleteMapping("/{assetId}")
    ResponseEntity<String> deleteAsset(@PathVariable("assetId") Long assetId);

    @PutMapping("/{assetId}")
    AssetDTO updateAsset(@PathVariable("assetId") Long assetId, @RequestParam("quantity") double quantity);


}
