package com.app.fastprint.ui.uploadfile.viewModel;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.app.fastprint.services.APIInterface;
import com.app.fastprint.services.RetrofitCalls;
import com.app.fastprint.ui.uploadfile.interfaces.IMUploadFile;
import com.app.fastprint.ui.uploadfile.interfaces.IPUploadFile;
import com.app.fastprint.ui.uploadfile.presenter.PUploadFile;
import com.app.fastprint.ui.uploadfile.responnseModel.UploadFileResponseModel;

public
class MUploadFile implements IMUploadFile {
    IPUploadFile ipUploadFile;
    public MUploadFile(PUploadFile pUploadFile) {
        this.ipUploadFile=pUploadFile;
    }

    @Override
    public void uploadFileListRestCalls() {
        RetrofitCalls retrofitCalls=new RetrofitCalls();
        retrofitCalls.uploadFileApi(mHandler);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case APIInterface.UPLOAD_FILE_SUCCESS:
                    UploadFileResponseModel uploadFileResponseModel = ((UploadFileResponseModel) msg.obj);
                    ipUploadFile.successResponseFromModel(uploadFileResponseModel);
                    break;
                case APIInterface.UPLOAD_FILE_FAILED:
                    String invalidRequest = String.valueOf(msg.obj);
                    ipUploadFile.errorResponseFromModel(invalidRequest);
                    break;

            }
        }
    };
}
