package com.app.fastprint.ui.signup.interfaces;

import com.app.fastprint.ui.signup.responseModel.SignUpResponseModel;

public
interface ISignUp {
    void successResponseFromPresenter(SignUpResponseModel signUpResponseModel);
    void errorResponseFromPresenter(String message);
}
