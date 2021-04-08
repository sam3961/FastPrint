package com.app.fastprint.ui.category.contactus.interfaces;

import com.app.fastprint.ui.category.contactus.responseModel.ContactUsResponseModel;

public interface IPContactUs {
    void getContactsUs();
    void successResponseFromModel(ContactUsResponseModel contactUsResponseModel);
    void errorResponseFromModel(String message);
}
