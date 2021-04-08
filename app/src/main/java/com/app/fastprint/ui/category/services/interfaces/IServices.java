package com.app.fastprint.ui.category.services.interfaces;

import com.app.fastprint.ui.category.services.responseModel.ServicesResponseModel;

public interface IServices {
    void successResponseFromPresenter(ServicesResponseModel servicesResponseModel);
    void errorResponseFromPresenter(String message);
}
