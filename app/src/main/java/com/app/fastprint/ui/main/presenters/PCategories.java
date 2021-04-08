package com.app.fastprint.ui.main.presenters;

import com.app.fastprint.ui.main.MainActivity;
import com.app.fastprint.ui.main.interfaces.ICategories;
import com.app.fastprint.ui.main.interfaces.IMCategories;
import com.app.fastprint.ui.main.interfaces.IPCategories;
import com.app.fastprint.ui.main.responseModel.MenuListResponseModel;
import com.app.fastprint.ui.main.viewModel.MCategories;

public class PCategories implements IPCategories {

    ICategories iCategories;
    IMCategories imCategories;

    public PCategories(MainActivity mainActivity) {
        this.iCategories=mainActivity;

    }

    @Override
    public void getMenuList() {
        imCategories=new MCategories(this);
        imCategories.getMenuListRestCalls();
    }

    @Override
    public void successResponseFromModel(MenuListResponseModel menuListResponseModel) {
        iCategories.successResponseFromPresenter(menuListResponseModel);
    }

    @Override
    public void errorResponseFromModel(String message) {
        iCategories.errorResponseFromPresenter(message);
    }
}
