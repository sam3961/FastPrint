package com.app.fastprint.ui.product.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public
class StoreListingResponseModel {
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

        @SerializedName("StoreCategory")
        @Expose
        private List<StoreCategory> storeCategory = null;

        public List<StoreCategory> getStoreCategory() {
            return storeCategory;
        }

        public void setStoreCategory(List<StoreCategory> storeCategory) {
            this.storeCategory = storeCategory;
        }
        public class StoreCategory {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("slug")
            @Expose
            private String slug;
            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("subCategory")
            @Expose
            private List<SubCategory> subCategory = null;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getSlug() {
                return slug;
            }

            public void setSlug(String slug) {
                this.slug = slug;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<SubCategory> getSubCategory() {
                return subCategory;
            }

            public void setSubCategory(List<SubCategory> subCategory) {
                this.subCategory = subCategory;
            }
            public class SubCategory {

                @SerializedName("id")
                @Expose
                private Integer id;
                @SerializedName("slug")
                @Expose
                private String slug;
                @SerializedName("name")
                @Expose
                private String name;

                public Integer getId() {
                    return id;
                }

                public void setId(Integer id) {
                    this.id = id;
                }

                public String getSlug() {
                    return slug;
                }

                public void setSlug(String slug) {
                    this.slug = slug;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

            }
        }
    }
}
