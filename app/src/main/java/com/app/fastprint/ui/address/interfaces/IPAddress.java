package com.app.fastprint.ui.address.interfaces;

import com.app.fastprint.ui.address.responseModel.BillingAddressResponseModel;
import com.app.fastprint.ui.payment.paymentResponseModel.GetAddressResponseModel;

public
interface IPAddress {
    void addBillingAddress(String user_id,String first_name,String last_name,
                          String address_1,
                           String address_2, String city_name, String zip_code, String country_name,
                           String state_name,String phone);
    void successResponseFromModel(BillingAddressResponseModel billingAddressResponseModel);
    void errorResponseFromModel(String message);
    void getBillingAddress(String user_id);
    void successAddressResponseFromModel(GetAddressResponseModel getAddressResponseModel);
    void errorAddressResponseFromModel(String message);

}
