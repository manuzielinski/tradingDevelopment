package com.manudev.userService.service;

import com.manudev.common.dto.CoinDTO;
import com.manudev.common.dto.UserDTO;
import com.manudev.userService.model.UserEntity;
import com.manudev.userService.model.Watchlist;

public interface WatchlistService {

    Watchlist findUserWatchlist(Long userId) throws Exception;
    Watchlist createWatchlist(UserEntity userEntity);
    Watchlist findById(Long watchlistId) throws Exception;
    CoinDTO addItemToWatchlist(CoinDTO coinDTO, UserEntity userEntity) throws Exception;
}
