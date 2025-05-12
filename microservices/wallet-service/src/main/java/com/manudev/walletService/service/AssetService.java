package com.manudev.walletService.service;

import com.manudev.common.dto.CoinDTO;
import com.manudev.common.dto.UserDTO;
import com.manudev.common.dto.AssetDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AssetService {

    AssetDTO createAsset(UserDTO userDTO, CoinDTO coinDTO, double quantity);
    AssetDTO getAssetById(Long assetId);
    AssetDTO getAssetByUserIdAndId(Long userId, Long assetId);
    List<AssetDTO> getUsersAssets(Long userId);
    AssetDTO updateAsset(Long assetId, double quantity);
    AssetDTO findAssetByUserIdAndCoinId(Long userId, String coinId);
    String deleteAsset(Long assetId);
}
