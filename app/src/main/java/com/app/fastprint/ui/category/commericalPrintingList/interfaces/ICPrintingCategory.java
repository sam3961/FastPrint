package com.app.fastprint.ui.category.commericalPrintingList.interfaces;

import com.app.fastprint.ui.category.commericalPrintingList.responseModel.CommercialPrintingCategoriesResponseModel;

public
interface ICPrintingCategory {
    void successResponseFromPresenter(CommercialPrintingCategoriesResponseModel commercialPrintingCategoriesResponseModel);
    void errorResponseFromPresenter(String message);
}
