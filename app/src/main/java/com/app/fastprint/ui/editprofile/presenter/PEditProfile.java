package com.app.fastprint.ui.editprofile.presenter;

import com.app.fastprint.ui.editprofile.EditProfileActivity;
import com.app.fastprint.ui.editprofile.interfaces.IEditProfile;
import com.app.fastprint.ui.editprofile.interfaces.IMEditProfile;
import com.app.fastprint.ui.editprofile.interfaces.IPEditProfile;
import com.app.fastprint.ui.editprofile.responseModel.UpdateProfileResponseModel;
import com.app.fastprint.ui.editprofile.viewModel.MEditProfile;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public
class PEditProfile implements IPEditProfile {

    IEditProfile iEditProfile;
    IMEditProfile imEditProfile;

    public PEditProfile(EditProfileActivity editProfileActivity) {
        this.iEditProfile=editProfileActivity;
    }

    @Override
    public void updateProfile(String token, String first_name, String last_name, String email, String phone, RequestBody imageRequest, MultipartBody.Part imageToUpload) {
        imEditProfile=new MEditProfile(this);
        imEditProfile.updateProfileRestCalls(token,first_name,last_name,email,phone,imageRequest,imageToUpload);
    }

    @Override
    public void successResponseFromModel(UpdateProfileResponseModel updateProfileResponseModel) {
        iEditProfile.successResponseFromPresenter(updateProfileResponseModel);
    }

    @Override
    public void errorResponseFromModel(String message) {
        iEditProfile.errorResponseFromPresenter(message);
    }
}
