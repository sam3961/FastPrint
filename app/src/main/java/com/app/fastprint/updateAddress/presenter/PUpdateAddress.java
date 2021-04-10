package com.app.fastprint.updateAddress.presenter;

import com.app.fastprint.ui.address.responseModel.BillingAddressResponseModel;
import com.app.fastprint.updateAddress.UpdateAddressActivity;
import com.app.fastprint.updateAddress.interfaces.IMUpdateAddress;
import com.app.fastprint.updateAddress.interfaces.IPUpdateAddress;
import com.app.fastprint.updateAddress.interfaces.IUpdateAddress;
import com.app.fastprint.updateAddress.viewModel.MUpdateAddress;

public
class PUpdateAddress implements IPUpdateAddress {
    IUpdateAddress ipUpdateAddress;
    IMUpdateAddress imUpdateAddress;
    public PUpdateAddress(UpdateAddressActivity updateAddressActivity) {
        this.ipUpdateAddress=updateAddressActivity;
    }

    @Override
    public void addBillingAddress(String user_id, String first_name, String last_name, String address_1,
                                  String address_2, String city_name, String zip_code, String country_name,
                                  String state_name,String phone) {
        imUpdateAddress=new MUpdateAddress(this);
        imUpdateAddress.addBillingAddressRestCalls(user_id,first_name,last_name,address_1,address_2,
                city_name,zip_code,country_name,state_name,phone);
    }

    @Override
    public void successResponseFromModel(BillingAddressResponseModel billingAddressResponseModel) {
        ipUpdateAddress.successResponseFromPresenter(billingAddressResponseModel);
    }

    @Override
    public void errorResponseFromModel(String message) {
        ipUpdateAddress.errorResponseFromPresenter(message);
    }
}
