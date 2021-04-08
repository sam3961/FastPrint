package com.app.fastprint.ui.category.logo;

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
import com.app.fastprint.ui.category.logo.interfaces.IDefinationofLogo;
import com.app.fastprint.ui.category.logo.interfaces.IPDefinationofLogo;
import com.app.fastprint.ui.category.logo.presenters.PDefinationofLogo;
import com.app.fastprint.ui.category.logo.responseModel.DefinationOfLogoResponseModel;
import com.app.fastprint.utills.UtilsAlertDialog;
import com.app.fastprint.utills.UtilsFontFamily;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DefinationofLogoActivity extends BaseClass implements IDefinationofLogo {

    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.tvTittle)
    TextView tvTittle;
    @BindView(R.id.textViewTitle)
    TextView textViewTitle;
    @BindView(R.id.imgDefination)
    ImageView imgDefination;

    @BindView(R.id.layoutDefinactionofLogo)
    RelativeLayout layoutDefinactionofLogo;

    Context context;
    Dialog dialog;
    IPDefinationofLogo ipDefinationofLogo;
    @BindView(R.id.tvlogos)
    JustifiedTextView tvlogos;
    @BindView(R.id.tvColor)
    JustifiedTextView tvColor;
    @BindView(R.id.tvFont)
    JustifiedTextView tvFont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definationof_logo);
        context = DefinationofLogoActivity.this;
        ButterKnife.bind(this);
        ipDefinationofLogo = new PDefinationofLogo(this);
        layoutDefinactionofLogo.setVisibility(View.GONE);
        if (NetworkUtils.isNetworkConnectionAvailable(context)) {
            dialog = UtilsAlertDialog.ShowDialog(context);
            ipDefinationofLogo.getDefinationOfLogo();
        }
        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));
        textViewTitle.setTypeface(UtilsFontFamily.typeFaceForRobotoBold(this));

    }

    @OnClick(R.id.imgBack)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void successResponseFromPresenter(DefinationOfLogoResponseModel definationOfLogoResponseModel) {
        dialog.dismiss();
        if (definationOfLogoResponseModel != null) {
            layoutDefinactionofLogo.setVisibility(View.VISIBLE);
            tvColor.setText(Html.fromHtml(definationOfLogoResponseModel.getData().getColor()));
            tvFont.setText(Html.fromHtml(definationOfLogoResponseModel.getData().getFont()));
            tvlogos.setText(Html.fromHtml(definationOfLogoResponseModel.getData().getLogo()));
            if (definationOfLogoResponseModel.getData().getImage() != null) {
                Glide.with(context).load(definationOfLogoResponseModel.getData().getImage())
                        .placeholder(R.drawable.ic_refresh)
                        .into(imgDefination);
            } else {
                Glide.with(context).load(definationOfLogoResponseModel.getData().getImage())
                        .placeholder(R.drawable.ic_refresh)
                        .into(imgDefination);
            }
        }
    }

    @Override
    public void errorResponseFromPresenter(String message) {
        dialog.dismiss();
        layoutDefinactionofLogo.setVisibility(View.GONE);
        String error_message = message;
        Toast.makeText(context, "" + error_message, Toast.LENGTH_SHORT).show();
    }
}
