package com.app.fastprint.ui.editprofile.interfaces;

import com.app.fastprint.ui.editprofile.responseModel.UpdateProfileResponseModel;

public
interface IEditProfile {
    void successResponseFromPresenter(UpdateProfileResponseModel updateProfileResponseModel);
    void errorResponseFromPresenter(String message);
}
