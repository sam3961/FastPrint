package com.app.fastprint.ui.category.enquiryforms.presenters;

import com.app.fastprint.ui.category.enquiryforms.EnquiryFormsActivity;
import com.app.fastprint.ui.category.enquiryforms.interfaces.IEnquiryForms;
import com.app.fastprint.ui.category.enquiryforms.interfaces.IMEnquiryForms;
import com.app.fastprint.ui.category.enquiryforms.interfaces.IPEnquiryForms;
import com.app.fastprint.ui.category.enquiryforms.responseModel.FormsResponseModel;
import com.app.fastprint.ui.category.enquiryforms.viewModel.MEnquiryForm;

public

class PEnquiryForm  implements IPEnquiryForms {
    IEnquiryForms iEnquiryForms;
    IMEnquiryForms imEnquiryForms;

    public PEnquiryForm(EnquiryFormsActivity enquiryFormsActivity) {
        this.iEnquiryForms=enquiryFormsActivity;

    }

    @Override
    public void getFromList() {
        imEnquiryForms=new MEnquiryForm(this);
        imEnquiryForms.getFormListRestCalls();
    }

    @Override
    public void successResponseFromModel(FormsResponseModel formsResponseModel) {
        iEnquiryForms.successResponseFromPresenters(formsResponseModel);
    }

    @Override
    public void errorResponseFromModel(String message) {
        iEnquiryForms.errorResponseFromPresenters(message);
    }
}
