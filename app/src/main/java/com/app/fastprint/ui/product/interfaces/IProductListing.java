package com.app.fastprint.ui.product.interfaces;

import com.app.fastprint.ui.product.responseModel.StoreListingResponseModel;
import com.app.fastprint.ui.product.responseModel.StoreProductResponseModel;

public interface IProductListing {

    void successResponseFromPresenter(StoreListingResponseModel storeResponseModel);
    void errorResponseFromPresenter(String message);

    void successResponseFromPresenter(StoreProductResponseModel storeProductResponseModel);
    void errorStoreResponseFromPresenter(String message);
}
