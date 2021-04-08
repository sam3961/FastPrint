package com.app.fastprint.ui.settings.interfaces;

import com.app.fastprint.ui.settings.responseModel.LogoutResponseModel;

public
interface IPSetting {
    void logout(String token);
    void successResponseFromModel(LogoutResponseModel logoutResponseModel);
    void errorResponseFromModel(String message);
}
