package com.app.fastprint.ui.uploadFileForm.interfaces;

import okhttp3.MultipartBody;

public
interface IMFormFileUpload {
    void uploadFileRestCalls(String name, String email, String url_link, String phone, String order_number, MultipartBody.Part upload_file, MultipartBody.Part upload_signature);

}
