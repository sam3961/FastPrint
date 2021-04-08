package com.app.fastprint.ui.signup.presenter;

import com.app.fastprint.ui.signup.SignUpActivity;
import com.app.fastprint.ui.signup.interfaces.IMSignUp;
import com.app.fastprint.ui.signup.interfaces.IPSignUp;
import com.app.fastprint.ui.signup.interfaces.ISignUp;
import com.app.fastprint.ui.signup.responseModel.SignUpResponseModel;
import com.app.fastprint.ui.signup.viewModel.MSignUp;

public
class PSignUp implements IPSignUp {
    ISignUp iSignUp;
    IMSignUp imSignUp;
    public PSignUp(SignUpActivity signUpActivity) {
        this.iSignUp=signUpActivity;
    }

    @Override
    public void signUp(String first_name, String last_name, String phone, String email, String password) {
        imSignUp=new MSignUp(this);
        imSignUp.signUpRestCalls(first_name,last_name,phone,email,password);
    }

    @Override
    public void successResponseFromModel(SignUpResponseModel signUpResponseModel) {
        iSignUp.successResponseFromPresenter(signUpResponseModel);
    }

    @Override
    public void errorResponseFromModel(String message) {
        iSignUp.errorResponseFromPresenter(message);
    }
}
