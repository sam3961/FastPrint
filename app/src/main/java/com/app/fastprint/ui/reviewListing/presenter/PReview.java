package com.app.fastprint.ui.reviewListing.presenter;

import com.app.fastprint.ui.reviewListing.ReviewListingActivity;
import com.app.fastprint.ui.reviewListing.interfaces.IMReview;
import com.app.fastprint.ui.reviewListing.interfaces.IPReview;
import com.app.fastprint.ui.reviewListing.interfaces.IReview;
import com.app.fastprint.ui.reviewListing.responseModel.ReviewResponseModel;
import com.app.fastprint.ui.reviewListing.viewModel.MReview;

public class PReview implements IPReview {
    IReview iReview;
    IMReview imReview;
    public PReview(ReviewListingActivity reviewListingActivity) {
        this.iReview=reviewListingActivity;

    }

    @Override
    public void getReviews(String id) {
        imReview=new MReview(this);
        imReview.getReviewsRestCalls(id);
    }

    @Override
    public void sucessResponseFromModel(ReviewResponseModel reviewResponseModel) {
        iReview.sucessResponseFromPresenter(reviewResponseModel);
    }

    @Override
    public void errorResponseFromModel(String message) {
        iReview.errorResponseFromPresenter(message);
    }
}
