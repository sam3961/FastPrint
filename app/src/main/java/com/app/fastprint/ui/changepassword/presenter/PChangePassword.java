package com.app.fastprint.ui.changepassword.presenter;

import com.app.fastprint.ui.changepassword.ChangePasswordActivity;
import com.app.fastprint.ui.changepassword.interfaces.IChangePassword;
import com.app.fastprint.ui.changepassword.interfaces.IMChangePassword;
import com.app.fastprint.ui.changepassword.interfaces.IPChangePassword;
import com.app.fastprint.ui.changepassword.responseModel.ChangePasswordResponseModel;
import com.app.fastprint.ui.changepassword.viewModel.MChangePassword;

public
class PChangePassword implements IPChangePassword {

    IChangePassword iChangePassword;
    IMChangePassword imChangePassword;

    public PChangePassword(ChangePasswordActivity changePasswordActivity) {
        this.iChangePassword=changePasswordActivity;
    }

    @Override
    public void changePassword(String token, String old_password, String new_password) {
        imChangePassword=new MChangePassword(this);
        imChangePassword.changePasswordRestCalls(token,old_password,new_password);
    }

    @Override
    public void successResponseFromModel(ChangePasswordResponseModel changePasswordResponseModel) {
        iChangePassword.successResponseFromPresenter(changePasswordResponseModel);
    }

    @Override
    public void errorResponseFromModel(String message) {
        iChangePassword.errorResponseFromPresenter(message);
    }
}
