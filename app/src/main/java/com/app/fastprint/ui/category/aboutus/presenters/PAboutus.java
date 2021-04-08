package com.app.fastprint.ui.category.aboutus.presenters;

import com.app.fastprint.ui.category.aboutus.AboutusActivity;
import com.app.fastprint.ui.category.aboutus.interfaces.IAboutus;
import com.app.fastprint.ui.category.aboutus.interfaces.IMAboutus;
import com.app.fastprint.ui.category.aboutus.interfaces.IPAboutus;
import com.app.fastprint.ui.category.aboutus.responseModel.AboutUsResponseModel;
import com.app.fastprint.ui.category.aboutus.viewModel.MAboutus;

public
class PAboutus implements IPAboutus {
    IAboutus iAboutus;
    IMAboutus imAboutus;

    public PAboutus(AboutusActivity aboutusActivity) {
        this.iAboutus = aboutusActivity;
    }

    @Override
    public void getAboutUs() {
        imAboutus = new MAboutus(this);
        imAboutus.aboutUsRestCalls();
    }

    @Override
    public void successResponseFromModel(AboutUsResponseModel aboutUsResponseModel) {
        iAboutus.successResponseFromPresenter(aboutUsResponseModel);
    }

    @Override
    public void errorResponseFromModel(String message) {
        iAboutus.errorResponseFromPresenter(message);
    }
}
