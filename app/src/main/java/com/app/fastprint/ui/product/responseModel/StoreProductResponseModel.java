package com.app.fastprint.ui.product.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public
class StoreProductResponseModel {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Data data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
    public class Data {

        @SerializedName("product")
        @Expose
        private List<Product> product = null;

        public List<Product> getProduct() {
            return product;
        }

        public void setProduct(List<Product> product) {
            this.product = product;
        }
        public class Product {

            @SerializedName("product_id")
            @Expose
            private Integer productId;
            @SerializedName("product_name")
            @Expose
            private String productName;
            @SerializedName("product_price")
            @Expose
            private String productPrice;
            @SerializedName("product_regular_price")
            @Expose
            private String productRegularPrice;
            @SerializedName("product_sale_price")
            @Expose
            private String productSalePrice;
            @SerializedName("product_image")
            @Expose
            private String productImage;
            @SerializedName("currency")
            @Expose
            private String currency;
            @SerializedName("currency_symbol")
            @Expose
            private String currencySymbol;
            @SerializedName("rating_count")
            @Expose
            private Integer ratingCount;
            @SerializedName("review_count")
            @Expose
            private Integer reviewCount;
            @SerializedName("average")
            @Expose
            private String average;

            public Integer getProductId() {
                return productId;
            }

            public void setProductId(Integer productId) {
                this.productId = productId;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public String getProductPrice() {
                return productPrice;
            }

            public void setProductPrice(String productPrice) {
                this.productPrice = productPrice;
            }

            public String getProductRegularPrice() {
                return productRegularPrice;
            }

            public void setProductRegularPrice(String productRegularPrice) {
                this.productRegularPrice = productRegularPrice;
            }

            public String getProductSalePrice() {
                return productSalePrice;
            }

            public void setProductSalePrice(String productSalePrice) {
                this.productSalePrice = productSalePrice;
            }

            public String getProductImage() {
                return productImage;
            }

            public void setProductImage(String productImage) {
                this.productImage = productImage;
            }

            public String getCurrency() {
                return currency;
            }

            public void setCurrency(String currency) {
                this.currency = currency;
            }

            public String getCurrencySymbol() {
                return currencySymbol;
            }

            public void setCurrencySymbol(String currencySymbol) {
                this.currencySymbol = currencySymbol;
            }

            public Integer getRatingCount() {
                return ratingCount;
            }

            public void setRatingCount(Integer ratingCount) {
                this.ratingCount = ratingCount;
            }

            public Integer getReviewCount() {
                return reviewCount;
            }

            public void setReviewCount(Integer reviewCount) {
                this.reviewCount = reviewCount;
            }

            public String getAverage() {
                return average;
            }

            public void setAverage(String average) {
                this.average = average;
            }

        }
    }
}
