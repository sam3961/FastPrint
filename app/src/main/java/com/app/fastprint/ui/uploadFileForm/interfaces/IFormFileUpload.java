package com.app.fastprint.ui.uploadFileForm.interfaces;


import com.app.fastprint.ui.uploadFileForm.responseModel.UploadFileSubmitResponseModel;

public
interface IFormFileUpload {
    void uploadFileSuccessResponseFromPresenter(UploadFileSubmitResponseModel uploadFileSubmitResponseModel);
    void uploadFileErrorResponseFromPresenter(String message);
}
