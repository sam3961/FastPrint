package com.app.fastprint.ui.changepassword.interfaces;

import com.app.fastprint.ui.changepassword.responseModel.ChangePasswordResponseModel;

public
interface IPChangePassword {
    void changePassword(String token,String old_password,String new_password);
    void successResponseFromModel(ChangePasswordResponseModel changePasswordResponseModel);
    void errorResponseFromModel(String message);
}
