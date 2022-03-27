package com.toy.apps.product.service;

import com.toy.apps.product.entity.Product;
import com.toy.apps.product.dto.ProductDto;
import com.toy.apps.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductDto.Read.ResponseDto> findAll() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(ProductDto.Read.ResponseDto::of).collect(Collectors.toList());
    }

    public List<ProductDto.Read.ResponseDto> findAllByName(final String name) {
        List<Product> products = productRepository.findAllByName(name);

        return products.stream().map(ProductDto.Read.ResponseDto::of).collect(Collectors.toList());
    }
}
