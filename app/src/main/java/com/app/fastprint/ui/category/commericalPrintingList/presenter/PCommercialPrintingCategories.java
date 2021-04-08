package com.app.fastprint.ui.category.commericalPrintingList.presenter;

import com.app.fastprint.ui.category.commericalPrintingList.CommercialPrintingCategoriesActivity;
import com.app.fastprint.ui.category.commericalPrintingList.interfaces.ICPrintingCategory;
import com.app.fastprint.ui.category.commericalPrintingList.interfaces.IMCPrintingCategory;
import com.app.fastprint.ui.category.commericalPrintingList.interfaces.IPCPrintingCategory;
import com.app.fastprint.ui.category.commericalPrintingList.responseModel.CommercialPrintingCategoriesResponseModel;
import com.app.fastprint.ui.category.commericalPrintingList.viewModel.MCommercialPrintingCategories;

public
class PCommercialPrintingCategories implements IPCPrintingCategory {

    ICPrintingCategory icPrintingCategory;
    IMCPrintingCategory imcPrintingCategory;

    public PCommercialPrintingCategories(CommercialPrintingCategoriesActivity commercialPrintingCategoriesActivity) {
        this.icPrintingCategory=commercialPrintingCategoriesActivity;
    }

    @Override
    public void getCPrintingCategory(String id) {
        imcPrintingCategory=new MCommercialPrintingCategories(this);
        imcPrintingCategory.getCPrintingCategoryRestsCalls(id);
    }

    @Override
    public void successResponseFromModel(CommercialPrintingCategoriesResponseModel commercialPrintingCategoriesResponseModel) {
        icPrintingCategory.successResponseFromPresenter(commercialPrintingCategoriesResponseModel);
    }

    @Override
    public void errorResponseFromModel(String message) {
        icPrintingCategory.errorResponseFromPresenter(message);
    }
}
