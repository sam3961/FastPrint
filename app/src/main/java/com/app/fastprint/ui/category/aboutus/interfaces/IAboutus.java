package com.app.fastprint.ui.category.aboutus.interfaces;

import com.app.fastprint.ui.category.aboutus.responseModel.AboutUsResponseModel;

public interface IAboutus {
    void successResponseFromPresenter(AboutUsResponseModel aboutUsResponseModel);
    void errorResponseFromPresenter(String message);
}
