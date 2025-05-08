package com.manudev.walletService.mapper;

import com.manudev.common.dto.AssetDTO;
import com.manudev.common.dto.UserDTO;
import com.manudev.walletService.model.Asset;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AssetMapper {
    AssetDTO assetToDTO(Asset asset);
    Asset dtoToAsset(AssetDTO assetDTO);
}