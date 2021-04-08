package com.app.fastprint.ui.category.commericalPrintingList.responseModel;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public
class CommercialPrintingCategoriesResponseModel {

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

        @SerializedName("commerical_category")
        @Expose
        private List<CommericalCategory> commericalCategory = null;

        public List<CommericalCategory> getCommericalCategory() {
            return commericalCategory;
        }

        public void setCommericalCategory(List<CommericalCategory> commericalCategory) {
            this.commericalCategory = commericalCategory;
        }
        public class CommericalCategory {

            @SerializedName("form_id")
            @Expose
            private String formId;
            @SerializedName("image")
            @Expose
            private String image;
            @SerializedName("title")
            @Expose
            private String title;

            public String getFormId() {
                return formId;
            }

            public void setFormId(String formId) {
                this.formId = formId;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

        }
    }


}
