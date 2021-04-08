package com.app.fastprint.updateAddress.interfaces;

import com.app.fastprint.ui.address.responseModel.BillingAddressResponseModel;

public
interface IUpdateAddress {
    void successResponseFromPresenter(BillingAddressResponseModel billingAddressResponseModel);
    void errorResponseFromPresenter(String message);
}
