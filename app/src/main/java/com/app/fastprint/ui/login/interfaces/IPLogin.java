package com.app.fastprint.ui.login.interfaces;

import com.app.fastprint.ui.login.loginResponse.LoginResponseModel;
import com.app.fastprint.ui.login.loginResponse.SocialLoginResponseModel;

public
interface IPLogin {
    void login(String email, String password, String devices_token,
               String devices_type,String login_type, String current_latitude,String current_longitude);
    void successResponseFromModel(LoginResponseModel loginResponseModel);
    void errorResponseFromModel(String message);

    void socialLogin(String email,String first_name,String last_name,
                     String image,String login_type,
                     String device_type,String current_lattitude,
                     String current_longitude,String device_token);
    void successSocialLoginResponseFromModel(SocialLoginResponseModel socialLoginResponseModel);
    void errorSocialLoginResponseFromModel(String message);
}
