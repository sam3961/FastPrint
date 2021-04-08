package com.app.fastprint.ui.address.interfaces;

import com.app.fastprint.ui.address.responseModel.BillingAddressResponseModel;
import com.app.fastprint.ui.payment.paymentResponseModel.GetAddressResponseModel;

public
interface IAddress {

    void successResponseFromPresenter(BillingAddressResponseModel billingAddressResponseModel);
    void errorResponseFromPresenter(String message);
    void errorAddressResponseFromPresenter(String message);
    void successAddressResponseFromPresenter(GetAddressResponseModel getAddressResponseModel);

}
