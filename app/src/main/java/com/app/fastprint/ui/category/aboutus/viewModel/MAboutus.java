package com.app.fastprint.ui.category.aboutus.viewModel;

import android.os.Handler;
import android.os.Message;
import com.app.fastprint.services.APIInterface;
import com.app.fastprint.services.RetrofitCalls;
import com.app.fastprint.ui.category.aboutus.interfaces.IMAboutus;
import com.app.fastprint.ui.category.aboutus.interfaces.IPAboutus;
import com.app.fastprint.ui.category.aboutus.presenters.PAboutus;
import com.app.fastprint.ui.category.aboutus.responseModel.AboutUsResponseModel;

public
class MAboutus implements IMAboutus {
    IPAboutus ipAboutus;

    public MAboutus(PAboutus pAboutus) {
        this.ipAboutus = pAboutus;
    }

    @Override
    public void aboutUsRestCalls() {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.aboutUsApi(mHandler);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case APIInterface.ABOUT_US_SUCCESS:
                    AboutUsResponseModel aboutUsResponseModel = ((AboutUsResponseModel) msg.obj);
                    ipAboutus.successResponseFromModel(aboutUsResponseModel);
                    break;
                case APIInterface.ABOUT_US_FAILED:
                    String invalidRequest = String.valueOf(msg.obj);
                    ipAboutus.errorResponseFromModel(invalidRequest);
                    break;

            }
        }
    };
}
