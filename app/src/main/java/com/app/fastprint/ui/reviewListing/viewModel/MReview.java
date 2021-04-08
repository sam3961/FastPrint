package com.app.fastprint.ui.reviewListing.viewModel;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.app.fastprint.services.APIInterface;
import com.app.fastprint.services.RetrofitCalls;
import com.app.fastprint.ui.reviewListing.interfaces.IMReview;
import com.app.fastprint.ui.reviewListing.interfaces.IPReview;
import com.app.fastprint.ui.reviewListing.presenter.PReview;
import com.app.fastprint.ui.reviewListing.responseModel.ReviewResponseModel;

public
class MReview implements IMReview {
    IPReview ipReview;
    public MReview(PReview pReview) {
        this.ipReview=pReview;
    }

    @Override
    public void getReviewsRestCalls(String id) {
        RetrofitCalls retrofitCalls=new RetrofitCalls();
        retrofitCalls.reviewApi(id,mHandler);
    }
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case APIInterface.REVIEW_SUCCESS:
                    ReviewResponseModel reviewResponseModel = ((ReviewResponseModel) msg.obj);
                    ipReview.sucessResponseFromModel(reviewResponseModel);
                    break;
                case APIInterface.REVIEW_FAILED:
                    String invalidRequest = String.valueOf(msg.obj);
                    ipReview.errorResponseFromModel(invalidRequest);
                    break;

            }
        }
    };
}
