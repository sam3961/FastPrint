package com.app.fastprint.ui.payment.interfaces;

import com.app.fastprint.ui.payment.paymentResponseModel.GetAddressResponseModel;
import com.app.fastprint.ui.payment.paymentResponseModel.PaymentResponseModel;

public interface IPayment {
    void successResponseFromPresenter(PaymentResponseModel paymentResponseModel);
    void errorResponseFromPresneter(String message);

    void successAddressResponseFromPresenter(GetAddressResponseModel getAddressResponseModel);
    void errorAddressResponseFromPresenter(String message);
}
