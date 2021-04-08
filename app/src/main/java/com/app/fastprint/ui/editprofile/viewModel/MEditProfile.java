package com.app.fastprint.ui.editprofile.viewModel;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.app.fastprint.services.APIInterface;
import com.app.fastprint.services.RetrofitCalls;
import com.app.fastprint.ui.editprofile.interfaces.IMEditProfile;
import com.app.fastprint.ui.editprofile.interfaces.IPEditProfile;
import com.app.fastprint.ui.editprofile.presenter.PEditProfile;
import com.app.fastprint.ui.editprofile.responseModel.UpdateProfileResponseModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public
class MEditProfile implements IMEditProfile {

    IPEditProfile ipEditProfile;

    public MEditProfile(PEditProfile pEditProfile) {
        this.ipEditProfile = pEditProfile;
    }

    @Override
    public void updateProfileRestCalls(String token, String first_name, String last_name, String email, String phone, RequestBody imageRequest, MultipartBody.Part imageToUpload) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.updateProfileApi(token, first_name, last_name, email, phone, imageRequest, imageToUpload, mHandler);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case APIInterface.UPDATE_PROFILE_SUCCESS:
                    UpdateProfileResponseModel updateProfileResponseModel=((UpdateProfileResponseModel)msg.obj);
                    ipEditProfile.successResponseFromModel(updateProfileResponseModel);
                    break;
                case APIInterface.UPDATE_PROFILE_FAILED:
                    String error= String.valueOf(msg.obj);
                    ipEditProfile.errorResponseFromModel(error);
                    break;
            }
        }
    };
}
