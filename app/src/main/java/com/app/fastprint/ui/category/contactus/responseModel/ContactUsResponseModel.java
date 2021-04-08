package com.app.fastprint.ui.category.contactus.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public
class ContactUsResponseModel {


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

        @SerializedName("fast_print")
        @Expose
        private String fastPrint;
        @SerializedName("phone_number")
        @Expose
        private String phoneNumber;
        @SerializedName("fax")
        @Expose
        private String fax;
        @SerializedName("mobile")
        @Expose
        private String mobile;
        @SerializedName("whatsapp")
        @Expose
        private String whatsapp;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("skype")
        @Expose
        private String skype;
        @SerializedName("al_fuhays_jo")
        @Expose
        private String alFuhaysJo;
        @SerializedName("website")
        @Expose
        private String website;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("latitude")
        @Expose
        private String latitude;
        @SerializedName("longitude")
        @Expose
        private String longitude;

        public String getFastPrint() {
            return fastPrint;
        }

        public void setFastPrint(String fastPrint) {
            this.fastPrint = fastPrint;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getFax() {
            return fax;
        }

        public void setFax(String fax) {
            this.fax = fax;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getWhatsapp() {
            return whatsapp;
        }

        public void setWhatsapp(String whatsapp) {
            this.whatsapp = whatsapp;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getSkype() {
            return skype;
        }

        public void setSkype(String skype) {
            this.skype = skype;
        }

        public String getAlFuhaysJo() {
            return alFuhaysJo;
        }

        public void setAlFuhaysJo(String alFuhaysJo) {
            this.alFuhaysJo = alFuhaysJo;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

    }
}
