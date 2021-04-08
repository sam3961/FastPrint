package com.app.fastprint.ui.uploadfile.interfaces;

import com.app.fastprint.ui.uploadfile.responnseModel.UploadFileResponseModel;

public
interface IUploadFile {
    void successResponseFromPresenter(UploadFileResponseModel uploadFileResponseModel);
    void errorResponseFromPresenter(String message);
}
