package com.app.fastprint.ui.address.presenter;

import com.app.fastprint.ui.address.AddressActivity;
import com.app.fastprint.ui.address.interfaces.IAddress;
import com.app.fastprint.ui.address.interfaces.IMAddress;
import com.app.fastprint.ui.address.interfaces.IPAddress;
import com.app.fastprint.ui.address.responseModel.BillingAddressResponseModel;
import com.app.fastprint.ui.address.viewModel.MAddress;
import com.app.fastprint.ui.payment.paymentResponseModel.GetAddressResponseModel;

public class PAddress implements IPAddress {

    IAddress iAddress;
    IMAddress imAddress;
    public PAddress(AddressActivity addressActivity) {
        this.iAddress=addressActivity;
    }

    @Override
    public void addBillingAddress(String user_id, String first_name, String last_name,  String address_1, String address_2, String city_name, String zip_code, String country_name,String state_name) {
        imAddress=new MAddress(this);
        imAddress.addBillingAddressRestCalls(user_id,first_name,last_name,address_1,address_2,city_name,zip_code,country_name,state_name);
    }

    @Override
    public void successResponseFromModel(BillingAddressResponseModel billingAddressResponseModel) {
        iAddress.successResponseFromPresenter(billingAddressResponseModel);
    }

    @Override
    public void errorResponseFromModel(String message) {
        iAddress.errorResponseFromPresenter(message);
    }

    @Override
    public void getBillingAddress(String user_id) {
        imAddress=new MAddress(this);
        imAddress.getBillingAddressRestCalls(user_id);
    }

    @Override
    public void successAddressResponseFromModel(GetAddressResponseModel getAddressResponseModel) {
        iAddress.successAddressResponseFromPresenter(getAddressResponseModel);
    }

    @Override
    public void errorAddressResponseFromModel(String message) {
        iAddress.errorAddressResponseFromPresenter(message);
    }
}
