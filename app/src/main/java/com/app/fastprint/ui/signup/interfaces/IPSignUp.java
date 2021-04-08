package com.app.fastprint.ui.signup.interfaces;

import com.app.fastprint.ui.signup.responseModel.SignUpResponseModel;

public interface IPSignUp {
    void signUp(String first_name, String last_name, String phone, String email, String password);
    void successResponseFromModel(SignUpResponseModel signUpResponseModel);
    void errorResponseFromModel(String message);
}
