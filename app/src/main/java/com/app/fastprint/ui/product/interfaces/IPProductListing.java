package com.app.fastprint.ui.product.interfaces;

import com.app.fastprint.ui.product.responseModel.StoreListingResponseModel;
import com.app.fastprint.ui.product.responseModel.StoreProductResponseModel;

public interface IPProductListing {
    void getStore();
    void successResponseFromModel(StoreListingResponseModel storeResponseModel);
    void errorResponseFromModel(String message);

    void getStoreProduct(String id);
    void successResponseFromModel(StoreProductResponseModel storeProductResponseModel);
    void errorStoreResponseFromModel(String message);
}
