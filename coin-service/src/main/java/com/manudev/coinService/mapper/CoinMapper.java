package com.manudev.coinService.mapper;

import com.manudev.coinService.model.Coin;
import com.manudev.common.dto.CoinDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CoinMapper {

    CoinDTO coinToDTO(Coin coin);

    Coin dtoToCoin(CoinDTO coinDTO);
}
