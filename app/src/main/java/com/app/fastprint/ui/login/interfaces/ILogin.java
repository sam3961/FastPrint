package com.app.fastprint.ui.login.interfaces;

import com.app.fastprint.ui.login.loginResponse.LoginResponseModel;
import com.app.fastprint.ui.login.loginResponse.SocialLoginResponseModel;

public
interface ILogin {
    void successResponseFromPresenter(LoginResponseModel loginResponseModel);
    void errorResponseFromPresenter(String message);

    void successSocialLoginResponseFromPresenter(SocialLoginResponseModel socialLoginResponseModel);
    void errorSocialLoginResponseFromPresenter(String message);
}
