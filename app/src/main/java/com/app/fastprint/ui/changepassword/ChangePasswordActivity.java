package com.app.fastprint.ui.changepassword;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.fastprint.R;
import com.app.fastprint.baseClass.BaseClass;
import com.app.fastprint.networks.NetworkUtils;
import com.app.fastprint.ui.changepassword.interfaces.IChangePassword;
import com.app.fastprint.ui.changepassword.interfaces.IPChangePassword;
import com.app.fastprint.ui.changepassword.presenter.PChangePassword;
import com.app.fastprint.ui.changepassword.responseModel.ChangePasswordResponseModel;
import com.app.fastprint.utills.AppControler;
import com.app.fastprint.utills.CommonMethods;
import com.app.fastprint.utills.UtilsAlertDialog;
import com.app.fastprint.utills.UtilsFontFamily;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePasswordActivity extends BaseClass implements IChangePassword {

    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.tvTittle)
    TextView tvTittle;

    @BindView(R.id.editOldPassword)
    EditText editOldPassword;

    @BindView(R.id.editNewPassword)
    EditText editNewPassword;

    @BindView(R.id.editConfirmPassword)
    EditText editConfirmPassword;

    @BindView(R.id.btn_update)
    Button btnUpdate;

    String user_id = "";
    Context context;
    IPChangePassword ipChangePassword;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);
        context = ChangePasswordActivity.this;
        user_id = AppControler.getInstance(context).getString(AppControler.Key.USER_ID);
        ipChangePassword = new PChangePassword(this);
        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));

    }

    @OnClick({R.id.imgBack, R.id.btn_update})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.btn_update:
                validationOnUpdatePassword();
                break;
        }
    }

    private void validationOnUpdatePassword() {

        if (editOldPassword.getText().toString().isEmpty()) {
            editOldPassword.setError("Enter old password");
        } else if (editNewPassword.getText().toString().trim().isEmpty()) {
            editNewPassword.setError("Enter new password");
        } else if (editNewPassword.length() < 6 || editNewPassword.length() > 16) {
            editNewPassword.setError("Password between 6 and 16 alphanumeric characters");
        } else if (!CommonMethods.isValidPassword(editNewPassword.getText().toString().trim())) {
            editNewPassword.setError("Password should contain 1 numeric or 1 character or 1 special character");
        } else if (editConfirmPassword.getText().toString().trim().isEmpty()) {
            editConfirmPassword.setError("Confirm new password");
        } else if (!editConfirmPassword.getText().toString().trim().matches(editNewPassword.getText().toString().trim())) {
            editConfirmPassword.setError("Password does not match");
        } else {
            if (NetworkUtils.isNetworkConnectionAvailable(context)) {
                dialog = UtilsAlertDialog.ShowDialog(context);
                ipChangePassword.changePassword(user_id, editOldPassword.getText().toString(), editConfirmPassword.getText().toString().trim());
            }
        }
    }

    @Override
    public void successResponseFromPresenter(ChangePasswordResponseModel changePasswordResponseModel) {
        dialog.dismiss();
        Toast.makeText(context, "" + changePasswordResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
        finish();
    }


    @Override
    public void errorResponseFromPresenter(String message) {
        dialog.dismiss();
        Toast.makeText(context, "" + message, Toast.LENGTH_SHORT).show();
        finish();
    }
}