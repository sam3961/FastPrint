package com.app.fastprint.ui.payment.viewModel;


import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.app.fastprint.services.APIInterface;
import com.app.fastprint.services.RetrofitCalls;
import com.app.fastprint.ui.payment.interfaces.IMPayment;
import com.app.fastprint.ui.payment.interfaces.IPPayment;
import com.app.fastprint.ui.payment.paymentResponseModel.GetAddressResponseModel;
import com.app.fastprint.ui.payment.paymentResponseModel.PaymentResponseModel;
import com.app.fastprint.ui.payment.presenter.PPayment;

public class MPayment implements IMPayment {

    IPPayment ipPayment;

    public MPayment(PPayment pPayment) {
        this.ipPayment = pPayment;
    }

    @Override
    public void getPaymentOptionRestCalls() {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.paymentOptionApi(mHandler);
    }

    @Override
    public void getBillingAddressRestCalls(String user_id) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.getBillingAddressApi(user_id, mHandler);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case APIInterface.PAYMENT_OPTION_SUCCESS:
                    PaymentResponseModel paymentResponseModel = ((PaymentResponseModel) msg.obj);
                    ipPayment.successResponseFromModel(paymentResponseModel);
                    break;
                case APIInterface.PAYMENT_OPTION_FAILED:
                    String invalidRequest = String.valueOf(msg.obj);
                    ipPayment.errorResponseFromModel(invalidRequest);
                    break;
                case APIInterface.GET_ADDRESS_SUCCESS:
                    GetAddressResponseModel responseModel = ((GetAddressResponseModel) msg.obj);
                    ipPayment.successAddressResponseFromModel(responseModel);
                    break;
                case APIInterface.GET_ADDRESS_FAILED:
                    String invalidAddressRequest = String.valueOf(msg.obj);
                    ipPayment.errorAddressResponseFromModel(invalidAddressRequest);
                    break;

            }
        }
    };
}
