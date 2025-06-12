package com.manudev.Trading.orderService.model;

import com.manudev.common.enums.OrderStatus;
import com.manudev.common.enums.OrderType;
import com.manudev.common.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false)
    private OrderType orderType;

    @Column(nullable = false)
    private BigDecimal price;

    private LocalDateTime timestamp = LocalDateTime.now();

    @Column(nullable = false)
    private OrderStatus orderStatus;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private OrderItem orderItem;

    public void setUserDTO(UserDTO userDTO) {
        this.userId = userDTO.getUserId();  // we only use the userID, not the entire entity
    }
}
