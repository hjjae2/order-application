package com.toy.apps.order.entity;

import com.toy.apps.member.entity.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ToString
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "orders")
@Entity
public class Order {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column
    private String identifier;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ToString.Exclude
    @Builder.Default
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @CreatedDate
    @Column
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column
    private LocalDateTime updatedAt;

    public static Order newOrderBy(Member member) {
        return Order.builder()
                    .identifier(generateNewIdentifier())
                    .member(member)
                    .orderStatus(OrderStatus.REQUEST)
                    .build();
    }

    public void succeed() {
        this.orderStatus = OrderStatus.SUCCESS;
    }

    public void fail() {
        this.orderStatus = OrderStatus.FAILURE;
    }

    private static String generateNewIdentifier() {
        String dateTime = LocalDateTime.now().toString().replaceAll("\\D", "");
        String hash = UUID.randomUUID().toString();
        hash = hash.substring(hash.lastIndexOf("-") + 1);

        return dateTime + "-" + hash;
    }

    public enum OrderStatus {
        REQUEST,
        SUCCESS,
        FAILURE,
    }
}
