package com.app.fastprint.ui.category.multipagePrinting.viewModel;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.app.fastprint.services.APIInterface;
import com.app.fastprint.services.RetrofitCalls;
import com.app.fastprint.ui.category.multipagePrinting.interfaces.IMMultiPagePrinting;
import com.app.fastprint.ui.category.multipagePrinting.interfaces.IPMultiPagePrinting;
import com.app.fastprint.ui.category.multipagePrinting.presenters.PMultiPagePrinting;
import com.app.fastprint.ui.category.multipagePrinting.responseModel.MultiPageFormSubmitResponseModel;
import com.app.fastprint.ui.category.multipagePrinting.responseModel.MultiPagePrintingResponseModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public
class MMultiPagePrinting implements IMMultiPagePrinting {

    IPMultiPagePrinting ipMultiPagePrinting;

    public MMultiPagePrinting(PMultiPagePrinting pMultiPagePrinting) {
        this.ipMultiPagePrinting = pMultiPagePrinting;
    }

    @Override
    public void getMultiPagePrintingRestCalls(String id) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.multipagePrintingApi(id, mHandler);
    }

    @Override
    public void submitMultiPagePrintingFromRestCalls(String category_id, String name,
                                                     String email, String company,
                                                     String phone, String job, String quantity,
                                                     String color_code, String number_of_color,
                                                     String orientation, String size,
                                                     String finished_size, String open_size,
                                                     String slide, String finishing,
                                                     String binding, String no_of_page,
                                                     String cover_page_type, String inside_page_type,
                                                     String cover_page_gram, String inside_page_gram,
                                                     String current_address, String comment,
                                                     RequestBody file_nameRequest,
                                                     MultipartBody.Part file_name, MultipartBody.Part signature_file) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.submitMultiPageFromApi(category_id,
                name,
                email,
                company,  phone, job, quantity,
                color_code,
                number_of_color, orientation, size,
                finished_size, open_size,
                slide, finishing, binding, no_of_page,
                cover_page_type, inside_page_type,
                cover_page_gram, inside_page_gram, current_address,
                comment, file_nameRequest,file_name, signature_file,mHandler);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case APIInterface.MULTIPAGE_PRINTING_SUCCESS:
                    MultiPagePrintingResponseModel multiPagePrintingResponseModel = ((MultiPagePrintingResponseModel) msg.obj);
                    ipMultiPagePrinting.successResponseFromModel(multiPagePrintingResponseModel);
                    break;
                case APIInterface.MULTIPAGE_PRINTING_FAILED:
                    String invalidReq = String.valueOf(msg.obj);
                    ipMultiPagePrinting.errorResponseFromModel(invalidReq);
                    break;
                case APIInterface.MULTIPAGE_FORM_SUBMIT_SUCCESS:
                    MultiPageFormSubmitResponseModel multiPageFormSubmitResponseModel = ((MultiPageFormSubmitResponseModel) msg.obj);
                    ipMultiPagePrinting.successMultipageSubmitResponseFromModel(multiPageFormSubmitResponseModel);
                    break;
                case APIInterface.MULTIPAGE_FORM_SUBMIT_FAILED:
                    String error= String.valueOf(msg.obj);
                    ipMultiPagePrinting.errorMultipageSubmitFromModel(error);
                    break;
            }
        }
    };
}
