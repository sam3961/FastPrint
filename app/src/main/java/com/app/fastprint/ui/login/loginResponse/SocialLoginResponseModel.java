package com.app.fastprint.ui.login.loginResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public
class SocialLoginResponseModel {
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

        @SerializedName("user_id")
        @Expose
        private Integer userId;
        @SerializedName("user_name")
        @Expose
        private String userName;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("phone_number")
        @Expose
        private String phoneNumber;
        @SerializedName("first_name")
        @Expose
        private String firstName;
        @SerializedName("last_name")
        @Expose
        private String lastName;
        @SerializedName("user_image")
        @Expose
        private String userImage;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("auth_token")
        @Expose
        private String authToken;
        @SerializedName("current_lattitude")
        @Expose
        private String currentLattitude;
        @SerializedName("current_longitude")
        @Expose
        private String currentLongitude;
        @SerializedName("login_type")
        @Expose
        private String loginType;
        @SerializedName("consumer_key")
        @Expose
        private String consumerKey;
        @SerializedName("secret_key")
        @Expose
        private String secretKey;

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getUserImage() {
            return userImage;
        }

        public void setUserImage(String userImage) {
            this.userImage = userImage;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAuthToken() {
            return authToken;
        }

        public void setAuthToken(String authToken) {
            this.authToken = authToken;
        }

        public String getCurrentLattitude() {
            return currentLattitude;
        }

        public void setCurrentLattitude(String currentLattitude) {
            this.currentLattitude = currentLattitude;
        }

        public String getCurrentLongitude() {
            return currentLongitude;
        }

        public void setCurrentLongitude(String currentLongitude) {
            this.currentLongitude = currentLongitude;
        }

        public String getLoginType() {
            return loginType;
        }

        public void setLoginType(String loginType) {
            this.loginType = loginType;
        }

        public String getConsumerKey() {
            return consumerKey;
        }

        public void setConsumerKey(String consumerKey) {
            this.consumerKey = consumerKey;
        }

        public String getSecretKey() {
            return secretKey;
        }

        public void setSecretKey(String secretKey) {
            this.secretKey = secretKey;
        }
    }
}
