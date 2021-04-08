package com.app.fastprint.ui.changepassword.viewModel;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.app.fastprint.services.APIInterface;
import com.app.fastprint.services.RetrofitCalls;
import com.app.fastprint.ui.changepassword.interfaces.IMChangePassword;
import com.app.fastprint.ui.changepassword.interfaces.IPChangePassword;
import com.app.fastprint.ui.changepassword.presenter.PChangePassword;
import com.app.fastprint.ui.changepassword.responseModel.ChangePasswordResponseModel;

public
class MChangePassword implements IMChangePassword {

    IPChangePassword ipChangePassword;

    public MChangePassword(PChangePassword pChangePassword) {
        this.ipChangePassword = pChangePassword;
    }

    @Override
    public void changePasswordRestCalls(String token, String old_password, String new_password) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.changePasswordApi(token, old_password, new_password, mHandler);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case APIInterface.CHANGE_PASSWORD_SUCCESS:
                    ChangePasswordResponseModel changePasswordResponseModel = ((ChangePasswordResponseModel) msg.obj);
                    ipChangePassword.successResponseFromModel(changePasswordResponseModel);
                    break;
                case APIInterface.CHANGE_PASSWORD_FAILED:
                    String invalid_request = String.valueOf(msg.obj);
                    ipChangePassword.errorResponseFromModel(invalid_request);
                    break;
            }
        }
    };
}
