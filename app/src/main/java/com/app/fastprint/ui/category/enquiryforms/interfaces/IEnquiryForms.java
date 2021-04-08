package com.app.fastprint.ui.category.enquiryforms.interfaces;

import com.app.fastprint.ui.category.enquiryforms.responseModel.FormsResponseModel;

public interface IEnquiryForms {

    void successResponseFromPresenters(FormsResponseModel formsResponseModel);
    void errorResponseFromPresenters(String message);
}
