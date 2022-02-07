package com.justpickup.orderservice.domain.order.entity;

import com.justpickup.orderservice.domain.orderItem.entity.OrderItem;
import com.justpickup.orderservice.domain.transaction.entity.Transaction;
import com.justpickup.orderservice.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseEntity {

    @Id    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    private Long userId;

    private Long userCouponId;

    private Long storeId;

    private Long orderPrice;

    private LocalDateTime orderTime;

    private Long usedPoint;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToOne(mappedBy = "order", fetch = FetchType.LAZY)
    private Transaction transaction;

    @BatchSize(size = 100)
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

}
