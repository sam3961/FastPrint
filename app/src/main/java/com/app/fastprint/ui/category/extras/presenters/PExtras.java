package com.app.fastprint.ui.category.extras.presenters;

import com.app.fastprint.ui.category.extras.ExtrasActivity;
import com.app.fastprint.ui.category.extras.interfaces.IExtras;
import com.app.fastprint.ui.category.extras.interfaces.IMExtras;
import com.app.fastprint.ui.category.extras.interfaces.IPExtras;
import com.app.fastprint.ui.category.extras.responseModel.ExtrasResponseModel;
import com.app.fastprint.ui.category.extras.viewModel.MExtras;

public
class PExtras implements IPExtras {

    IExtras iExtras;
    IMExtras imExtras;

    public PExtras(ExtrasActivity extrasActivity) {
        this.iExtras=extrasActivity;
    }

    @Override
    public void getExtras() {
        imExtras=new MExtras(this);
        imExtras.getExtrasRestCalls();
    }

    @Override
    public void successResponseFromModel(ExtrasResponseModel extrasResponseModel) {
        iExtras.successResponseFromPresenter(extrasResponseModel);
    }

    @Override
    public void errorResponseFromModel(String message) {
        iExtras.errorResponseFromPresenter(message);
    }
}
