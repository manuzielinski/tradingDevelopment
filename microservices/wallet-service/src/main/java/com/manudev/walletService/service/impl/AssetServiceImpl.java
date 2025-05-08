package com.manudev.walletService.service.impl;

import com.manudev.common.dto.AssetDTO;
import com.manudev.common.dto.CoinDTO;
import com.manudev.common.dto.UserDTO;
import com.manudev.walletService.mapper.AssetMapper;
import com.manudev.walletService.model.Asset;
import com.manudev.walletService.repository.AssetRepository;
import com.manudev.walletService.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AssetServiceImpl implements AssetService {

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private AssetMapper assetMapper;

    @Override
    public AssetDTO createAsset(UserDTO userDTO, CoinDTO coinDTO, double quantity) {
        Asset asset = new Asset();
        asset.setUserId(userDTO.getUserID());
        asset.setCoinId(coinDTO.getId());
        asset.setQuantity(quantity);
        asset.setBuyPrice(coinDTO.getCurrentPrice());

        return assetMapper.assetToDTO(assetRepository.save(asset));
    }

    @Override
    public AssetDTO getAssetById(Long assetId) {
        return assetMapper.assetToDTO(assetRepository.findById(assetId)
                .orElseThrow(() -> new RuntimeException("Asset not found")));
    }

    @Override
    public AssetDTO getAssetByUserIdAndId(Long userId, Long assetId) {
        return null;
    }

    @Override
    public List<AssetDTO> getUsersAssets(Long userId) {
        return assetMapper.assetToDTO(assetRepository.findByUserId(userId));
    }

    @Override
    public Asset updateAsset(Long assetId, double quantity) {

        Asset oldAsset = getAssetById(assetId);
        oldAsset.setQuantity(quantity+oldAsset.getQuantity());
        return assetRepository.save(oldAsset);
    }

    @Override
    public Asset findAssetByUserIdAndCoinId(Long userId, String coinId) {
        return assetRepository.findByUserIdAndCoinId(userId,coinId);
    }

    @Override
    public void deleteAsset(Long assetId) {
        assetRepository.deleteById(assetId);
    }
}
