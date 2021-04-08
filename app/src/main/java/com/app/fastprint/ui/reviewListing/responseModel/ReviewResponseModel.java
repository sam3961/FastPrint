package com.app.fastprint.ui.reviewListing.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public
class ReviewResponseModel {
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

        @SerializedName("Review")
        @Expose
        private List<Review> review = null;

        public List<Review> getReview() {
            return review;
        }

        public void setReview(List<Review> review) {
            this.review = review;
        }
        public class Review {

            @SerializedName("user_id")
            @Expose
            private String userId;
            @SerializedName("comment_ID")
            @Expose
            private String commentID;
            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("email")
            @Expose
            private String email;
            @SerializedName("date")
            @Expose
            private Integer date;
            @SerializedName("comment")
            @Expose
            private String comment;

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getCommentID() {
                return commentID;
            }

            public void setCommentID(String commentID) {
                this.commentID = commentID;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public Integer getDate() {
                return date;
            }

            public void setDate(Integer date) {
                this.date = date;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }
        }
    }
}
