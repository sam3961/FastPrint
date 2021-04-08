package com.app.fastprint.ui.signup.viewModel;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.app.fastprint.services.APIInterface;
import com.app.fastprint.services.RetrofitCalls;
import com.app.fastprint.ui.signup.interfaces.IMSignUp;
import com.app.fastprint.ui.signup.interfaces.IPSignUp;
import com.app.fastprint.ui.signup.presenter.PSignUp;
import com.app.fastprint.ui.signup.responseModel.SignUpResponseModel;

public
class MSignUp implements IMSignUp {
    IPSignUp ipSignUp;

    public MSignUp(PSignUp pSignUp) {
        this.ipSignUp = pSignUp;
    }

    @Override
    public void signUpRestCalls(String first_name, String last_name, String phone, String email, String password) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.signUpApi(first_name, last_name, phone, email, password, mHandler);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case APIInterface.SIGNUP_SUCCESS:
                    SignUpResponseModel signUpResponseModel = ((SignUpResponseModel) msg.obj);
                    ipSignUp.successResponseFromModel(signUpResponseModel);
                    break;
                case APIInterface.SIGNUP_FAILED:
                    String invalidReq = String.valueOf(msg.obj);
                    ipSignUp.errorResponseFromModel(invalidReq);
                    break;
            }
        }
    };
}
