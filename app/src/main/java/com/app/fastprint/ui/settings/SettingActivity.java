package com.app.fastprint.ui.settings;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.facebook.login.LoginManager;
import com.app.fastprint.R;
import com.app.fastprint.baseClass.BaseClass;
import com.app.fastprint.networks.NetworkUtils;
import com.app.fastprint.ui.AccountInformations.AccountInfoActivity;
import com.app.fastprint.ui.changepassword.ChangePasswordActivity;
import com.app.fastprint.ui.main.MainActivity;
import com.app.fastprint.ui.myorders.MyOrdersActivity;
import com.app.fastprint.ui.notification.NotificationActivity;
import com.app.fastprint.ui.others.OthersActivity;
import com.app.fastprint.ui.settings.adapter.SettingsAdapter;
import com.app.fastprint.ui.settings.interfaces.IPSetting;
import com.app.fastprint.ui.settings.interfaces.ISetting;
import com.app.fastprint.ui.settings.presenter.PSetting;
import com.app.fastprint.ui.settings.responseModel.LogoutResponseModel;
import com.app.fastprint.utills.AppControler;
import com.app.fastprint.utills.UtilsAlertDialog;
import com.app.fastprint.utills.UtilsFontFamily;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.testing.FakeReviewManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends BaseClass implements ISetting {
    Context context;
    SettingsAdapter settingsAdapter;
    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.tvTittle)
    TextView tvTittle;

    @BindView(R.id.Accountinfo)
    RelativeLayout Accountinfo;

    @BindView(R.id.Notifications)
    RelativeLayout Notifications;

    @BindView(R.id.Myorders)
    RelativeLayout Myorders;

    @BindView(R.id.Changepassword)
    RelativeLayout Changepassword;

    @BindView(R.id.Privacypolicy)
    RelativeLayout Privacypolicy;

    @BindView(R.id.Refundpolicy)
    RelativeLayout Refundpolicy;

    @BindView(R.id.Termsandconditions)
    RelativeLayout Termsandconditions;

    @BindView(R.id.Logout)
    RelativeLayout Logout;

    IPSetting ipSetting;
    Dialog mdialog;

    String auth_token="";
    GoogleSignInClient googleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        context = SettingActivity.this;
        ipSetting=new PSetting(this);
        auth_token= AppControler.getInstance(context).getString(AppControler.Key.AUTH_TOKEN);
        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);
        if (auth_token != null && !auth_token.isEmpty()) {
            Accountinfo.setVisibility(View.VISIBLE);
            Myorders.setVisibility(View.VISIBLE);
            Changepassword.setVisibility(View.VISIBLE);
            Logout.setVisibility(View.VISIBLE);
        } else {
            Accountinfo.setVisibility(View.GONE);
            Myorders.setVisibility(View.GONE);
            Changepassword.setVisibility(View.GONE);
            Logout.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.imgBack, R.id.Accountinfo, R.id.Notifications, R.id.Myorders, R.id.Changepassword,
            R.id.Privacypolicy, R.id.Refundpolicy, R.id.Termsandconditions, R.id.Logout})
    public void onViewClicked(View view) {
        Intent intent = null;

        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.Accountinfo:
                intent = new Intent(context, AccountInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.Notifications:
                intent = new Intent(context, NotificationActivity.class);
                startActivity(intent);
                break;
            case R.id.Myorders:
                intent = new Intent(context, MyOrdersActivity.class);
                startActivity(intent);
                break;
            case R.id.Changepassword:
                intent = new Intent(context, ChangePasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.Privacypolicy:
                intent = new Intent(context, OthersActivity.class);
                intent.putExtra("intentType", "privacy_policy");
                startActivity(intent);
                break;
            case R.id.Refundpolicy:
                intent = new Intent(context, OthersActivity.class);
                intent.putExtra("intentType", "refund_policy");
                startActivity(intent);
                break;
            case R.id.Termsandconditions:
                intent = new Intent(context, OthersActivity.class);
                intent.putExtra("intentType", "terms_and_conditions");
                startActivity(intent);
                break;
            case R.id.Logout:
                logoutAccount();
                break;
        }
    }
    private void logoutAccount() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.DialogeTheme);
        builder.setCancelable(true);
        builder.setMessage("Are you sure you want to logout?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        if (NetworkUtils.isNetworkConnectionAvailable(context)) {
                            mdialog = UtilsAlertDialog.ShowDialog(context);
                            if (auth_token!=null){
                                ipSetting.logout(auth_token);
                            }
                        }

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void successResponseFromPresenter(LogoutResponseModel logoutResponseModel) {
        mdialog.dismiss();
        Toast.makeText(context, ""+logoutResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
        logoutGoogle();
        LoginManager.getInstance().logOut();
        AppControler.getInstance(context).clear();
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void logoutGoogle() {
        googleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                    }
                });
    }

    @Override
    public void errorResponseFromPresenter(String message) {
        mdialog.dismiss();
        Toast.makeText(context, ""+message, Toast.LENGTH_SHORT).show();
    }

}