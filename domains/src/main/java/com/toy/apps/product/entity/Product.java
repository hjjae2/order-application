package com.toy.apps.product.entity;

import com.toy.apps.member.entity.Member;
import com.toy.apps.product.exception.ProductValidationException;
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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@ToString
@Builder
@Getter
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer quantity;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member seller;

    @CreatedDate
    @Column
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column
    private LocalDateTime updatedAt;

    public boolean isQuantityOverThan(int quantity) {
        return this.quantity >= quantity;
    }

    public void minusQuantity(int quantity) {
        this.quantity -= quantity;
        validateQuantity();
    }

    /**
     * 정책에 따른 유효성 검사 로직을 추가/수정합니다. <br>
     */
    private void validateName() {
        if (this.name == null || this.name.isBlank()) {
            throw new ProductValidationException("상품의 이름이 유효하지 않습니다. (상품의 이름은 빈 값이 될 수 없습니다.)");
        }
    }

    private void validatePrice() {
        if (this.price < 0) {
            throw new ProductValidationException("상품의 가격이 유효하지 않습니다. (상품의 가격은 음수 값이 될 수 없습니다.)");
        }
    }

    private void validateQuantity() {
        if (this.quantity < 0) {
            throw new ProductValidationException("상품의 수량이 부족합니다. (상품의 수량은 음수 값이 될 수 없습니다.)");
        }
    }
}
