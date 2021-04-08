package com.app.fastprint.ui.changepassword.interfaces;

import com.app.fastprint.ui.changepassword.responseModel.ChangePasswordResponseModel;

public
interface IChangePassword {
    void successResponseFromPresenter(ChangePasswordResponseModel changePasswordResponseModel);
    void errorResponseFromPresenter(String message);
}
