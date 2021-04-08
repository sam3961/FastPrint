package com.app.fastprint.ui.category.services.viewModel;

import android.os.Handler;
import android.os.Message;

import com.app.fastprint.services.APIInterface;
import com.app.fastprint.services.RetrofitCalls;
import com.app.fastprint.ui.category.services.interfaces.IMServices;
import com.app.fastprint.ui.category.services.interfaces.IPServices;
import com.app.fastprint.ui.category.services.presenters.PServices;
import com.app.fastprint.ui.category.services.responseModel.ServicesResponseModel;

public
class MServices implements IMServices {

    IPServices ipServices;

    public MServices(PServices pServices) {
        this.ipServices=pServices;

    }

    @Override
    public void servicesRestCalls() {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.servicesApi(mHandler);
    }
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case APIInterface.SERVICES_SUCCESS:
                    ServicesResponseModel servicesResponseModel = ((ServicesResponseModel) msg.obj);
                    ipServices.successResponseFromModel(servicesResponseModel);
                    break;
                case APIInterface.SERVICES_FAILED:
                    String invalidRequest = String.valueOf(msg.obj);
                    ipServices.errorResponseFromModel(invalidRequest);
                    break;

            }
        }
    };
}
