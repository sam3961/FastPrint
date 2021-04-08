package com.app.fastprint.ui.category.services;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.codesgood.views.JustifiedTextView;
import com.app.fastprint.R;
import com.app.fastprint.baseClass.BaseClass;
import com.app.fastprint.networks.NetworkUtils;
import com.app.fastprint.ui.category.services.interfaces.IPServices;
import com.app.fastprint.ui.category.services.interfaces.IServices;
import com.app.fastprint.ui.category.services.presenters.PServices;
import com.app.fastprint.ui.category.services.responseModel.ServicesResponseModel;
import com.app.fastprint.utills.UtilsAlertDialog;
import com.app.fastprint.utills.UtilsFontFamily;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ServicesActivity extends BaseClass implements IServices {

    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.tvTittle)
    TextView tvTittle;
    @BindView(R.id.tvServices)
    TextView tvServices;
    @BindView(R.id.imgServices)
    ImageView imgServices;
    @BindView(R.id.layoutServices)
    RelativeLayout layoutServices;
    Dialog dialog;
    Context context;
    IPServices ipServices;
    @BindView(R.id.tvPrePress)
    JustifiedTextView tvPrePress;
    @BindView(R.id.tvPost)
    JustifiedTextView tvPost;
    @BindView(R.id.tvPrePost)
    JustifiedTextView tvPrePost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        context = ServicesActivity.this;
        ButterKnife.bind(this);
        ipServices = new PServices(this);
        layoutServices.setVisibility(View.GONE);
        if (NetworkUtils.isNetworkConnectionAvailable(context)) {
            dialog = UtilsAlertDialog.ShowDialog(context);
            ipServices.getServices();
        }
        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));
        tvServices.setTypeface(UtilsFontFamily.typeFaceForRobotoBold(this));


    }

    @OnClick(R.id.imgBack)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void successResponseFromPresenter(ServicesResponseModel servicesResponseModel) {
        dialog.dismiss();
        if (servicesResponseModel != null) {
            layoutServices.setVisibility(View.VISIBLE);
            tvPrePress.setText(Html.fromHtml(servicesResponseModel.getData().getPrePress()));
            tvPost.setText(Html.fromHtml(servicesResponseModel.getData().getPost()));
            tvPrePost.setText(Html.fromHtml(servicesResponseModel.getData().getPostPress()));
            if (servicesResponseModel.getData().getImage() != null) {
                Glide.with(context).load(servicesResponseModel.getData().getImage())
                        .placeholder(R.drawable.ic_refresh)
                        .into(imgServices);
            } else {
                Glide.with(context).load(servicesResponseModel.getData().getImage())
                        .placeholder(R.drawable.ic_refresh)
                        .into(imgServices);
            }
        }
    }

    @Override
    public void errorResponseFromPresenter(String message) {
        dialog.dismiss();
        layoutServices.setVisibility(View.GONE);
        String error_message = message;
        Toast.makeText(context, "" + error_message, Toast.LENGTH_SHORT).show();
    }
}
