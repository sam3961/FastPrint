package com.app.fastprint.ui.category.contactus.presenters;

import com.app.fastprint.ui.category.contactus.ContactUsActivity;
import com.app.fastprint.ui.category.contactus.interfaces.IContactUs;
import com.app.fastprint.ui.category.contactus.interfaces.IMContactUs;
import com.app.fastprint.ui.category.contactus.interfaces.IPContactUs;
import com.app.fastprint.ui.category.contactus.responseModel.ContactUsResponseModel;
import com.app.fastprint.ui.category.contactus.viewModel.MContactUs;

public
class PContactUs implements IPContactUs {

    IContactUs iContactUs;
    IMContactUs imContactUs;

    public PContactUs(ContactUsActivity contactUsActivity) {
        this.iContactUs=contactUsActivity;
    }

    @Override
    public void getContactsUs() {
        imContactUs=new MContactUs(this);
        imContactUs.ContactUsRestCalls();
    }

    @Override
    public void successResponseFromModel(ContactUsResponseModel contactUsResponseModel) {
        iContactUs.successResponseFromPresenter(contactUsResponseModel);
    }

    @Override
    public void errorResponseFromModel(String message) {
        iContactUs.errorResponseFromPresenter(message);
    }
}
