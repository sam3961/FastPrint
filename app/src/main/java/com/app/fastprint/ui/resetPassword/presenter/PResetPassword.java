package com.app.fastprint.ui.resetPassword.presenter;

import com.app.fastprint.ui.resetPassword.ResetPasswordActivity;
import com.app.fastprint.ui.resetPassword.interfaces.IMResetPassword;
import com.app.fastprint.ui.resetPassword.interfaces.IPResetPassword;
import com.app.fastprint.ui.resetPassword.interfaces.IResetPassword;
import com.app.fastprint.ui.resetPassword.responseModel.ResetPasswordResponseModel;
import com.app.fastprint.ui.resetPassword.viewModel.MResetPassword;

public
class PResetPassword implements IPResetPassword {
    IResetPassword resetPassword;
    IMResetPassword imResetPassword;
    public PResetPassword(ResetPasswordActivity resetPasswordActivity) {
        this.resetPassword=resetPasswordActivity;
    }

    @Override
    public void resetPassword(String email) {
        imResetPassword=new MResetPassword(this);
        imResetPassword.resetPasswordRestCalls(email);
    }

    @Override
    public void successResponseFromPresenter(ResetPasswordResponseModel resetPasswordResponseModel) {
        resetPassword.successResponseFromModel(resetPasswordResponseModel);
    }

    @Override
    public void errorResponseFromPresenter(String message) {
        resetPassword.errorResponseFromModel(message);
    }
}
