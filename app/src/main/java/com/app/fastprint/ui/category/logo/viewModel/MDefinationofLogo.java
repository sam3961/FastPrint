package com.app.fastprint.ui.category.logo.viewModel;

import android.os.Handler;
import android.os.Message;

import com.app.fastprint.services.APIInterface;
import com.app.fastprint.services.RetrofitCalls;
import com.app.fastprint.ui.category.logo.interfaces.IMDefinationofLogo;
import com.app.fastprint.ui.category.logo.interfaces.IPDefinationofLogo;
import com.app.fastprint.ui.category.logo.presenters.PDefinationofLogo;
import com.app.fastprint.ui.category.logo.responseModel.DefinationOfLogoResponseModel;

public
class MDefinationofLogo implements IMDefinationofLogo {

    IPDefinationofLogo ipDefinationofLogo;

    public MDefinationofLogo(PDefinationofLogo pDefinationofLogo) {
        this.ipDefinationofLogo = pDefinationofLogo;
    }

    @Override
    public void definationOfLogoRestCalls() {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.definationofLogoApi(mHandler);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case APIInterface.DEFINATION_OF_LOGO_SUCCESS:
                    DefinationOfLogoResponseModel definationOfLogoResponseModel = ((DefinationOfLogoResponseModel) msg.obj);
                    ipDefinationofLogo.successResponseFromModel(definationOfLogoResponseModel);
                    break;
                case APIInterface.DEFINATION_OF_LOGO_FAILED:
                    String invalidRequest = String.valueOf(msg.obj);
                    ipDefinationofLogo.errorResponseFromModel(invalidRequest);
                    break;

            }
        }
    };
}
