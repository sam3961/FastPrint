package com.app.fastprint.ui.editprofile.interfaces;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public
interface IMEditProfile {
    void updateProfileRestCalls(String token, String first_name, String last_name, String email, String phone, RequestBody imageRequest, MultipartBody.Part imageToUpload);

}
