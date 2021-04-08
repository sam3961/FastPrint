package com.app.fastprint.ui.category.aboutus.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public
class AboutUsResponseModel {
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

        @SerializedName("about_us")
        @Expose
        private AboutUs aboutUs;

        public AboutUs getAboutUs() {
            return aboutUs;
        }

        public void setAboutUs(AboutUs aboutUs) {
            this.aboutUs = aboutUs;
        }
        public class AboutUs {

            @SerializedName("heading")
            @Expose
            private String heading;
            @SerializedName("director")
            @Expose
            private String director;
            @SerializedName("deputy_director")
            @Expose
            private String deputyDirector;
            @SerializedName("founded")
            @Expose
            private String founded;
            @SerializedName("our_misssion")
            @Expose
            private String ourMisssion;
            @SerializedName("our_vision")
            @Expose
            private String ourVision;
            @SerializedName("our_promise")
            @Expose
            private String ourPromise;
            @SerializedName("our_products")
            @Expose
            private String ourProducts;
            @SerializedName("lingdin_image")
            @Expose
            private String lingdinImage;
            @SerializedName("image_aboutus")
            @Expose
            private String imageAboutus;

            public String getHeading() {
                return heading;
            }

            public void setHeading(String heading) {
                this.heading = heading;
            }

            public String getDirector() {
                return director;
            }

            public void setDirector(String director) {
                this.director = director;
            }

            public String getDeputyDirector() {
                return deputyDirector;
            }

            public void setDeputyDirector(String deputyDirector) {
                this.deputyDirector = deputyDirector;
            }

            public String getFounded() {
                return founded;
            }

            public void setFounded(String founded) {
                this.founded = founded;
            }

            public String getOurMisssion() {
                return ourMisssion;
            }

            public void setOurMisssion(String ourMisssion) {
                this.ourMisssion = ourMisssion;
            }

            public String getOurVision() {
                return ourVision;
            }

            public void setOurVision(String ourVision) {
                this.ourVision = ourVision;
            }

            public String getOurPromise() {
                return ourPromise;
            }

            public void setOurPromise(String ourPromise) {
                this.ourPromise = ourPromise;
            }

            public String getOurProducts() {
                return ourProducts;
            }

            public void setOurProducts(String ourProducts) {
                this.ourProducts = ourProducts;
            }

            public String getLingdinImage() {
                return lingdinImage;
            }

            public void setLingdinImage(String lingdinImage) {
                this.lingdinImage = lingdinImage;
            }

            public String getImageAboutus() {
                return imageAboutus;
            }

            public void setImageAboutus(String imageAboutus) {
                this.imageAboutus = imageAboutus;
            }

        }
    }
}
