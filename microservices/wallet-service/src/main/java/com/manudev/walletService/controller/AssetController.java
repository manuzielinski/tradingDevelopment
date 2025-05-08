package com.manudev.walletService.controller;

import com.manudev.common.dto.AssetDTO;
import com.manudev.common.dto.UserDTO;
import com.manudev.walletService.client.UserClient;
import com.manudev.walletService.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    @Autowired
    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @Autowired
    private UserClient userClient;

    @GetMapping("/{assetId}")
    public ResponseEntity<AssetDTO> getAssetById(@PathVariable Long assetId){
        AssetDTO assetDTO = assetService.getAssetById(assetId);
        return ResponseEntity.ok().body(assetDTO);
    }

    @GetMapping("/coin/{coinId}/user")
    public ResponseEntity<AssetDTO> getAssetByUserIdAndCoinId(@PathVariable String coinId, @RequestHeader("Authorization") String jwt) throws Exception {
        UserDTO userDTO = userClient.findUserProfileByJwt(jwt);
        AssetDTO assetDTO = assetService.findAssetByUserIdAndCoinId(userDTO.getUserID(), coinId);
        return ResponseEntity.ok().body(assetDTO);
    }

    @GetMapping()
    public ResponseEntity<List<AssetDTO>> getAssetsForUser(@RequestHeader("Authorization") String jwt) throws Exception {
        UserDTO userDTO = userClient.findUserProfileByJwt(jwt);
        List<AssetDTO> assets = assetService.getUsersAssets(userDTO.getUserID());
        return ResponseEntity.ok().body(assets);
    }
}
