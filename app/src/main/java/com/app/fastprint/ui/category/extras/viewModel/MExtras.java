package com.app.fastprint.ui.category.extras.viewModel;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.app.fastprint.services.APIInterface;
import com.app.fastprint.services.RetrofitCalls;
import com.app.fastprint.ui.category.extras.interfaces.IMExtras;
import com.app.fastprint.ui.category.extras.interfaces.IPExtras;
import com.app.fastprint.ui.category.extras.presenters.PExtras;
import com.app.fastprint.ui.category.extras.responseModel.ExtrasResponseModel;

public
class MExtras implements IMExtras {
    IPExtras ipExtras;

    public MExtras(PExtras pExtras) {
        this.ipExtras = pExtras;
    }

    @Override
    public void getExtrasRestCalls() {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.extrasApi(mHandler);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case APIInterface.EXTRAS_LIST_SUCCESS:
                    ExtrasResponseModel extrasResponseModel = ((ExtrasResponseModel) msg.obj);
                    ipExtras.successResponseFromModel(extrasResponseModel);
                    break;
                case APIInterface.EXTRAS_LIST_FAILED:
                    String invalidRequest = String.valueOf(msg.obj);
                    ipExtras.errorResponseFromModel(invalidRequest);
                    break;

            }
        }
    };

}
