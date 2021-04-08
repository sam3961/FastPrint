package com.app.fastprint.ui.cart.responseModel;

public class CartListing {

    private String product_id;
    private String product_name;
    private String quantity ;
    private String cover_options ;
    private String page_including_cover ;
    private String amount ;
    private String discount_amount ;
    private String product_image ;
    private String total_rating ;
    private String currencySymbol ;
    private Float rating;
    private int variationId;

    public CartListing() {
    }

    public CartListing(String product_id,String product_name, String quantity, String cover_options, String page_including_cover,
                       String amount, String discount_amount,String product_image,String total_rating,
                       Float rating,int variationId,String currencySymbol) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.quantity = quantity;
        this.cover_options = cover_options;
        this.page_including_cover = page_including_cover;
        this.amount = amount;
        this.discount_amount = discount_amount;
        this.product_image = product_image;
        this.total_rating = total_rating;
        this.rating = rating;
        this.variationId = variationId;
        this.currencySymbol = currencySymbol;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getCover_options() {
        return cover_options;
    }

    public String getPage_including_cover() {
        return page_including_cover;
    }

    public String getAmount() {
        return amount;
    }

    public String getProduct_image() {
        return product_image;
    }

    public String getDiscount_amount() {
        return discount_amount;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getTotal_rating() {
        return total_rating;
    }

    public Float getRating() {
        return rating;
    }

    public int getVariationId() {
        return variationId;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }
}
