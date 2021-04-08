package com.app.fastprint.ui.sendPayment.interfaces;

import com.app.fastprint.ui.sendPayment.sendPaymentResponseModel.SendPaymentResponseModel;

public interface IPSendPayment {
    void getPaymentList();
    void successResponseFromModel(SendPaymentResponseModel sendPaymentResponseModel);
    void failedResponseFromModel(String message);
}
