package com.toy.apps.product.repository;

import com.toy.apps.product.entity.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    TestEntityManager em;

    @Autowired
    ProductRepository productRepository;

    @DisplayName("[findAllByName] (p1 2건, p2 1건 중) p1 검색 => 2건 반환")
    @Test
    void findAllByName_경우_2건조회() {
        // given
        String name = "p1";
        Product product1 = em.persist(Product.builder().name("p1").price(100).quantity(1).build());
        Product product2 = em.persist(Product.builder().name("p1").price(200).quantity(2).build());
        Product product3 = em.persist(Product.builder().name("p2").price(300).quantity(3).build());
        em.flush();
        em.clear();

        // when
        List<Product> allByName = productRepository.findAllByName(name);

        // then
        Assertions.assertThat(allByName.size()).isEqualTo(2);
        Assertions.assertThat(allByName.stream().anyMatch(one -> one.getId().equals(product1.getId()))).isTrue();
        Assertions.assertThat(allByName.stream().anyMatch(one -> one.getId().equals(product2.getId()))).isTrue();
        Assertions.assertThat(allByName.stream().allMatch(one -> name.equals(one.getName()))).isTrue();
    }

    @DisplayName("[findAllByName] (p1 2건, p2 1건 중) p2 검색 => 1건 반환")
    @Test
    void findAllByName_경우_1건조회() {
        // given
        String name = "p2";
        Product product1 = em.persist(Product.builder().name("p1").price(100).quantity(1).build());
        Product product2 = em.persist(Product.builder().name("p1").price(200).quantity(2).build());
        Product product3 = em.persist(Product.builder().name("p2").price(300).quantity(3).build());
        em.flush();
        em.clear();

        // when
        List<Product> allByName = productRepository.findAllByName(name);

        // then
        Assertions.assertThat(allByName.size()).isEqualTo(1);
        Assertions.assertThat(allByName.stream().allMatch(one -> one.getId().equals(product3.getId()))).isTrue();
        Assertions.assertThat(allByName.stream().allMatch(one -> name.equals(one.getName()))).isTrue();
    }

    @DisplayName("[findAllByName] (p1 2건, p2 1건 중) p3 검색 => 0건 반환")
    @Test
    void findAllByName_경우_0건조회() {
        // given
        String name = "p3";
        em.persist(Product.builder().name("p1").price(100).quantity(1).build());
        em.persist(Product.builder().name("p1").price(200).quantity(2).build());
        em.persist(Product.builder().name("p2").price(300).quantity(3).build());
        em.flush();
        em.clear();

        // when
        List<Product> allByName = productRepository.findAllByName(name);

        // then
        Assertions.assertThat(allByName.isEmpty()).isTrue();
    }
}