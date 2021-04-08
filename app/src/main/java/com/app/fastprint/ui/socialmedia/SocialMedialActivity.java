package com.app.fastprint.ui.socialmedia;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fastprint.R;
import com.app.fastprint.baseClass.BaseClass;
import com.app.fastprint.networks.NetworkUtils;
import com.app.fastprint.ui.socialmedia.adapters.SocialMediaAdapter;
import com.app.fastprint.ui.socialmedia.interfaces.IPSocialMedia;
import com.app.fastprint.ui.socialmedia.interfaces.ISocialMedia;
import com.app.fastprint.ui.socialmedia.presenter.PSocialMedia;
import com.app.fastprint.ui.socialmedia.responseModel.SocialMediaResponseModel;
import com.app.fastprint.utills.UtilsAlertDialog;
import com.app.fastprint.utills.UtilsFontFamily;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SocialMedialActivity extends BaseClass implements ISocialMedia {

    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.tvTittle)
    TextView tvTittle;
    @BindView(R.id.RecyclerSocialMedia)
    RecyclerView RecyclerSocialMedia;
    SocialMediaAdapter socialMediaAdapter;
    Context context;
    Dialog dialog;
    IPSocialMedia ipSocialMedia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_medial);
        ButterKnife.bind(this);
        context = SocialMedialActivity.this;
        ipSocialMedia=new PSocialMedia(this);
        if (NetworkUtils.isNetworkConnectionAvailable(context)) {
            dialog = UtilsAlertDialog.ShowDialog(context);
            ipSocialMedia.getSocialMedia();
        }
        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));
    }

    @OnClick(R.id.imgBack)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void successResponseFromPresenters(SocialMediaResponseModel socialMediaResponseModel) {
        dialog.dismiss();
        if (socialMediaResponseModel!=null){
            updateListUI(socialMediaResponseModel);
        }
    }

    private void updateListUI(SocialMediaResponseModel socialMediaResponseModel) {
        socialMediaAdapter = new SocialMediaAdapter(this, socialMediaResponseModel.getData().getSocial());
        RecyclerSocialMedia.setLayoutManager(new LinearLayoutManager(context));
        RecyclerSocialMedia.setAdapter(socialMediaAdapter);
    }

    @Override
    public void errorResponseFromPresenters(String message) {
        dialog.dismiss();
        String error_message = message;
        Toast.makeText(context, "" + error_message, Toast.LENGTH_SHORT).show();
    }
}
