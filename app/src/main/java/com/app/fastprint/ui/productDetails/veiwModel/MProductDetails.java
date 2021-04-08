package com.app.fastprint.ui.productDetails.veiwModel;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.app.fastprint.services.APIInterface;
import com.app.fastprint.services.RetrofitCalls;
import com.app.fastprint.ui.productDetails.interfaces.IMProductDetails;
import com.app.fastprint.ui.productDetails.interfaces.IPProductDetails;
import com.app.fastprint.ui.productDetails.presenter.PProductDetails;
import com.app.fastprint.ui.productDetails.responseModel.ProductDetailsResponseModel;
import com.app.fastprint.ui.productDetails.responseModel.variationResponse.VariationResponseModel;

public
class MProductDetails implements IMProductDetails {
    IPProductDetails ipProductDetails;

    public MProductDetails(PProductDetails pProductDetails) {
        this.ipProductDetails = pProductDetails;

    }
    @Override
    public void getProductDetailsRestCalls(String id) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.productDetailsApi(id, mHandler);
    }

    @Override
    public void getPriceRestCalls(String id, String quantity, String pages, String cover) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.priceApi(id, quantity, pages, cover, mHandler);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case APIInterface.PRODUCT_DETAIL_SUCCESS:
                    ProductDetailsResponseModel productDetailsResponseModel = ((ProductDetailsResponseModel) msg.obj);
                    ipProductDetails.successResponseFromModel(productDetailsResponseModel);
                    break;
                case APIInterface.PRODUCT_DETAIL_FAILED:
                    String invalidRequest = String.valueOf(msg.obj);
                    ipProductDetails.errorResponseFromModel(invalidRequest);
                    break;

                case APIInterface.GET_PRICE_SUCCESS:
                    VariationResponseModel priceResponseModel = ((VariationResponseModel) msg.obj);
                    ipProductDetails.successPriceResponseFromModel(priceResponseModel);
                    break;
                case APIInterface.GET_PRICE_FAILED:
                    String invalidPriceRequest = String.valueOf(msg.obj);
                    ipProductDetails.errorPriceResponseFromModel(invalidPriceRequest);
                    break;
            }
        }
    };
}
