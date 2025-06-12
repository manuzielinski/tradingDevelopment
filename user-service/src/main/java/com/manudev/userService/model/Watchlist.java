package com.manudev.userService.model;

import com.manudev.common.dto.CoinDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Watchlist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long watchlistId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ElementCollection
    private List<String> coinIds = new ArrayList<>();
}
