package com.app.fastprint.ui.reviewListing.interfaces;

import com.app.fastprint.ui.reviewListing.responseModel.ReviewResponseModel;

public
interface IReview {
    void sucessResponseFromPresenter(ReviewResponseModel reviewResponseModel);
    void errorResponseFromPresenter(String message);
}
