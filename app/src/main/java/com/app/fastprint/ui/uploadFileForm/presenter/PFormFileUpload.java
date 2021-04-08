package com.app.fastprint.ui.uploadFileForm.presenter;

import com.app.fastprint.ui.uploadFileForm.FormFileUploadActivity;
import com.app.fastprint.ui.uploadFileForm.interfaces.IFormFileUpload;
import com.app.fastprint.ui.uploadFileForm.interfaces.IMFormFileUpload;
import com.app.fastprint.ui.uploadFileForm.interfaces.IPFormFileUpload;
import com.app.fastprint.ui.uploadFileForm.responseModel.UploadFileSubmitResponseModel;
import com.app.fastprint.ui.uploadFileForm.viewModel.MFormFileUpload;


import okhttp3.MultipartBody;

public
class PFormFileUpload implements IPFormFileUpload {
    IFormFileUpload iFormFileUpload;
    IMFormFileUpload imFormFileUpload;
    public PFormFileUpload(FormFileUploadActivity formFileUploadActivity) {
        this.iFormFileUpload=formFileUploadActivity;
    }

    @Override
    public void uploadFile(String name, String email, String url_link, String phone, String order_number, MultipartBody.Part upload_file, MultipartBody.Part upload_signature) {
        imFormFileUpload=new MFormFileUpload(this);
        imFormFileUpload.uploadFileRestCalls(name,email,url_link,phone,order_number,upload_file,upload_signature);
    }

    @Override
    public void uploadFileSuccessResponseFromModel(UploadFileSubmitResponseModel uploadFileSubmitResponseModel) {
        iFormFileUpload.uploadFileSuccessResponseFromPresenter(uploadFileSubmitResponseModel);
    }

    @Override
    public void uploadFileErrorResponseFromModel(String message) {
        iFormFileUpload.uploadFileErrorResponseFromPresenter(message);
    }
}
