package com.app.fastprint.ui.uploadFileForm.interfaces;



import com.app.fastprint.ui.uploadFileForm.responseModel.UploadFileSubmitResponseModel;

import okhttp3.MultipartBody;

public
interface IPFormFileUpload {
    void uploadFile(String name, String email, String url_link, String phone, String order_number, MultipartBody.Part upload_file, MultipartBody.Part upload_signature);
    void uploadFileSuccessResponseFromModel(UploadFileSubmitResponseModel uploadFileSubmitResponseModel);
    void uploadFileErrorResponseFromModel(String message);
}
