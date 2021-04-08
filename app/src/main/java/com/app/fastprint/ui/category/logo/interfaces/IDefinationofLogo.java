package com.app.fastprint.ui.category.logo.interfaces;
import com.app.fastprint.ui.category.logo.responseModel.DefinationOfLogoResponseModel;

public interface IDefinationofLogo {
    void successResponseFromPresenter(DefinationOfLogoResponseModel definationOfLogoResponseModel);
    void errorResponseFromPresenter(String message);
}
