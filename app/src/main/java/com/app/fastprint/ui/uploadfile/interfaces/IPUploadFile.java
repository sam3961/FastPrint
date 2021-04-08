package com.app.fastprint.ui.uploadfile.interfaces;

import com.app.fastprint.ui.uploadfile.responnseModel.UploadFileResponseModel;

public
interface IPUploadFile {
    void uploadFileList();
    void successResponseFromModel(UploadFileResponseModel uploadFileResponseModel);
    void errorResponseFromModel(String message);
}
