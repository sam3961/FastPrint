package com.app.fastprint.ui.category.commericalPrintingList.viewModel;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.NonNull;
import com.app.fastprint.services.APIInterface;
import com.app.fastprint.services.RetrofitCalls;
import com.app.fastprint.ui.category.commericalPrintingList.interfaces.IMCPrintingCategory;
import com.app.fastprint.ui.category.commericalPrintingList.interfaces.IPCPrintingCategory;
import com.app.fastprint.ui.category.commericalPrintingList.presenter.PCommercialPrintingCategories;
import com.app.fastprint.ui.category.commericalPrintingList.responseModel.CommercialPrintingCategoriesResponseModel;

public
class MCommercialPrintingCategories implements IMCPrintingCategory {

    IPCPrintingCategory ipcPrintingCategory;

    public MCommercialPrintingCategories(PCommercialPrintingCategories pCommercialPrintingCategories) {
        this.ipcPrintingCategory=pCommercialPrintingCategories;
    }

    @Override
    public void getCPrintingCategoryRestsCalls(String id) {
        RetrofitCalls retrofitCalls=new RetrofitCalls();
        retrofitCalls.commercialPrintingCategoriesApi(id,mHandler);
    }
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case APIInterface.PRINTING_CATEGORIES_SUCCESS:
                    CommercialPrintingCategoriesResponseModel responseModel = ((CommercialPrintingCategoriesResponseModel) msg.obj);
                    ipcPrintingCategory.successResponseFromModel(responseModel);
                    break;
                case APIInterface.PRINTING_CATEGORIES_FAILED:
                    String invalidRequest = String.valueOf(msg.obj);
                    ipcPrintingCategory.errorResponseFromModel(invalidRequest);
                    break;

            }
        }
    };
}