package com.app.fastprint.ui.resetPassword;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.fastprint.R;
import com.app.fastprint.baseClass.BaseClass;
import com.app.fastprint.networks.NetworkUtils;
import com.app.fastprint.ui.resetPassword.interfaces.IPResetPassword;
import com.app.fastprint.ui.resetPassword.interfaces.IResetPassword;
import com.app.fastprint.ui.resetPassword.presenter.PResetPassword;
import com.app.fastprint.ui.resetPassword.responseModel.ResetPasswordResponseModel;
import com.app.fastprint.utills.UtilsAlertDialog;
import com.app.fastprint.utills.UtilsFontFamily;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResetPasswordActivity extends BaseClass implements IResetPassword {

    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.tvTittle)
    TextView tvTittle;
    @BindView(R.id.tvregisterEmail)
    TextView tvregisterEmail;
    @BindView(R.id.editMail)
    EditText editMail;
    @BindView(R.id.tvResetPassword)
    TextView tvResetPassword;

    Dialog dialog;
    IPResetPassword ipResetPassword;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        ButterKnife.bind(this);
        context=ResetPasswordActivity.this;
        ipResetPassword=new PResetPassword(this);
        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoBold(this));

    }

    @OnClick({R.id.imgBack, R.id.tvResetPassword})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.tvResetPassword:
                validationOnRestPassword();
                break;
        }
    }

    private void validationOnRestPassword() {

        if (editMail.getText().toString().trim().isEmpty()){
            editMail.setError("Please enter your register email");
        }else {
            if (NetworkUtils.isNetworkConnectionAvailable(context)) {
                dialog = UtilsAlertDialog.ShowDialog(context);
                ipResetPassword.resetPassword(editMail.getText().toString().trim());
            }
        }
    }

    @Override
    public void successResponseFromModel(ResetPasswordResponseModel resetPasswordResponseModel) {
        dialog.dismiss();
        Toast.makeText(context, ""+resetPasswordResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void errorResponseFromModel(String message) {
        dialog.dismiss();
        Toast.makeText(context, ""+message, Toast.LENGTH_SHORT).show();
    }
}
