package com.app.fastprint.ui.reviewListing.interfaces;

import com.app.fastprint.ui.reviewListing.responseModel.ReviewResponseModel;

public
interface IPReview {
    void getReviews(String id);
    void sucessResponseFromModel(ReviewResponseModel reviewResponseModel);
    void errorResponseFromModel(String message);
}
