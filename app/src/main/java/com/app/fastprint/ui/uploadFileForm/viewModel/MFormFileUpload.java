package com.app.fastprint.ui.uploadFileForm.viewModel;

import android.os.Handler;
import android.os.Message;
import androidx.annotation.NonNull;
import com.app.fastprint.services.APIInterface;
import com.app.fastprint.services.RetrofitCalls;
import com.app.fastprint.ui.uploadFileForm.interfaces.IMFormFileUpload;
import com.app.fastprint.ui.uploadFileForm.interfaces.IPFormFileUpload;
import com.app.fastprint.ui.uploadFileForm.presenter.PFormFileUpload;
import com.app.fastprint.ui.uploadFileForm.responseModel.UploadFileSubmitResponseModel;


import okhttp3.MultipartBody;

public class MFormFileUpload implements IMFormFileUpload {

    IPFormFileUpload ipFormFileUpload;

    public MFormFileUpload(PFormFileUpload pFormFileUpload) {
        this.ipFormFileUpload=pFormFileUpload;
    }

    @Override
    public void uploadFileRestCalls(String name, String email, String url_link, String phone, String order_number, MultipartBody.Part upload_file, MultipartBody.Part upload_signature) {
        RetrofitCalls retrofitCalls=new RetrofitCalls();
        retrofitCalls.submitUploadFileFromApi(name,email,url_link,phone,order_number,upload_file,upload_signature,mHandler);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case APIInterface.SUBMIT_UPLOAD_FILE_SUCCESS:
                    UploadFileSubmitResponseModel uploadFileSubmitResponseModel=((UploadFileSubmitResponseModel)msg.obj);
                    ipFormFileUpload.uploadFileSuccessResponseFromModel(uploadFileSubmitResponseModel);
                    break;
                case APIInterface.SUBMIT_UPLOAD_FILE_FAILED:
                    String invalidReq = String.valueOf(msg.obj);
                    ipFormFileUpload.uploadFileErrorResponseFromModel(invalidReq);
                    break;
            }
        }
    };
}
