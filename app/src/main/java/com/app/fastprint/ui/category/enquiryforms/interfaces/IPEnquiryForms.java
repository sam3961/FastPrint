package com.app.fastprint.ui.category.enquiryforms.interfaces;
import com.app.fastprint.ui.category.enquiryforms.responseModel.FormsResponseModel;

public interface IPEnquiryForms {
    void getFromList();
    void successResponseFromModel(FormsResponseModel formsResponseModel);
    void errorResponseFromModel(String message);
}
