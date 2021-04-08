package com.app.fastprint.ui.editprofile.interfaces;

import com.app.fastprint.ui.editprofile.responseModel.UpdateProfileResponseModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public
interface IPEditProfile {
    void updateProfile(String token, String first_name, String last_name, String email, String phone, RequestBody imageRequest, MultipartBody.Part imageToUpload);
    void successResponseFromModel(UpdateProfileResponseModel updateProfileResponseModel);
    void errorResponseFromModel(String message);
}
