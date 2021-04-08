package com.app.fastprint.ui.main.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MenuListResponseModel {
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

        @SerializedName("menu")
        @Expose
        private List<Menu> menu = null;
        @SerializedName("banner")
        @Expose
        private List<Banner> banner = null;

        public List<Menu> getMenu() {
            return menu;
        }

        public void setMenu(List<Menu> menu) {
            this.menu = menu;
        }

        public List<Banner> getBanner() {
            return banner;
        }

        public void setBanner(List<Banner> banner) {
            this.banner = banner;
        }
        public class Menu {

            @SerializedName("id")
            @Expose
            private String id;
            @SerializedName("image")
            @Expose
            private String image;
            @SerializedName("title")
            @Expose
            private String title;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

        public class Banner {

            @SerializedName("title")
            @Expose
            private String title;
            @SerializedName("image")
            @Expose
            private String image;
            @SerializedName("id")
            @Expose
            private String id;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

        }
    }
}
