package com.app.fastprint.ui.address.viewModel;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.app.fastprint.services.APIInterface;
import com.app.fastprint.services.RetrofitCalls;
import com.app.fastprint.ui.address.interfaces.IMAddress;
import com.app.fastprint.ui.address.interfaces.IPAddress;
import com.app.fastprint.ui.address.presenter.PAddress;
import com.app.fastprint.ui.address.responseModel.BillingAddressResponseModel;
import com.app.fastprint.ui.payment.paymentResponseModel.GetAddressResponseModel;

public
class MAddress implements IMAddress {

    IPAddress ipAddress;

    public MAddress(PAddress pAddress) {
        this.ipAddress = pAddress;
    }

    @Override
    public void addBillingAddressRestCalls(String user_id, String first_name, String last_name, String address_1, String address_2, String city_name, String zip_code, String country_name, String state_name) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.addBillingAddressApi(user_id, first_name, last_name, address_1, address_2, city_name, zip_code, country_name, state_name, mHandler);
    }

    @Override
    public void getBillingAddressRestCalls(String user_id) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.getBillingAddressApi(user_id, mHandler);

    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case APIInterface.BILLING_ADDRESS_SUCCESS:
                    BillingAddressResponseModel billingAddressResponseModel = ((BillingAddressResponseModel) msg.obj);
                    ipAddress.successResponseFromModel(billingAddressResponseModel);
                    break;
                case APIInterface.BILLING_ADDRESS_FAILED:
                    String error = String.valueOf(msg.obj);
                    ipAddress.errorResponseFromModel(error);
                    break;
                case APIInterface.GET_ADDRESS_SUCCESS:
                    GetAddressResponseModel responseModel = ((GetAddressResponseModel) msg.obj);
                    ipAddress.successAddressResponseFromModel(responseModel);
                    break;
                case APIInterface.GET_ADDRESS_FAILED:
                    String invalidAddressRequest = String.valueOf(msg.obj);
                    ipAddress.errorAddressResponseFromModel(invalidAddressRequest);
                    break;


            }
        }
    };
}
