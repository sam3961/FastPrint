package com.app.fastprint.ui.resetPassword.interfaces;

import com.app.fastprint.ui.resetPassword.responseModel.ResetPasswordResponseModel;

public
interface IResetPassword {
    void successResponseFromModel(ResetPasswordResponseModel resetPasswordResponseModel);
    void errorResponseFromModel(String message);
}
