package com.toy.apps.product.dto;

import com.toy.apps.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class ProductDto {

    public static class Create {
        public static class RequestDto {
        }

        public static class ResponseDto {
        }
    }

    public static class Read {
        public static class RequestDto {
        }

        @ToString
        @AllArgsConstructor
        @Builder
        @Getter
        public static class ResponseDto {

            Long id;
            String name;
            Integer price;
            Integer quantity;

            public static ResponseDto of(Product product) {
                return ResponseDto.builder()
                                  .id(product.getId())
                                  .name(product.getName())
                                  .price(product.getPrice())
                                  .quantity(product.getQuantity())
                                  .build();
            }
        }
    }

    public static class Update {
        public static class RequestDto {

        }

        public static class ResponseDto {

        }
    }

    public static class Delete {
        public static class RequestDto {

        }

        public static class ResponseDto {

        }
    }
}
