package com.app.fastprint.ui.main.interfaces;

import com.app.fastprint.ui.main.responseModel.MenuListResponseModel;

public interface ICategories {
void successResponseFromPresenter(MenuListResponseModel menuListResponseModel);
void errorResponseFromPresenter(String message);
}
