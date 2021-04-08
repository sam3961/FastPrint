package com.app.fastprint.ui.category.extras.interfaces;

import com.app.fastprint.ui.category.extras.responseModel.ExtrasResponseModel;

public interface IPExtras {
    void  getExtras();
    void  successResponseFromModel(ExtrasResponseModel extrasResponseModel);
    void  errorResponseFromModel(String message);
}
