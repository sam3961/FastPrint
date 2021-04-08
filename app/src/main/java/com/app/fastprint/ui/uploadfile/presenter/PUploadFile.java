package com.app.fastprint.ui.uploadfile.presenter;

import com.app.fastprint.ui.uploadfile.UploadFileActivity;
import com.app.fastprint.ui.uploadfile.interfaces.IMUploadFile;
import com.app.fastprint.ui.uploadfile.interfaces.IPUploadFile;
import com.app.fastprint.ui.uploadfile.interfaces.IUploadFile;
import com.app.fastprint.ui.uploadfile.responnseModel.UploadFileResponseModel;
import com.app.fastprint.ui.uploadfile.viewModel.MUploadFile;

public
class PUploadFile implements IPUploadFile {

    IUploadFile iUploadFile;
    IMUploadFile imUploadFile;
    public PUploadFile(UploadFileActivity uploadFileActivity) {
        this.iUploadFile=uploadFileActivity;

    }

    @Override
    public void uploadFileList() {
        imUploadFile=new MUploadFile(this);
        imUploadFile.uploadFileListRestCalls();
    }

    @Override
    public void successResponseFromModel(UploadFileResponseModel uploadFileResponseModel) {
        iUploadFile.successResponseFromPresenter(uploadFileResponseModel);
    }

    @Override
    public void errorResponseFromModel(String message) {
        iUploadFile.errorResponseFromPresenter(message);
    }
}
