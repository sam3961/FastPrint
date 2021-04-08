package com.app.fastprint.ui.settings.interfaces;

import com.app.fastprint.ui.settings.responseModel.LogoutResponseModel;

public
interface ISetting {
    void successResponseFromPresenter(LogoutResponseModel logoutResponseModel);
    void errorResponseFromPresenter(String message);
}
