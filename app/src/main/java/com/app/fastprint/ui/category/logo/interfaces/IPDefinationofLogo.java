package com.app.fastprint.ui.category.logo.interfaces;
import com.app.fastprint.ui.category.logo.responseModel.DefinationOfLogoResponseModel;

public interface IPDefinationofLogo {
    void getDefinationOfLogo();
    void successResponseFromModel(DefinationOfLogoResponseModel definationOfLogoResponseModel);
    void errorResponseFromModel(String message);
}
