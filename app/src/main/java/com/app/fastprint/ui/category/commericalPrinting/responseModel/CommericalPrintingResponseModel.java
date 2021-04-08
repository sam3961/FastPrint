package com.app.fastprint.ui.category.commericalPrinting.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public
class CommericalPrintingResponseModel {

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

        @SerializedName("Commerical")
        @Expose
        private List<Commerical> commerical = null;

        public List<Commerical> getCommerical() {
            return commerical;
        }

        public void setCommerical(List<Commerical> commerical) {
            this.commerical = commerical;
        }
        public class Commerical {

            @SerializedName("job")
            @Expose
            private List<String> job = null;
            @SerializedName("jobs")
            @Expose
            private List<String> jobs = null;
            @SerializedName("no_of_sheets")
            @Expose
            private List<String> noOfSheets = null;
            @SerializedName("color_code")
            @Expose
            private List<String> colorCode = null;
            @SerializedName("number_of_colors")
            @Expose
            private List<String> numberOfColors = null;
            @SerializedName("orientation")
            @Expose
            private List<String> orientation = null;
            @SerializedName("size")
            @Expose
            private List<String> size = null;
            @SerializedName("side")
            @Expose
            private List<String> side = null;
            @SerializedName("finishing")
            @Expose
            private List<String> finishing = null;
            @SerializedName("paper_type")
            @Expose
            private List<String> paperType = null;
            @SerializedName("paper_gram")
            @Expose
            private List<String> paperGram = null;

            public List<String> getJob() {
                return job;
            }

            public void setJob(List<String> job) {
                this.job = job;
            }

            public List<String> getJobs() {
                return jobs;
            }

            public void setJobs(List<String> jobs) {
                this.jobs = jobs;
            }

            public List<String> getNoOfSheets() {
                return noOfSheets;
            }

            public void setNoOfSheets(List<String> noOfSheets) {
                this.noOfSheets = noOfSheets;
            }

            public List<String> getColorCode() {
                return colorCode;
            }

            public void setColorCode(List<String> colorCode) {
                this.colorCode = colorCode;
            }

            public List<String> getNumberOfColors() {
                return numberOfColors;
            }

            public void setNumberOfColors(List<String> numberOfColors) {
                this.numberOfColors = numberOfColors;
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

            public List<String> getSide() {
                return side;
            }

            public void setSide(List<String> side) {
                this.side = side;
            }

            public List<String> getFinishing() {
                return finishing;
            }

            public void setFinishing(List<String> finishing) {
                this.finishing = finishing;
            }

            public List<String> getPaperType() {
                return paperType;
            }

            public void setPaperType(List<String> paperType) {
                this.paperType = paperType;
            }

            public List<String> getPaperGram() {
                return paperGram;
            }

            public void setPaperGram(List<String> paperGram) {
                this.paperGram = paperGram;
            }

        }
    }
}
