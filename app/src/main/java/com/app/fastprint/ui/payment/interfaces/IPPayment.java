package com.app.fastprint.ui.payment.interfaces;

import com.app.fastprint.ui.payment.paymentResponseModel.GetAddressResponseModel;
import com.app.fastprint.ui.payment.paymentResponseModel.PaymentResponseModel;

public interface IPPayment {
    void getPaymentOption();
    void successResponseFromModel(PaymentResponseModel paymentResponseModel);
    void errorResponseFromModel(String message);

    void getBillingAddress(String user_id);
    void successAddressResponseFromModel(GetAddressResponseModel getAddressResponseModel);
    void errorAddressResponseFromModel(String message);
}
