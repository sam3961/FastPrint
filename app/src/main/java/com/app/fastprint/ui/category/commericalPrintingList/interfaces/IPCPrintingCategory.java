package com.app.fastprint.ui.category.commericalPrintingList.interfaces;

import com.app.fastprint.ui.category.commericalPrintingList.responseModel.CommercialPrintingCategoriesResponseModel;

public
interface IPCPrintingCategory {
    void getCPrintingCategory(String id);
    void successResponseFromModel(CommercialPrintingCategoriesResponseModel commercialPrintingCategoriesResponseModel);
    void errorResponseFromModel(String message);
}
