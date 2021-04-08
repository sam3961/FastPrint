package com.app.fastprint.ui.sendPayment.viewModel;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.app.fastprint.services.APIInterface;
import com.app.fastprint.services.RetrofitCalls;
import com.app.fastprint.ui.sendPayment.interfaces.IMSendPayment;
import com.app.fastprint.ui.sendPayment.interfaces.IPSendPayment;
import com.app.fastprint.ui.sendPayment.presenter.PSendPayment;
import com.app.fastprint.ui.sendPayment.sendPaymentResponseModel.SendPaymentResponseModel;

public class MSendPayment implements IMSendPayment {

    IPSendPayment ipSendPayment;

    public MSendPayment(PSendPayment pSendPayment) {
        this.ipSendPayment=pSendPayment;
    }

    @Override
    public void getPaymentListRestCalls() {
        RetrofitCalls retrofitCalls=new RetrofitCalls();
        retrofitCalls.sendpaymentApi(mHandler);
    }
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case APIInterface.SEND_PAYMENT_SUCCESS:
                    SendPaymentResponseModel sendPaymentResponseModel = ((SendPaymentResponseModel) msg.obj);
                    ipSendPayment.successResponseFromModel(sendPaymentResponseModel);
                    break;
                case APIInterface.SEND_PAYMENT_FAILED:
                    String invalidRequest = String.valueOf(msg.obj);
                    ipSendPayment.failedResponseFromModel(invalidRequest);
                    break;

            }
        }
    };
}
