package com.app.fastprint.ui.category.services.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public
class ServicesResponseModel {

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

        @SerializedName("pre_press")
        @Expose
        private String prePress;
        @SerializedName("post")
        @Expose
        private String post;
        @SerializedName("post_press")
        @Expose
        private String postPress;
        @SerializedName("image")
        @Expose
        private String image;

        public String getPrePress() {
            return prePress;
        }

        public void setPrePress(String prePress) {
            this.prePress = prePress;
        }

        public String getPost() {
            return post;
        }

        public void setPost(String post) {
            this.post = post;
        }

        public String getPostPress() {
            return postPress;
        }

        public void setPostPress(String postPress) {
            this.postPress = postPress;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

    }
}
