package com.app.fastprint.ui.category.multipagePrinting.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public
class MultiPagePrintingResponseModel {
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

        @SerializedName("multi_page")
        @Expose
        private List<MultiPage> multiPage = null;

        public List<MultiPage> getMultiPage() {
            return multiPage;
        }

        public void setMultiPage(List<MultiPage> multiPage) {
            this.multiPage = multiPage;
        }
        public class MultiPage {

            @SerializedName("job")
            @Expose
            private List<String> job = null;
            @SerializedName("color_code")
            @Expose
            private List<String> colorCode = null;
            @SerializedName("numer_of_colors")
            @Expose
            private List<String> numerOfColors = null;
            @SerializedName("orientation")
            @Expose
            private List<String> orientation = null;
            @SerializedName("size")
            @Expose
            private List<String> size = null;
            @SerializedName("sides")
            @Expose
            private List<String> sides = null;
            @SerializedName("cover_ad_ons")
            @Expose
            private List<String> coverAdOns = null;
            @SerializedName("binding")
            @Expose
            private List<String> binding = null;
            @SerializedName("cover_page_type")
            @Expose
            private List<String> coverPageType = null;
            @SerializedName("inside_page_type")
            @Expose
            private List<String> insidePageType = null;
            @SerializedName("cover_page_gram")
            @Expose
            private List<String> coverPageGram = null;
            @SerializedName("inside_page_gram")
            @Expose
            private List<String> insidePageGram = null;

            public List<String> getJob() {
                return job;
            }

            public void setJob(List<String> job) {
                this.job = job;
            }

            public List<String> getColorCode() {
                return colorCode;
            }

            public void setColorCode(List<String> colorCode) {
                this.colorCode = colorCode;
            }

            public List<String> getNumerOfColors() {
                return numerOfColors;
            }

            public void setNumerOfColors(List<String> numerOfColors) {
                this.numerOfColors = numerOfColors;
            }

            public List<String> getOrientation() {
                return orientation;
            }

            public void setOrientation(List<String> orientation) {
                this.orientation = orientation;
            }

            public List<String> getSize() {
                return size;
            }

            public void setSize(List<String> size) {
                this.size = size;
            }

            public List<String> getSides() {
                return sides;
            }

            public void setSides(List<String> sides) {
                this.sides = sides;
            }

            public List<String> getCoverAdOns() {
                return coverAdOns;
            }

            public void setCoverAdOns(List<String> coverAdOns) {
                this.coverAdOns = coverAdOns;
            }

            public List<String> getBinding() {
                return binding;
            }

            public void setBinding(List<String> binding) {
                this.binding = binding;
            }

            public List<String> getCoverPageType() {
                return coverPageType;
            }

            public void setCoverPageType(List<String> coverPageType) {
                this.coverPageType = coverPageType;
            }

            public List<String> getInsidePageType() {
                return insidePageType;
            }

            public void setInsidePageType(List<String> insidePageType) {
                this.insidePageType = insidePageType;
            }

            public List<String> getCoverPageGram() {
                return coverPageGram;
            }

            public void setCoverPageGram(List<String> coverPageGram) {
                this.coverPageGram = coverPageGram;
            }

            public List<String> getInsidePageGram() {
                return insidePageGram;
            }

            public void setInsidePageGram(List<String> insidePageGram) {
                this.insidePageGram = insidePageGram;
            }

        }
    }
}
