package com.app.fastprint.ui.category.contactus.viewModel;
import android.os.Handler;
import android.os.Message;
import com.app.fastprint.services.APIInterface;
import com.app.fastprint.services.RetrofitCalls;
import com.app.fastprint.ui.category.contactus.interfaces.IMContactUs;
import com.app.fastprint.ui.category.contactus.interfaces.IPContactUs;
import com.app.fastprint.ui.category.contactus.presenters.PContactUs;
import com.app.fastprint.ui.category.contactus.responseModel.ContactUsResponseModel;

public
class MContactUs implements IMContactUs {
    IPContactUs ipContactUs;

    public MContactUs(PContactUs pContactUs) {
        this.ipContactUs = pContactUs;
    }

    @Override
    public void ContactUsRestCalls() {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.contactUsApi(mHandler);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case APIInterface.CONTACTS_US_SUCCESS:
                    ContactUsResponseModel contactUsResponseModel = ((ContactUsResponseModel) msg.obj);
                    ipContactUs.successResponseFromModel(contactUsResponseModel);
                    break;
                case APIInterface.CONTACTS_US_FAILED:
                    String invalidRequest = String.valueOf(msg.obj);
                    ipContactUs.errorResponseFromModel(invalidRequest);
                    break;

            }
        }
    };

}
