package com.toy.apps.order.dto;

import com.toy.apps.order.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Min;

public class OrderItemDto {

    public static class Create {
        @AllArgsConstructor
        @Builder
        @Getter
        public static class RequestDto {
            @Min(value = 0, message = "상품의 ID는 음수 값이 될 수 없습니다.")
            Long productId;
            @Min(value = 1, message = "상품의 수량은 양수 값(1 이상)만 허용합니다.")
            Integer quantity;
        }

        public static class ResponseDto {
        }
    }

    public static class Read {
        @AllArgsConstructor
        @Builder
        @Getter
        public static class RequestDto {
        }

        @AllArgsConstructor
        @Builder
        @Getter
        public static class ResponseDto {
            String productName;
            Integer quantity;

            public static ResponseDto of(OrderItem orderItem) {
                return ResponseDto.builder()
                                  .productName(orderItem.getProduct().getName())
                                  .quantity(orderItem.getQuantity())
                                  .build();
            }
        }
    }
}
