package com.ohgiraffers.jpql.section07.subquery;

import com.ohgiraffers.jpql.section06.join.Category;
import jakarta.persistence.*;

@Entity(name = "Section07Menu")
@Table(name = "tbl_Menu")
public class Menu {

    @Id
    private int menuCode;

    private String menuName;

    private int menuPrice;

    private int categoryCode;

    private String orderableStatus;

    public Menu() {}

    @Override
    public String toString() {
        return "Menu{" +
                "menuCode=" + menuCode +
                ", menuName='" + menuName + '\'' +
                ", menuPrice=" + menuPrice +
                ", categoryCode=" + categoryCode +
                ", orderableStatus='" + orderableStatus + '\'' +
                '}';
    }
}
