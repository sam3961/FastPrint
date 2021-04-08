package com.app.fastprint.ui.login.viewModel;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.app.fastprint.services.APIInterface;
import com.app.fastprint.services.RetrofitCalls;
import com.app.fastprint.ui.login.interfaces.IMLogin;
import com.app.fastprint.ui.login.interfaces.IPLogin;
import com.app.fastprint.ui.login.loginResponse.LoginResponseModel;
import com.app.fastprint.ui.login.loginResponse.SocialLoginResponseModel;
import com.app.fastprint.ui.login.presenter.PLogin;

public
class MLogin implements IMLogin {
    IPLogin ipLogin;

    public MLogin(PLogin pLogin) {
        this.ipLogin = pLogin;
    }

    @Override
    public void loginRestCalls(String email, String password, String devices_token, String devices_type, String login_type, String current_latitude, String current_longitude) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.loginApi(email, password, devices_token, devices_type, login_type, current_latitude, current_longitude, mHandler);
    }

    @Override
    public void socialLoginRestCalls(String email, String first_name, String last_name, String image, String login_type, String device_type, String current_lattitude, String current_longitude, String device_token) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.socialLoginApi(email, first_name, last_name, image, login_type, device_type, current_lattitude, current_longitude, device_token, mHandler);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case APIInterface.lOGIN_SUCCESS:
                    LoginResponseModel loginResponseModel = ((LoginResponseModel) msg.obj);
                    ipLogin.successResponseFromModel(loginResponseModel);
                    break;
                case APIInterface.lOGIN_FAILED:
                    String invalidReq = String.valueOf(msg.obj);
                    ipLogin.errorResponseFromModel(invalidReq);
                    break;
                case APIInterface.SOCIAL_LOGIN_SUCCESS:
                    SocialLoginResponseModel socialLoginResponseModel = ((SocialLoginResponseModel) msg.obj);
                    ipLogin.successSocialLoginResponseFromModel(socialLoginResponseModel);
                    break;
                case APIInterface.SOCIAL_LOGIN_FAILED:
                    String error = String.valueOf(msg.obj);
                    ipLogin.errorSocialLoginResponseFromModel(error);
                    break;
            }
        }
    };
}
