package com.app.fastprint.ui.others.viewModel;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.app.fastprint.services.APIInterface;
import com.app.fastprint.services.RetrofitCalls;
import com.app.fastprint.ui.others.interfaces.IMOther;
import com.app.fastprint.ui.others.interfaces.IPOther;
import com.app.fastprint.ui.others.presenter.POther;
import com.app.fastprint.ui.others.responseModel.OtherResponseModel;

public
class MOther implements IMOther {

    IPOther ipOther;

    public MOther(POther pOther) {
        this.ipOther = pOther;
    }

    @Override
    public void otherRestCalls() {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.otherApi(mHandler);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case APIInterface.POLICY_SUCCESS:
                    OtherResponseModel otherResponseModel = ((OtherResponseModel) msg.obj);
                    ipOther.successResponseFromModel(otherResponseModel);
                    break;
                case APIInterface.POLICY_FAILED:
                    String error = String.valueOf(msg.obj);
                    ipOther.errorResponseFromModel(error);
                    break;
            }
        }
    };
}
