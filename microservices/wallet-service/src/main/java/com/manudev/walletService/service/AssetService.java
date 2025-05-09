package com.manudev.walletService.service;

import com.manudev.common.dto.CoinDTO;
import com.manudev.common.dto.UserDTO;
import com.manudev.common.dto.AssetDTO;

import java.util.List;

public interface AssetService {

    AssetDTO createAsset(UserDTO userDTO, CoinDTO coinDTO, double quantity);
    AssetDTO getAssetById(Long assetId);
    AssetDTO getAssetByUserIdAndId(Long userId, Long AssetDTOId);
    List<AssetDTO> getUsersAssets(Long userId);
    AssetDTO updateAsset(Long AssetDTOId, double quantity);
    AssetDTO findAssetByUserIdAndCoinId(Long userId, String coinId);
    String deleteAsset(Long AssetDTOId);
}
