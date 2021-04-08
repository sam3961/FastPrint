package com.app.fastprint.ui.category.contactus.interfaces;

import com.app.fastprint.ui.category.contactus.responseModel.ContactUsResponseModel;

public interface IContactUs {
    void successResponseFromPresenter(ContactUsResponseModel contactUsResponseModel);
    void errorResponseFromPresenter(String message);
}
