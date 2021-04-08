package com.app.fastprint.ui.login.presenter;

import com.app.fastprint.ui.login.LoginActivity;
import com.app.fastprint.ui.login.interfaces.ILogin;
import com.app.fastprint.ui.login.interfaces.IMLogin;
import com.app.fastprint.ui.login.interfaces.IPLogin;
import com.app.fastprint.ui.login.loginResponse.LoginResponseModel;
import com.app.fastprint.ui.login.loginResponse.SocialLoginResponseModel;
import com.app.fastprint.ui.login.viewModel.MLogin;

public
class PLogin implements IPLogin {
    ILogin iLogin;
    IMLogin imLogin;
    public PLogin(LoginActivity loginActivity) {
        this.iLogin=loginActivity;
    }

    @Override
    public void login(String email, String password, String devices_token, String devices_type, String login_type, String current_latitude, String current_longitude) {
        imLogin=new MLogin(this);
        imLogin.loginRestCalls(email,password,devices_token,devices_type,login_type,current_latitude,current_longitude);
    }

    @Override
    public void successResponseFromModel(LoginResponseModel loginResponseModel) {
        iLogin.successResponseFromPresenter(loginResponseModel);
    }

    @Override
    public void errorResponseFromModel(String message) {
        iLogin.errorResponseFromPresenter(message);
    }

    @Override
    public void socialLogin(String email, String first_name, String last_name, String image, String login_type, String device_type, String current_lattitude, String current_longitude, String device_token) {
        imLogin=new MLogin(this);
        imLogin.socialLoginRestCalls(email,first_name,last_name,image,login_type,device_type,current_lattitude,current_longitude,device_token);
    }

    @Override
    public void successSocialLoginResponseFromModel(SocialLoginResponseModel socialLoginResponseModel) {
        iLogin.successSocialLoginResponseFromPresenter(socialLoginResponseModel);
    }

    @Override
    public void errorSocialLoginResponseFromModel(String message) {
        iLogin.errorSocialLoginResponseFromPresenter(message);
    }
}
