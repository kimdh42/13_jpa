package com.ohgiraffers.mapping.section03.compositekey.subsection02.idclass;

public class CartDTO {

    private int cartownerMemberNo;

    private int addedBookNo;

    private int quantity;

    public CartDTO(int cartownerMemberNo, int addedBookNo, int quantity) {
        this.cartownerMemberNo = cartownerMemberNo;
        this.addedBookNo = addedBookNo;
        this.quantity = quantity;
    }

    public int getCartownerMemberNo() {
        return cartownerMemberNo;
    }

    public void setCartownerMemberNo(int cartownerMemberNo) {
        this.cartownerMemberNo = cartownerMemberNo;
    }

    public int getAddedBookNo() {
        return addedBookNo;
    }

    public void setAddedBookNo(int addedBookNo) {
        this.addedBookNo = addedBookNo;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartDTO{" +
                "cartownerMemberNo=" + cartownerMemberNo +
                ", addedBookNo=" + addedBookNo +
                ", quantity=" + quantity +
                '}';
    }
}
