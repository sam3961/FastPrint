package com.app.fastprint.ui.settings.viewModel;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.app.fastprint.services.APIInterface;
import com.app.fastprint.services.RetrofitCalls;
import com.app.fastprint.ui.settings.interfaces.IMSetting;
import com.app.fastprint.ui.settings.interfaces.IPSetting;
import com.app.fastprint.ui.settings.presenter.PSetting;
import com.app.fastprint.ui.settings.responseModel.LogoutResponseModel;

public
class MSetting implements IMSetting {
    IPSetting ipSetting;

    public MSetting(PSetting pSetting) {
        this.ipSetting = pSetting;
    }

    @Override
    public void logoutRestCalls(String token) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.logoutApi(token, mHandler);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case APIInterface.LOGOUT_SUCCESS:
                    LogoutResponseModel logoutResponseModel = ((LogoutResponseModel) msg.obj);
                    ipSetting.successResponseFromModel(logoutResponseModel);
                    break;
                case APIInterface.LOGOUT_FAILED:
                    String invalid = String.valueOf(msg.obj);
                    ipSetting.errorResponseFromModel(invalid);
                    break;
            }
        }
    };
}
