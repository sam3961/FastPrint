package com.app.fastprint.ui.product.viewModel;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.app.fastprint.services.APIInterface;
import com.app.fastprint.services.RetrofitCalls;
import com.app.fastprint.ui.product.interfaces.IMProductListing;
import com.app.fastprint.ui.product.interfaces.IPProductListing;
import com.app.fastprint.ui.product.presenters.PProductListing;
import com.app.fastprint.ui.product.responseModel.StoreListingResponseModel;
import com.app.fastprint.ui.product.responseModel.StoreProductResponseModel;

public
class MProductListing implements IMProductListing {

    IPProductListing ipProductListing;

    public MProductListing(PProductListing pProductListing) {
        this.ipProductListing = pProductListing;
    }

    @Override
    public void getStoreRestCall() {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.storeListingApi(mHandler);
    }

    @Override
    public void getStroeProductRestCall(String id) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.storeProductApi(id, mHandler);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case APIInterface.STORE_LISTING_SUCCESS:
                    StoreListingResponseModel storeListingResponseModel = ((StoreListingResponseModel) msg.obj);
                    ipProductListing.successResponseFromModel(storeListingResponseModel);
                    break;
                case APIInterface.STORE_LISTING_FAILED:
                    String invalidRequest = String.valueOf(msg.obj);
                    ipProductListing.errorResponseFromModel(invalidRequest);
                    break;
                case APIInterface.STORE_PRODUTC_SUCCESS:
                    StoreProductResponseModel storeProductResponseModel = ((StoreProductResponseModel) msg.obj);
                    ipProductListing.successResponseFromModel(storeProductResponseModel);
                    break;
                case APIInterface.STORE_PRODUTC_FAILED:
                    String storeInvalidReq = String.valueOf(msg.obj);
                    ipProductListing.errorStoreResponseFromModel(storeInvalidReq);
                    break;

            }
        }
    };
}
