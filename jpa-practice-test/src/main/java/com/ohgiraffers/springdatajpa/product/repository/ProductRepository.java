package com.ohgiraffers.springdatajpa.product.repository;

import com.ohgiraffers.springdatajpa.product.entity.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/* <엔티티, Id타입> */
public interface ProductRepository extends JpaRepository<Product, Integer> {

    /* 파라미터로 전달 받은 가격을 초과하는 메뉴 목록 조회 */
    List<Product> findByOriginCostGreaterThan(String originCost);

    /* 파라미터로 전달 받은 가격을 초과하는 메뉴 목록 가격순으로 조회 */
    List<Product> findByOriginCostGreaterThanOrderByOriginCost(String originCost);

    /* 파라미터로 전달 받은 가격을 초과하는 메뉴 목록 전달 받은 정렬 기준으로 조회 */
    /* CAST 함수를 이용하여 DB에 originCost를 String -> int 로 변경 */
    @Query("SELECT p FROM Product p WHERE CAST(p.originCost AS int) > ?1")
    List<Product> findByOriginCostGreaterThan(int originCost, Sort sort);
}
