package com.app.fastprint.ui.resetPassword.interfaces;

import com.app.fastprint.ui.resetPassword.responseModel.ResetPasswordResponseModel;

public
interface IPResetPassword {
    void resetPassword(String email);
    void successResponseFromPresenter(ResetPasswordResponseModel resetPasswordResponseModel);
    void errorResponseFromPresenter(String message);
}
