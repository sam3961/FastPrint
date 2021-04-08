package com.app.fastprint.ui.reviewListing;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fastprint.R;
import com.app.fastprint.baseClass.BaseClass;
import com.app.fastprint.networks.NetworkUtils;
import com.app.fastprint.ui.reviewListing.adapter.ReviewAdapter;
import com.app.fastprint.ui.reviewListing.interfaces.IPReview;
import com.app.fastprint.ui.reviewListing.interfaces.IReview;
import com.app.fastprint.ui.reviewListing.presenter.PReview;
import com.app.fastprint.ui.reviewListing.responseModel.ReviewResponseModel;
import com.app.fastprint.utills.UtilsAlertDialog;
import com.app.fastprint.utills.UtilsFontFamily;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReviewListingActivity extends BaseClass implements IReview {

    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.imgNoRecordFound)
    ImageView imgNoRecordFound;
    @BindView(R.id.tvTittle)
    TextView tvTittle;
    @BindView(R.id.recyclerReviews)
    RecyclerView recyclerReviews;
    Context context;
    ReviewAdapter reviewAdapter;
    Dialog dialog;
    IPReview ipReview;
    String product_id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_listing);
        ButterKnife.bind(this);
        product_id = getIntent().getStringExtra("product_id");
        context = ReviewListingActivity.this;
        ipReview = new PReview(this);
        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));
        if (NetworkUtils.isNetworkConnectionAvailable(context)) {
            dialog = UtilsAlertDialog.ShowDialog(context);
            ipReview.getReviews(product_id);
        }

    }

    @OnClick(R.id.imgBack)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void sucessResponseFromPresenter(ReviewResponseModel reviewResponseModel) {
        dialog.dismiss();
        if (reviewResponseModel.getData() != null && reviewResponseModel.getData().getReview().size() > 0) {
            imgNoRecordFound.setVisibility(View.GONE);
            reviewAdapter = new ReviewAdapter(context, reviewResponseModel.getData().getReview());
            recyclerReviews.setLayoutManager(new LinearLayoutManager(this));
            recyclerReviews.setAdapter(reviewAdapter);
        }else {
            imgNoRecordFound.setVisibility(View.VISIBLE);
        }
        Toast.makeText(context, "" + reviewResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void errorResponseFromPresenter(String message) {
        dialog.dismiss();
        imgNoRecordFound.setVisibility(View.VISIBLE);
        Toast.makeText(context, "" + message, Toast.LENGTH_SHORT).show();
    }
}