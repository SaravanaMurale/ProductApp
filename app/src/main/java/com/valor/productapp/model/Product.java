package com.valor.productapp.model;

public class Product {

    int productId;
    String productName;
    String productCat;
    String productAmt;
    String tax;
    String totalAmt;

    public Product(int productId, String productName, String productCat, String productAmt, String tax, String totalAmt) {
        this.productId = productId;
        this.productName = productName;
        this.productCat = productCat;
        this.productAmt = productAmt;
        this.tax = tax;
        this.totalAmt = totalAmt;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCat() {
        return productCat;
    }

    public void setProductCat(String productCat) {
        this.productCat = productCat;
    }

    public String getProductAmt() {
        return productAmt;
    }

    public void setProductAmt(String productAmt) {
        this.productAmt = productAmt;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(String totalAmt) {
        this.totalAmt = totalAmt;
    }
}
