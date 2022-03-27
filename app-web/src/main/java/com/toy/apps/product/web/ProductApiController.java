package com.toy.apps.product.web;

import com.toy.apps.product.dto.ProductDto;
import com.toy.apps.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/products")
@RestController
public class ProductApiController {

    private final ProductService productService;

    @GetMapping
    public List<ProductDto.Read.ResponseDto> findAll(@RequestParam(value = "name", required = false) String name) {
        if (name == null) {
            return productService.findAll();
        }

        return productService.findAllByName(name);
    }
}
