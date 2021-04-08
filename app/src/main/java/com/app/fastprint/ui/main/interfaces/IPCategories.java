package com.app.fastprint.ui.main.interfaces;

import com.app.fastprint.ui.main.responseModel.MenuListResponseModel;

public interface IPCategories {
    void getMenuList();
    void successResponseFromModel(MenuListResponseModel menuListResponseModel);
    void errorResponseFromModel(String message);
}
