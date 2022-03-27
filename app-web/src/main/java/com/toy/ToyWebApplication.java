package com.toy;

import com.toy.apps.member.entity.Member;
import com.toy.apps.member.repository.MemberRepository;
import com.toy.apps.product.entity.Product;
import com.toy.apps.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@RequiredArgsConstructor
@SpringBootApplication
public class ToyWebApplication {

    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(ToyWebApplication.class, args);
    }

    @Bean
    public void init() {
        Member member = memberRepository.save(Member.builder().id(1L).email("user@toy.com").password("$2a$10$KoftBGRexCyEg5FZ4aY7J.S0Qur/uF7rX8DL93F14ztJ.j4bai3X2").type(Member.Type.NORMAL).build());
        productRepository.save(Product.builder().id(1L).name("A0001").price(12000).quantity(129).seller(member).build());
        productRepository.save(Product.builder().id(2L).name("A0002").price(2000).quantity(29).seller(member).build());
        productRepository.save(Product.builder().id(3L).name("A0003").price(34000).quantity(5).seller(member).build());
    }
}
