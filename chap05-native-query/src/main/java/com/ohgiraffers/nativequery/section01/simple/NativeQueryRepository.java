package com.ohgiraffers.nativequery.section01.simple;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NativeQueryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Menu nativeQueryByResultType(int menuCode) {

        /* Native Query 수행 결과를 엔티티에 매핑 시키려면 모든 컬럼이 조회 되어야만 매핑이 가능하다. */
        String query = "SELECT menu_code, menu_name, menu_price, category_code, orderable_status "
                + "FROM tbl_menu WHERE menu_code = ?";

        Query nativeQuery = entityManager.createNativeQuery(query, Menu.class)
                .setParameter(1, menuCode);

        return (Menu)nativeQuery.getSingleResult();
    }

    public List<Object[]> nativeQueryByNoResultType() {

        String query = "SELECT menu_name, menu_price FROM tbl_menu";

        Query nativeQuery = entityManager.createNativeQuery(query);

        return nativeQuery.getResultList();
    }

    public List<Object[]> nativeQueryByAutoMapping() {
        String query
                = "SELECT c.category_code, c.category_name, c.ref_category_code," +
                " COALESCE(v.menu_count, 0) menu_count" +
                " FROM tbl_category c" +
                " LEFT JOIN (SELECT COUNT(*) AS menu_count, m.category_code" +
                " FROM tbl_menu m" +
                " GROUP BY m.category_code) v ON (c.category_code = v.category_code)" +
                " ORDER BY 1";
        Query nativeQuery
                = entityManager.createNativeQuery(query, "categoryCountAutoMapping");
        return nativeQuery.getResultList();
    }

    public List<Object[]> nativeQueryByManualMapping() {
        String query
                = "SELECT c.category_code, c.category_name, c.ref_category_code," +
                " COALESCE(v.menu_count, 0) menu_count" +
                " FROM tbl_category c" +
                " LEFT JOIN (SELECT COUNT(*) AS menu_count, m.category_code" +
                " FROM tbl_menu m" +
                " GROUP BY m.category_code) v ON (c.category_code = v.category_code)" +
                " ORDER BY 1";
        Query nativeQuery
                = entityManager.createNativeQuery(query, "categoryCountManualMapping");
        return nativeQuery.getResultList();
    }

}
