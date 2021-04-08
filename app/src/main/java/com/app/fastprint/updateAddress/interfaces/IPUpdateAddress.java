package com.app.fastprint.updateAddress.interfaces;

import com.app.fastprint.ui.address.responseModel.BillingAddressResponseModel;

public
interface IPUpdateAddress {
    void addBillingAddress(String user_id,String first_name,String last_name,
                           String address_1,
                           String address_2, String city_name, String zip_code, String country_name,String state_name);
    void successResponseFromModel(BillingAddressResponseModel billingAddressResponseModel);
    void errorResponseFromModel(String message);
}
