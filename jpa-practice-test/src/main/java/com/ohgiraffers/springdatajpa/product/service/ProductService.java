package com.ohgiraffers.springdatajpa.product.service;


import com.ohgiraffers.springdatajpa.product.dto.CategoryDTO;
import com.ohgiraffers.springdatajpa.product.dto.ProductDTO;
import com.ohgiraffers.springdatajpa.product.entity.Category;
import com.ohgiraffers.springdatajpa.product.entity.Product;
import com.ohgiraffers.springdatajpa.product.repository.CategoryRepository;
import com.ohgiraffers.springdatajpa.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    /* 1. findById*/
    public ProductDTO findProductByProductCode(int productCode) {
        Product foundProduct = productRepository.findById(productCode).orElseThrow(IllegalArgumentException::new);
        return modelMapper.map(foundProduct, ProductDTO.class);
    }

    /* 2. findAll : Sort */
    public List<ProductDTO> findProductList() {

        List<Product> productList = productRepository.findAll(Sort.by("productCode").descending());

        return productList.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .toList();
    }

    /* 3. findAll : Pageable */
    public Page<ProductDTO> findProductList(Pageable pageable) {
        pageable = PageRequest.of(
                pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                Sort.by("productCode").descending()
        );
        Page<Product> productList = productRepository.findAll(pageable);

        return productList.map(product -> modelMapper.map(product, ProductDTO.class));
    }

    /* 4. Query Method */
    public List<ProductDTO> findByOriginCost(String originCost) {

        int cost = Integer.parseInt(originCost);

//        List<Product> productList = productRepository.findByOriginCostGreaterThanOrderByOriginCost(originCost);

        List<Product> productList = productRepository.findByOriginCostGreaterThan(
                cost,
                Sort.by("originCost").descending()
        );

        return productList.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .toList();
    }

    /* 5. JPQL or Native Query*/
    public List<CategoryDTO> findAllCategory() {

        List<Category> categoryList = categoryRepository.findAllCategory();
        return categoryList.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .toList();

    }

    /* 6. save */
    @Transactional
    public void registProduct(ProductDTO productDTO) {

        productRepository.save(modelMapper.map(productDTO, Product.class));
    }

    /* 7. 수정( 엔티티 객체 필드 값 변경) */
    @Transactional
    public void modifyProduct(ProductDTO productDTO) {

        Product foundProduct = productRepository.findById(productDTO.getProductCode()).orElseThrow(IllegalArgumentException::new);

        foundProduct.modifyProductName(productDTO.getProductName());
    }

    /* 8. deleteById */
    @Transactional
    public void deleteProduct(int productCode) {

        productRepository.deleteById(productCode);
    }
}
