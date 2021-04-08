package com.app.fastprint.ui.category.extras.interfaces;

import com.app.fastprint.ui.category.extras.responseModel.ExtrasResponseModel;

public interface IExtras {
    void  successResponseFromPresenter(ExtrasResponseModel extrasResponseModel);
    void  errorResponseFromPresenter(String message);
}
