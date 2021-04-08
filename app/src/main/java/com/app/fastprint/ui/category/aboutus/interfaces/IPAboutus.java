package com.app.fastprint.ui.category.aboutus.interfaces;

import com.app.fastprint.ui.category.aboutus.responseModel.AboutUsResponseModel;

public interface IPAboutus {
    void getAboutUs();
    void successResponseFromModel(AboutUsResponseModel aboutUsResponseModel);
    void errorResponseFromModel(String message);
}
