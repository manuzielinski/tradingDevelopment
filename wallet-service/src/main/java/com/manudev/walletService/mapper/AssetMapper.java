package com.manudev.walletService.mapper;

import com.manudev.common.dto.AssetDTO;
import com.manudev.walletService.model.Asset;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AssetMapper {
    // metodos para entidades
    AssetDTO assetToDTO(Asset asset);
    Asset dtoToAsset(AssetDTO assetDTO);

    // metodos para listas
    List<AssetDTO> assetListToDTO(List<Asset> assetList);
    List<Asset> dtoListToAsset(List<AssetDTO> dtoList);
}

