package com.app.fastprint.ui.findus.interfaces;
import com.app.fastprint.ui.findus.responseModel.FindUsResponseModel;

public interface IFindUs {
    void successResponseFromPresenter(FindUsResponseModel findUsResponseModel);
    void errorResponseFromPresenter(String message);
}
