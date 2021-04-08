package com.app.fastprint.ui.payment.presenter;

import com.app.fastprint.ui.payment.PaymentActivity;
import com.app.fastprint.ui.payment.interfaces.IMPayment;
import com.app.fastprint.ui.payment.interfaces.IPPayment;
import com.app.fastprint.ui.payment.interfaces.IPayment;
import com.app.fastprint.ui.payment.paymentResponseModel.GetAddressResponseModel;
import com.app.fastprint.ui.payment.paymentResponseModel.PaymentResponseModel;
import com.app.fastprint.ui.payment.viewModel.MPayment;

public class PPayment implements IPPayment {
    IPayment iPayment;
    IMPayment imPayment;

    public PPayment(PaymentActivity paymentActivity) {
        this.iPayment=paymentActivity;
    }

    @Override
    public void getPaymentOption() {
        imPayment=new MPayment(this);
        imPayment.getPaymentOptionRestCalls();
    }

    @Override
    public void successResponseFromModel(PaymentResponseModel paymentResponseModel) {
        iPayment.successResponseFromPresenter(paymentResponseModel);
    }

    @Override
    public void errorResponseFromModel(String message) {
        iPayment.errorResponseFromPresneter(message);
    }

    @Override
    public void getBillingAddress(String user_id) {
        imPayment=new MPayment(this);
        imPayment.getBillingAddressRestCalls(user_id);
    }

    @Override
    public void successAddressResponseFromModel(GetAddressResponseModel getAddressResponseModel) {
        iPayment.successAddressResponseFromPresenter(getAddressResponseModel);
    }

    @Override
    public void errorAddressResponseFromModel(String message) {
        iPayment.errorAddressResponseFromPresenter(message);
    }
}
