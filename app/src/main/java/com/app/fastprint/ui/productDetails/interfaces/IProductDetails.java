package com.app.fastprint.ui.productDetails.interfaces;

import com.app.fastprint.ui.productDetails.responseModel.ProductDetailsResponseModel;
import com.app.fastprint.ui.productDetails.responseModel.variationResponse.VariationResponseModel;

public
interface IProductDetails {

    void successResponseFromPresenter(ProductDetailsResponseModel productDetailsResponseModel);
    void errorResponseFromPresenter(String message);

    void successPriceResponseFromPresenter(VariationResponseModel priceResponseModel);
    void errorPriceResponseFromPresenter(String message);

}
