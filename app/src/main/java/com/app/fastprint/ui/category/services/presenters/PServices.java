package com.app.fastprint.ui.category.services.presenters;

import com.app.fastprint.ui.category.services.ServicesActivity;
import com.app.fastprint.ui.category.services.interfaces.IMServices;
import com.app.fastprint.ui.category.services.interfaces.IPServices;
import com.app.fastprint.ui.category.services.interfaces.IServices;
import com.app.fastprint.ui.category.services.responseModel.ServicesResponseModel;
import com.app.fastprint.ui.category.services.viewModel.MServices;

public
class PServices implements IPServices {
    IServices iServices;
    IMServices imServices;
    public PServices(ServicesActivity servicesActivity) {
        this.iServices=servicesActivity;
    }

    @Override
    public void getServices() {
        imServices=new MServices(this);
        imServices.servicesRestCalls();
    }

    @Override
    public void successResponseFromModel(ServicesResponseModel servicesResponseModel) {
        iServices.successResponseFromPresenter(servicesResponseModel);
    }

    @Override
    public void errorResponseFromModel(String message) {
        iServices.errorResponseFromPresenter(message);
    }
}
