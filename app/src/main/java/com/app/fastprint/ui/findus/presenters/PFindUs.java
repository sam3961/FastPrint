package com.app.fastprint.ui.findus.presenters;


import com.app.fastprint.ui.findus.FindUsActivity;
import com.app.fastprint.ui.findus.interfaces.IFindUs;
import com.app.fastprint.ui.findus.interfaces.IMFindUs;
import com.app.fastprint.ui.findus.interfaces.IPFindUs;
import com.app.fastprint.ui.findus.responseModel.FindUsResponseModel;
import com.app.fastprint.ui.findus.viewModel.MFindUs;

public
class PFindUs implements IPFindUs {

    IFindUs iFindUs;
    IMFindUs imFindUs;

    public PFindUs(FindUsActivity findUsActivity) {
        this.iFindUs=findUsActivity;
    }

    @Override
    public void findUs() {
        imFindUs=new MFindUs(this);
        imFindUs.findUsRestCalls();
    }

    @Override
    public void successResponseFromModel(FindUsResponseModel findUsResponseModel) {
        iFindUs.successResponseFromPresenter(findUsResponseModel);
    }

    @Override
    public void errorResponseFromModel(String message) {
        iFindUs.errorResponseFromPresenter(message);
    }
}
