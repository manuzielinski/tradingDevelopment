package com.manudev.Trading.orderService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.manudev.common.dto.CoinDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    private double quantity;

    @ManyToOne
    private CoinDTO coinDTO;

    private double buyPrice;

    private double sellPrice;

    @JsonIgnore
    @OneToOne
    private Order order;
}
