package com.app.fastprint.ui.category.services.interfaces;

import com.app.fastprint.ui.category.services.responseModel.ServicesResponseModel;

public interface IPServices {
    void getServices();
    void successResponseFromModel(ServicesResponseModel servicesResponseModel);
    void errorResponseFromModel(String message);
}
