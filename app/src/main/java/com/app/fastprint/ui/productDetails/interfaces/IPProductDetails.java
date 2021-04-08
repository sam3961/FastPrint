package com.app.fastprint.ui.productDetails.interfaces;

import com.app.fastprint.ui.productDetails.responseModel.ProductDetailsResponseModel;
import com.app.fastprint.ui.productDetails.responseModel.variationResponse.VariationResponseModel;

public
interface IPProductDetails {
    void getProductDetails(String id);
    void successResponseFromModel(ProductDetailsResponseModel productDetailsResponseModel);
    void errorResponseFromModel(String message);

    void getPrice(String id,String quantity,String pages,String cover);
    void successPriceResponseFromModel(VariationResponseModel priceResponseModel);
    void errorPriceResponseFromModel(String message);
}
