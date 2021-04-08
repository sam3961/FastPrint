package com.app.fastprint.ui.product.presenters;

import com.app.fastprint.ui.product.ProductListingActivity;
import com.app.fastprint.ui.product.interfaces.IMProductListing;
import com.app.fastprint.ui.product.interfaces.IPProductListing;
import com.app.fastprint.ui.product.interfaces.IProductListing;
import com.app.fastprint.ui.product.responseModel.StoreListingResponseModel;
import com.app.fastprint.ui.product.responseModel.StoreProductResponseModel;
import com.app.fastprint.ui.product.viewModel.MProductListing;

public
class PProductListing implements IPProductListing {

    IProductListing iProductListing;
    IMProductListing imProductListing;

    public PProductListing(ProductListingActivity productListingActivity) {
        this.iProductListing=productListingActivity;
    }

    @Override
    public void getStore() {
        imProductListing=new MProductListing(this);
        imProductListing.getStoreRestCall();
    }

    @Override
    public void successResponseFromModel(StoreListingResponseModel storeResponseModel) {
        iProductListing.successResponseFromPresenter(storeResponseModel);
    }

    @Override
    public void errorResponseFromModel(String message) {
        iProductListing.errorResponseFromPresenter(message);
    }

    @Override
    public void getStoreProduct(String id) {
        imProductListing=new MProductListing(this);
        imProductListing.getStroeProductRestCall(id);
    }

    @Override
    public void successResponseFromModel(StoreProductResponseModel storeProductResponseModel) {
        iProductListing.successResponseFromPresenter(storeProductResponseModel);
    }

    @Override
    public void errorStoreResponseFromModel(String message) {
        iProductListing.errorStoreResponseFromPresenter(message);
    }
}
