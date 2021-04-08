package com.app.fastprint.ui.uploadfile.responnseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public
class UploadFileResponseModel {
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

        @SerializedName("uploadFile")
        @Expose
        private List<UploadFile> uploadFile = null;

        public List<UploadFile> getUploadFile() {
            return uploadFile;
        }

        public void setUploadFile(List<UploadFile> uploadFile) {
            this.uploadFile = uploadFile;
        }
        public class UploadFile {

            @SerializedName("id")
            @Expose
            private String id;
            @SerializedName("image")
            @Expose
            private String image;
            @SerializedName("name")
            @Expose
            private String name;

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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

        }
    }

}
