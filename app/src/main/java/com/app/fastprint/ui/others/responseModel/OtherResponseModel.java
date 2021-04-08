package com.app.fastprint.ui.others.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public
class OtherResponseModel {

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

        @SerializedName("privacy_policy")
        @Expose
        private String privacyPolicy;
        @SerializedName("refund_policy")
        @Expose
        private String refundPolicy;
        @SerializedName("terms_and_conditions")
        @Expose
        private String termsAndConditions;

        public String getPrivacyPolicy() {
            return privacyPolicy;
        }

        public void setPrivacyPolicy(String privacyPolicy) {
            this.privacyPolicy = privacyPolicy;
        }

        public String getRefundPolicy() {
            return refundPolicy;
        }

        public void setRefundPolicy(String refundPolicy) {
            this.refundPolicy = refundPolicy;
        }

        public String getTermsAndConditions() {
            return termsAndConditions;
        }

        public void setTermsAndConditions(String termsAndConditions) {
            this.termsAndConditions = termsAndConditions;
        }

    }
}
