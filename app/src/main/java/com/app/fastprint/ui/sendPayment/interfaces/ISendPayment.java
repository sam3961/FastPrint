package com.app.fastprint.ui.sendPayment.interfaces;

import com.app.fastprint.ui.sendPayment.sendPaymentResponseModel.SendPaymentResponseModel;

public interface ISendPayment {
    void successResponseFromPresnter(SendPaymentResponseModel sendPaymentResponseModel);
    void failedResponseFromPresnter(String message);
}
