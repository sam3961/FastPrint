package com.app.fastprint.ui.category.enquiryforms.viewModel;

import android.os.Handler;
import android.os.Message;

import com.app.fastprint.services.APIInterface;
import com.app.fastprint.services.RetrofitCalls;
import com.app.fastprint.ui.category.enquiryforms.interfaces.IMEnquiryForms;
import com.app.fastprint.ui.category.enquiryforms.interfaces.IPEnquiryForms;
import com.app.fastprint.ui.category.enquiryforms.presenters.PEnquiryForm;
import com.app.fastprint.ui.category.enquiryforms.responseModel.FormsResponseModel;

public
class MEnquiryForm implements IMEnquiryForms {
    IPEnquiryForms ipEnquiryForms;

    public MEnquiryForm(PEnquiryForm pEnquiryForm) {
        this.ipEnquiryForms = pEnquiryForm;

    }

    @Override
    public void getFormListRestCalls() {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.enquiryformApi(mHandler);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case APIInterface.FORM_LIST_SUCCESS:
                    FormsResponseModel formsResponseModel = ((FormsResponseModel) msg.obj);
                    ipEnquiryForms.successResponseFromModel(formsResponseModel);
                    break;
                case APIInterface.FORM_LIST_FAILED:
                    String invalidRequest = String.valueOf(msg.obj);
                    ipEnquiryForms.errorResponseFromModel(invalidRequest);
                    break;

            }
        }
    };
}
