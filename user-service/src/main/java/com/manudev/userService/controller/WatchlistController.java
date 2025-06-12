package com.manudev.userService.controller;

import com.manudev.common.dto.CoinDTO;
import com.manudev.common.dto.UserDTO;
import com.manudev.userService.client.CoinClient;
import com.manudev.userService.mapper.UserMapper;
import com.manudev.userService.model.UserEntity;
import com.manudev.userService.model.Watchlist;
import com.manudev.userService.repository.WatchlistRepository;
import com.manudev.userService.service.UserService;
import com.manudev.userService.service.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/api/watchlist")
public class WatchlistController {

    @Autowired
    private WatchlistService watchlistService;

    @Autowired
    private WatchlistRepository watchlistRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CoinClient coinClient;


    @GetMapping("/user")
    public ResponseEntity<Watchlist> getUserWatchlist(@RequestHeader("Authorization") String jwt) throws Exception {
        UserDTO userDTO = userService.findUserProfileByJwt(jwt);
        Watchlist watchlist = watchlistService.findUserWatchlist(userDTO.getUserId());
        return ResponseEntity.ok(watchlist);
    }

    @PostMapping("/create")
    public ResponseEntity<Watchlist> createWatchlist(@RequestHeader("Authorization") String jwt) {
        try {
            UserDTO userDTO = userService.findUserProfileByJwt(jwt);
            UserEntity userEntity = userMapper.dtoToUser(userDTO);
            Watchlist createdWatchlist = watchlistService.createWatchlist(userEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdWatchlist);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PatchMapping("/add/coin/{coinId}")
    public ResponseEntity<CoinDTO> addItemToWatchlist(@RequestHeader("Authorization") String jwt, @PathVariable String coinId) {
        UserDTO userDTO = userService.findUserProfileByJwt(jwt);
        CoinDTO addCoin = coinClient.findById(coinId);
        return ResponseEntity.ok(addCoin);
    }

}
