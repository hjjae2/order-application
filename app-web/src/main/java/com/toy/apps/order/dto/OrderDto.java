package com.toy.apps.order.dto;

import com.toy.apps.order.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDto {

    public static class Request {
        @AllArgsConstructor
        @Builder
        @Getter
        public static class RequestDto {
            private Long memberId;
            @Valid
            private List<OrderItemDto.Create.RequestDto> orderItems;

            public void setMemberId(Long memberId) {
                this.memberId = memberId;
            }
        }

        @AllArgsConstructor
        @Builder
        @Getter
        public static class ResponseDto {
            private boolean result;

            public static ResponseDto of(boolean result) {
                return ResponseDto.builder().result(result).build();
            }

            public static ResponseDto success() {
                return of(true);
            }

            public static ResponseDto failure() {
                return of(false);
            }
        }
    }

    public static class Read {
        @AllArgsConstructor
        @Builder
        @Getter
        public static class RequestDto { }

        @AllArgsConstructor
        @Builder
        @Getter
        public static class ResponseDto {

            Long orderId;
            String orderIdentifier;
            String orderStatus;
            List<OrderItemDto.Read.ResponseDto> orderItems;

            public static ResponseDto of(Order order) {
                List<OrderItemDto.Read.ResponseDto> orderItems = order.getOrderItems().stream()
                                                                      .map(OrderItemDto.Read.ResponseDto::of)
                                                                      .collect(Collectors.toList());

                return ResponseDto.builder()
                                  .orderId(order.getId())
                                  .orderIdentifier(order.getIdentifier())
                                  .orderStatus(order.getOrderStatus().name())
                                  .orderItems(orderItems)
                                  .build();
            }
        }
    }
}
