package com.app.fastprint.ui.sendPayment.presenter;

import com.app.fastprint.ui.sendPayment.SendPaymentActivity;
import com.app.fastprint.ui.sendPayment.interfaces.IMSendPayment;
import com.app.fastprint.ui.sendPayment.interfaces.IPSendPayment;
import com.app.fastprint.ui.sendPayment.interfaces.ISendPayment;
import com.app.fastprint.ui.sendPayment.sendPaymentResponseModel.SendPaymentResponseModel;
import com.app.fastprint.ui.sendPayment.viewModel.MSendPayment;

public
class PSendPayment  implements IPSendPayment {

    ISendPayment iSendPayment;
    IMSendPayment imSendPayment;

    public PSendPayment(SendPaymentActivity sendPaymentActivity) {
        this.iSendPayment=sendPaymentActivity;
    }

    @Override
    public void getPaymentList() {
        imSendPayment=new MSendPayment(this);
        imSendPayment.getPaymentListRestCalls();
    }

    @Override
    public void successResponseFromModel(SendPaymentResponseModel sendPaymentResponseModel) {
        iSendPayment.successResponseFromPresnter(sendPaymentResponseModel);
    }

    @Override
    public void failedResponseFromModel(String message) {
        iSendPayment.failedResponseFromPresnter(message);
    }
}
