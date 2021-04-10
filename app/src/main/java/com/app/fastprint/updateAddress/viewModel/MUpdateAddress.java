package com.app.fastprint.updateAddress.viewModel;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.app.fastprint.services.APIInterface;
import com.app.fastprint.services.RetrofitCalls;
import com.app.fastprint.ui.address.responseModel.BillingAddressResponseModel;
import com.app.fastprint.updateAddress.interfaces.IMUpdateAddress;
import com.app.fastprint.updateAddress.interfaces.IPUpdateAddress;
import com.app.fastprint.updateAddress.presenter.PUpdateAddress;

public
class MUpdateAddress  implements IMUpdateAddress {
    IPUpdateAddress ipUpdateAddress;
    public MUpdateAddress(PUpdateAddress pUpdateAddress) {
        this.ipUpdateAddress=pUpdateAddress;
    }

    @Override
    public void addBillingAddressRestCalls(String user_id, String first_name,
                                           String last_name, String address_1, String address_2,
                                           String city_name, String zip_code, String country_name,
                                           String state_name,String phone) {
        RetrofitCalls retrofitCalls=new RetrofitCalls();
        retrofitCalls.addBillingAddressApi(user_id, first_name, last_name,
                address_1, address_2, city_name, zip_code, country_name,state_name, phone,mHandler);
    }
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case APIInterface.BILLING_ADDRESS_SUCCESS:
                    BillingAddressResponseModel billingAddressResponseModel = ((BillingAddressResponseModel) msg.obj);
                    ipUpdateAddress.successResponseFromModel(billingAddressResponseModel);
                    break;
                case APIInterface.BILLING_ADDRESS_FAILED:
                    String error = String.valueOf(msg.obj);
                    ipUpdateAddress.errorResponseFromModel(error);
                    break;

            }
        }
    };
}
