package com.app.fastprint.ui.category.aboutus;

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
import com.app.fastprint.ui.category.aboutus.interfaces.IAboutus;
import com.app.fastprint.ui.category.aboutus.interfaces.IPAboutus;
import com.app.fastprint.ui.category.aboutus.presenters.PAboutus;
import com.app.fastprint.ui.category.aboutus.responseModel.AboutUsResponseModel;
import com.app.fastprint.utills.UtilsAlertDialog;
import com.app.fastprint.utills.UtilsFontFamily;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutusActivity extends BaseClass implements IAboutus {

    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.imgAboutus)
    ImageView imgAboutus;
    @BindView(R.id.tvTittle)
    TextView tvTittle;
    @BindView(R.id.tvHeading)
    TextView tvHeading;

    IPAboutus ipAboutus;
    Dialog dialog;
    Context context;
    @BindView(R.id.director)
    TextView director;
    @BindView(R.id.tvDirector)
    JustifiedTextView tvDirector;

    @BindView(R.id.deputydirector)
    TextView deputydirector;
    @BindView(R.id.tvDeputyDirector)
    JustifiedTextView tvDeputyDirector;
    @BindView(R.id.imgLinkden)
    ImageView imgLinkden;
    @BindView(R.id.founded)
    TextView founded;
    @BindView(R.id.tvfounded)
    TextView tvfounded;
    @BindView(R.id.ourMission)
    TextView ourMission;
    @BindView(R.id.tvourMission)
    JustifiedTextView tvourMission;
    @BindView(R.id.ourVision)
    TextView ourVision;
    @BindView(R.id.tvourVision)
    JustifiedTextView tvourVision;
    @BindView(R.id.ourPromise)
    TextView ourPromise;
    @BindView(R.id.tvourPromise)
    JustifiedTextView tvourPromise;
    @BindView(R.id.ourProduct)
    TextView ourProduct;
    @BindView(R.id.tvourProduct)
    JustifiedTextView tvourProduct;
    @BindView(R.id.layoutRelative)
    RelativeLayout layoutRelative;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        context = AboutusActivity.this;
        ButterKnife.bind(this);
        ipAboutus = new PAboutus(this);
        layoutRelative.setVisibility(View.GONE);
        if (NetworkUtils.isNetworkConnectionAvailable(context)) {
            dialog = UtilsAlertDialog.ShowDialog(context);
            ipAboutus.getAboutUs();
        }
        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));
        director.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));
        deputydirector.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));
        founded.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));
        ourMission.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));
        ourVision.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));
        ourPromise.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));
        ourProduct.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));
        tvfounded.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));

    }


    @Override
    public void successResponseFromPresenter(AboutUsResponseModel aboutUsResponseModel) {
        dialog.dismiss();
        layoutRelative.setVisibility(View.VISIBLE);
        if (aboutUsResponseModel != null) {
            tvHeading.setText(Html.fromHtml(aboutUsResponseModel.getData().getAboutUs().getHeading()));
            tvDirector.setText(Html.fromHtml(aboutUsResponseModel.getData().getAboutUs().getDirector()));
            tvDeputyDirector.setText(Html.fromHtml(aboutUsResponseModel.getData().getAboutUs().getDeputyDirector()));
            tvfounded.setText(Html.fromHtml(aboutUsResponseModel.getData().getAboutUs().getFounded()));
            tvourMission.setText(Html.fromHtml(aboutUsResponseModel.getData().getAboutUs().getOurMisssion()));
            tvourVision.setText(Html.fromHtml(aboutUsResponseModel.getData().getAboutUs().getOurVision()));
            tvourPromise.setText(Html.fromHtml(aboutUsResponseModel.getData().getAboutUs().getOurPromise()));
            tvourProduct.setText(Html.fromHtml(aboutUsResponseModel.getData().getAboutUs().getOurProducts()));

            if (aboutUsResponseModel.getData().getAboutUs().getHeading() != null) {
                Glide.with(context).load(aboutUsResponseModel.getData().getAboutUs().getLingdinImage())
                        .placeholder(R.drawable.ic_linkedin)
                        .into(imgLinkden);
            } else {
                Glide.with(context).load(aboutUsResponseModel.getData().getAboutUs().getLingdinImage())
                        .placeholder(R.drawable.ic_linkedin)
                        .into(imgLinkden);
            }


            if (aboutUsResponseModel.getData().getAboutUs().getHeading() != null) {
                Glide.with(context).load(aboutUsResponseModel.getData().getAboutUs().getImageAboutus())
                        .placeholder(R.drawable.ic_refresh)
                        .into(imgAboutus);
            } else {
                Glide.with(context).load(aboutUsResponseModel.getData().getAboutUs().getImageAboutus())
                        .placeholder(R.drawable.ic_refresh)
                        .into(imgAboutus);
            }
        }
    }

    @Override
    public void errorResponseFromPresenter(String message) {
        dialog.dismiss();
        layoutRelative.setVisibility(View.GONE);
        String error_message = message;
        Toast.makeText(context, "" + error_message, Toast.LENGTH_SHORT).show();
    }


    @OnClick({R.id.imgBack, R.id.imgLinkden})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.imgLinkden:
                Toast.makeText(context, "Comming soon", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
