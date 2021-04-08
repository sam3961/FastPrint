package com.app.fastprint.ui.category.commericalPrinting.viewModel;


import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.app.fastprint.services.APIInterface;
import com.app.fastprint.services.RetrofitCalls;
import com.app.fastprint.ui.category.commericalPrinting.interfaces.IMCommericalPrinting;
import com.app.fastprint.ui.category.commericalPrinting.interfaces.IPCommercialPrinting;
import com.app.fastprint.ui.category.commericalPrinting.presenters.PCommericalPrinting;
import com.app.fastprint.ui.category.commericalPrinting.responseModel.CommercialFromSubmitResponseModel;
import com.app.fastprint.ui.category.commericalPrinting.responseModel.CommericalPrintingResponseModel;

import okhttp3.MultipartBody;

public
class MCommericalPrinting implements IMCommericalPrinting {
    IPCommercialPrinting ipCommercialPrinting;

    public MCommericalPrinting(PCommericalPrinting pCommericalPrinting) {
        this.ipCommercialPrinting = pCommericalPrinting;
    }

    @Override
    public void getCommercialPrintingRestCalls(String category_id) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.commericalPrintingApi(category_id, mHandler);
    }

    @Override
    public void submitCommercialFromRestCalls(String category_id,
                                              String sub_category_id,
                                              String name,
                                              String company,
                                              String email,
                                              String phone,
                                              String job,
                                              String jobs,
                                              String quantity ,
                                              String no_of_sheets,
                                              String pages_per_set,
                                              String no_of_set ,
                                              String side ,
                                              String orientation,
                                              String color_code,
                                              String number_of_color,
                                              String paper_type,
                                              String paper_gram ,
                                              String size,
                                              String finished_size,
                                              String open_size,
                                              String finishing,
                                              String comment,
                                              MultipartBody.Part  file_name,
                                              MultipartBody.Part  signature_file,
                                              String current_address) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.submitCommercialFromApi(category_id,
                sub_category_id,
                name,
                company,
                email,
                phone,
                job,
                jobs,
                quantity,
                no_of_sheets,
                pages_per_set,
                no_of_set,
                side,
                orientation,
                color_code,
                number_of_color ,
                paper_type,
                paper_gram,
                size,
                finished_size,
                open_size,
                finishing,
                comment,
                file_name,
                signature_file,
                current_address,mHandler);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case APIInterface.COMMERICAL_PRINTING_SUCCESS:
                    CommericalPrintingResponseModel commericalPrintingResponseModel = ((CommericalPrintingResponseModel) msg.obj);
                    ipCommercialPrinting.successResponseFromModel(commericalPrintingResponseModel);
                    break;
                case APIInterface.COMMERICAL_PRINTING_FAILED:
                    String invalidRequest = String.valueOf(msg.obj);
                    ipCommercialPrinting.errorResponseFromModel(invalidRequest);
                    break;
                case APIInterface.COMMERICAL_FORM_SUBMIT_SUCCESS:
                    CommercialFromSubmitResponseModel commercialFromSubmitResponseModel = ((CommercialFromSubmitResponseModel) msg.obj);
                    ipCommercialPrinting.successSubmitResponseFromModel(commercialFromSubmitResponseModel);
                    break;
                case APIInterface.COMMERICAL_FORM_SUBMIT_FAILED:
                    String error = String.valueOf(msg.obj);
                    ipCommercialPrinting.errorSubmitResponseFromModel(error);
                    break;

            }
        }
    };
}
