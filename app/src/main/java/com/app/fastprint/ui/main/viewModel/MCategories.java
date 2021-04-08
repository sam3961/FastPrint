package com.app.fastprint.ui.main.viewModel;

import android.os.Handler;
import android.os.Message;

import com.app.fastprint.services.APIInterface;
import com.app.fastprint.services.RetrofitCalls;
import com.app.fastprint.ui.main.interfaces.IMCategories;
import com.app.fastprint.ui.main.interfaces.IPCategories;
import com.app.fastprint.ui.main.presenters.PCategories;
import com.app.fastprint.ui.main.responseModel.MenuListResponseModel;

public class MCategories implements IMCategories {
    IPCategories ipCategories;
    public MCategories(PCategories pCategories) {
        this.ipCategories=pCategories;

    }

    @Override
    public void getMenuListRestCalls() {
        RetrofitCalls retrofitCalls=new RetrofitCalls();
        retrofitCalls.menuListApi(mHandler);
    }
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case APIInterface.MENU_LIST_SUCCESS:
                    MenuListResponseModel menuListResponseModel = ((MenuListResponseModel) msg.obj);
                    ipCategories.successResponseFromModel(menuListResponseModel);
                    break;
                case APIInterface.MENU_LIST_FAILED:
                    String invalidRequest = String.valueOf(msg.obj);
                    ipCategories.errorResponseFromModel(invalidRequest);
                    break;

            }
        }
    };
}
