package com.ohgiraffers.associationmapping.section01.manytoone;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "Section01Category")
@Table(name = "tbl_category")
public class Category {


    @Id
    private int categoryCode;

    private String categoryName;

    private Integer refCategoryCode;

    protected Category() {}

    public Category(int categoryCode, String categoryName, Integer refCategoryCode) {
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
        this.refCategoryCode = refCategoryCode;
    }
}
