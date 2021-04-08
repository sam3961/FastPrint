package com.app.fastprint.ui.productDetails.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public
class ProductDetailsResponseModel {
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

        @SerializedName("product_detail")
        @Expose
        private List<ProductDetail> productDetail = null;
        @SerializedName("product_images")
        @Expose
        private List<String> productImages = null;
        @SerializedName("quantity")
        @Expose
        private List<String> quantity = null;
        @SerializedName("pages")
        @Expose
        private List<String> pages = null;
        @SerializedName("cover")
        @Expose
        private List<String> cover = null;
        @SerializedName("similar")
        @Expose
        private List<Similar> similar = null;

        public List<ProductDetail> getProductDetail() {
            return productDetail;
        }

        public void setProductDetail(List<ProductDetail> productDetail) {
            this.productDetail = productDetail;
        }

        public List<String> getProductImages() {
            return productImages;
        }

        public void setProductImages(List<String> productImages) {
            this.productImages = productImages;
        }

        public List<String> getQuantity() {
            return quantity;
        }

        public void setQuantity(List<String> quantity) {
            this.quantity = quantity;
        }

        public List<String> getPages() {
            return pages;
        }

        public void setPages(List<String> pages) {
            this.pages = pages;
        }

        public List<String> getCover() {
            return cover;
        }

        public void setCover(List<String> cover) {
            this.cover = cover;
        }

        public List<Similar> getSimilar() {
            return similar;
        }

        public void setSimilar(List<Similar> similar) {
            this.similar = similar;
        }
        public class ProductDetail {

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
            @SerializedName("description")
            @Expose
            private String description;
            @SerializedName("short_description")
            @Expose
            private String shortDescription;
            @SerializedName("currency")
            @Expose
            private String currency;
            @SerializedName("min_price")
            @Expose
            private String min_price;
            @SerializedName("max_price")
            @Expose
            private String max_price;
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

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getShortDescription() {
                return shortDescription;
            }

            public void setShortDescription(String shortDescription) {
                this.shortDescription = shortDescription;
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

            public String getMin_price() {
                return min_price;
            }

            public void setMin_price(String min_price) {
                this.min_price = min_price;
            }

            public String getMax_price() {
                return max_price;
            }

            public void setMax_price(String max_price) {
                this.max_price = max_price;
            }
        }


        public class Similar {

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
