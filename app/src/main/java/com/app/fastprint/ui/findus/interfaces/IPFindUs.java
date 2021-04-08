package com.app.fastprint.ui.findus.interfaces;

import com.app.fastprint.ui.findus.responseModel.FindUsResponseModel;

public interface IPFindUs {
    void findUs();
    void successResponseFromModel(FindUsResponseModel findUsResponseModel);
    void errorResponseFromModel(String message);
}
