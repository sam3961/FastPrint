package com.app.fastprint.ui.category.logo.presenters;

import com.app.fastprint.ui.category.logo.DefinationofLogoActivity;
import com.app.fastprint.ui.category.logo.interfaces.IDefinationofLogo;
import com.app.fastprint.ui.category.logo.interfaces.IMDefinationofLogo;
import com.app.fastprint.ui.category.logo.interfaces.IPDefinationofLogo;
import com.app.fastprint.ui.category.logo.responseModel.DefinationOfLogoResponseModel;
import com.app.fastprint.ui.category.logo.viewModel.MDefinationofLogo;

public
class PDefinationofLogo implements IPDefinationofLogo {

    IDefinationofLogo iDefinationofLogo;
    IMDefinationofLogo imDefinationofLogo;

    public PDefinationofLogo(DefinationofLogoActivity definationofLogoActivity) {
        this.iDefinationofLogo=definationofLogoActivity;
    }

    @Override
    public void getDefinationOfLogo() {
        imDefinationofLogo=new MDefinationofLogo(this);
        imDefinationofLogo.definationOfLogoRestCalls();
    }

    @Override
    public void successResponseFromModel(DefinationOfLogoResponseModel definationOfLogoResponseModel) {
        iDefinationofLogo.successResponseFromPresenter(definationOfLogoResponseModel);
    }

    @Override
    public void errorResponseFromModel(String message) {
        iDefinationofLogo.errorResponseFromPresenter(message);
    }
}
