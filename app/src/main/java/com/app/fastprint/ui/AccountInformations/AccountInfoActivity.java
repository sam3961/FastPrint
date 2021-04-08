package com.app.fastprint.ui.AccountInformations;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.app.fastprint.R;
import com.app.fastprint.baseClass.BaseClass;
import com.app.fastprint.ui.editprofile.EditProfileActivity;
import com.app.fastprint.utills.AppControler;
import com.app.fastprint.utills.UtilsFontFamily;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class AccountInfoActivity extends BaseClass {

    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.tvTittle)
    TextView tvTittle;
    @BindView(R.id.imgUser)
    CircleImageView imgUser;
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.tvUserName)
    TextView tvUserName;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.tvphoneNumber)
    TextView tvphoneNumber;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.tvEmail)
    TextView tvEmail;

    @BindView(R.id.tvEditProfile)
    TextView tvEditProfile;

    String user_name="";
    String user_image="";
    String user_phone="";
    String user_email="";
    String user_first_name="";
    String user_last_name="";

    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_info);
        ButterKnife.bind(this);
        context=AccountInfoActivity.this;
        user_first_name= AppControler.getInstance(context).getString(AppControler.Key.USER_FIRST_NAME);
        user_last_name= AppControler.getInstance(context).getString(AppControler.Key.USER_LAST_NAME);
        user_name= AppControler.getInstance(context).getString(AppControler.Key.USER_NAME);
        user_image= AppControler.getInstance(context).getString(AppControler.Key.USER_IMAGE);
        user_phone= AppControler.getInstance(context).getString(AppControler.Key.USER_PHONE);
        user_email= AppControler.getInstance(context).getString(AppControler.Key.USER_EMAIL);

        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));
        username.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));
        phone.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));
        email.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));

        tvEditProfile.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));


        if (user_image != null) {
            Glide.with(context).load(user_image)
                    .placeholder(R.drawable.ic_refresh)
                    .into(imgUser);
        } else {
            Glide.with(context).load(user_image)
                    .placeholder(R.drawable.ic_refresh)
                    .into(imgUser);
        }

        tvUserName.setText(user_first_name+" "+user_last_name);
        tvphoneNumber.setText(user_phone);
        tvEmail.setText(user_email);

    }

    @OnClick({R.id.imgBack, R.id.tvEditProfile})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.tvEditProfile:
                Intent intent = new Intent(AccountInfoActivity.this, EditProfileActivity.class);
                startActivity(intent);
                break;
        }
    }
}