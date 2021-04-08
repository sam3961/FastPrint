package com.app.fastprint.ui.others.presenter;

import com.app.fastprint.ui.others.OthersActivity;
import com.app.fastprint.ui.others.interfaces.IMOther;
import com.app.fastprint.ui.others.interfaces.IOther;
import com.app.fastprint.ui.others.interfaces.IPOther;
import com.app.fastprint.ui.others.responseModel.OtherResponseModel;
import com.app.fastprint.ui.others.viewModel.MOther;

public
class POther implements IPOther {
    IOther iOther;
    IMOther imOther;
    public POther(OthersActivity othersActivity) {
        this.iOther=othersActivity;
    }

    @Override
    public void getOther() {
        imOther=new MOther(this);
        imOther.otherRestCalls();
    }

    @Override
    public void successResponseFromModel(OtherResponseModel otherResponseModel) {
        iOther.successResponseFromPresenter(otherResponseModel);
    }

    @Override
    public void errorResponseFromModel(String message) {
        iOther.errorResponseFromPresenter(message);
    }
}
