package com.manudev.userService.service.impl;

import com.manudev.common.dto.CoinDTO;
import com.manudev.userService.model.UserEntity;
import com.manudev.userService.model.Watchlist;
import com.manudev.userService.repository.WatchlistRepository;
import com.manudev.userService.service.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WatchlistServiceImpl implements WatchlistService {

    @Autowired
    private WatchlistRepository watchlistRepository;

    @Override
    public Watchlist findUserWatchlist(Long userId) throws Exception {
        Watchlist watchlist = watchlistRepository.findByUserId(userId);
        if(watchlist==null){
            throw new Exception("watchlist not found");
        }
        return watchlist;
    }

    @Override
    public Watchlist createWatchlist(UserEntity userEntity) {
        Watchlist watchlist = new Watchlist();
        watchlist.setUser(userEntity);
        return watchlistRepository.save(watchlist);
    }

    @Override
    public Watchlist findById(Long watchlistId) throws Exception {
        Optional<Watchlist> watchlistOptional = watchlistRepository.findById(watchlistId);
                if(watchlistOptional.isEmpty()) {
                    throw new Exception("Watchlist not found");
                }
                return watchlistOptional.get();
    }

    @Override
    public CoinDTO addItemToWatchlist(CoinDTO coinDTO, UserEntity userEntity) throws Exception {
        Watchlist watchlist = findUserWatchlist(userEntity.getUserID());

        if(watchlist.getCoinIds().contains(coinDTO.getId())){
            watchlist.getCoinIds().remove(coinDTO.getId());
        }
        else watchlist.getCoinIds().add(coinDTO.getId());
        watchlistRepository.save(watchlist);
        return coinDTO;
    }
}
