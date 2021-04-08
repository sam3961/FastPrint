package com.app.fastprint.ui.resetPassword.viewModel;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.app.fastprint.services.APIInterface;
import com.app.fastprint.services.RetrofitCalls;
import com.app.fastprint.ui.resetPassword.interfaces.IMResetPassword;
import com.app.fastprint.ui.resetPassword.interfaces.IPResetPassword;
import com.app.fastprint.ui.resetPassword.presenter.PResetPassword;
import com.app.fastprint.ui.resetPassword.responseModel.ResetPasswordResponseModel;

public
class MResetPassword implements IMResetPassword {
    IPResetPassword ipResetPassword;

    public MResetPassword(PResetPassword pResetPassword) {
        this.ipResetPassword = pResetPassword;
    }

    @Override
    public void resetPasswordRestCalls(String email) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.resetPasswordApi(email, mHandler);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case APIInterface.FORGOT_PASSWORD_SUCCESS:
                    ResetPasswordResponseModel resetPasswordResponseModel = ((ResetPasswordResponseModel) msg.obj);
                    ipResetPassword.successResponseFromPresenter(resetPasswordResponseModel);
                    break;
                case APIInterface.FORGOT_PASSWORD_FAILED:
                    String invalid_request = String.valueOf(msg.obj);
                    ipResetPassword.errorResponseFromPresenter(invalid_request);
                    break;
            }
        }
    };
}
