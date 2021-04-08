package com.app.fastprint.ui.findus.viewModel;

import android.os.Handler;
import android.os.Message;

import com.app.fastprint.services.APIInterface;
import com.app.fastprint.services.RetrofitCalls;
import com.app.fastprint.ui.findus.interfaces.IMFindUs;
import com.app.fastprint.ui.findus.interfaces.IPFindUs;
import com.app.fastprint.ui.findus.presenters.PFindUs;
import com.app.fastprint.ui.findus.responseModel.FindUsResponseModel;

public
class MFindUs implements IMFindUs {
    IPFindUs ipFindUs;

    public MFindUs(PFindUs pFindUs) {
        this.ipFindUs = pFindUs;
    }

    @Override
    public void findUsRestCalls() {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.findusApi(mHandler);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case APIInterface.FIND_US_SUCCESS:
                    FindUsResponseModel contactUsResponseModel = ((FindUsResponseModel) msg.obj);
                    ipFindUs.successResponseFromModel(contactUsResponseModel);
                    break;
                case APIInterface.FIND_US_FAILED:
                    String invalidRequest = String.valueOf(msg.obj);
                    ipFindUs.errorResponseFromModel(invalidRequest);
                    break;

            }
        }
    };
}
