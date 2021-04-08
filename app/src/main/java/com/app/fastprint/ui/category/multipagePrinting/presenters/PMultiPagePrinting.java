package com.app.fastprint.ui.category.multipagePrinting.presenters;

import com.app.fastprint.ui.category.multipagePrinting.MultiPagePrintingActivity;
import com.app.fastprint.ui.category.multipagePrinting.interfaces.IMMultiPagePrinting;
import com.app.fastprint.ui.category.multipagePrinting.interfaces.IMultiPagePrinting;
import com.app.fastprint.ui.category.multipagePrinting.interfaces.IPMultiPagePrinting;
import com.app.fastprint.ui.category.multipagePrinting.responseModel.MultiPageFormSubmitResponseModel;
import com.app.fastprint.ui.category.multipagePrinting.responseModel.MultiPagePrintingResponseModel;
import com.app.fastprint.ui.category.multipagePrinting.viewModel.MMultiPagePrinting;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public
class PMultiPagePrinting implements IPMultiPagePrinting {

    IMultiPagePrinting iMultiPagePrinting;
    IMMultiPagePrinting imMultiPagePrinting;

    public PMultiPagePrinting(MultiPagePrintingActivity multiPagePrintingActivity) {
        this.iMultiPagePrinting = multiPagePrintingActivity;
    }

    @Override
    public void getMultiPagePrinting(String id) {
        imMultiPagePrinting = new MMultiPagePrinting(this);
        imMultiPagePrinting.getMultiPagePrintingRestCalls(id);
    }

    @Override
    public void successResponseFromModel(MultiPagePrintingResponseModel multiPagePrintingResponseModel) {
        iMultiPagePrinting.successResponseFromPresenter(multiPagePrintingResponseModel);
    }

    @Override
    public void errorResponseFromModel(String message) {
        iMultiPagePrinting.errorResponseFromPresenter(message);
    }
    @Override
    public void submitMultiPagePrintingFrom(String category_id, String name,
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
        imMultiPagePrinting = new MMultiPagePrinting(this);
        imMultiPagePrinting.submitMultiPagePrintingFromRestCalls(category_id,
                name,
                email,
                company,  phone, job, quantity,
                color_code,
                number_of_color, orientation, size,
                finished_size, open_size,
                slide, finishing, binding, no_of_page,
                cover_page_type, inside_page_type,
                cover_page_gram, inside_page_gram, current_address,
                comment, file_nameRequest,file_name, signature_file);
    }
    @Override
    public void successMultipageSubmitResponseFromModel(MultiPageFormSubmitResponseModel multiPageFormSubmitResponseModel) {
        iMultiPagePrinting.successMultipageSubmitResponseFromPresenter(multiPageFormSubmitResponseModel);
    }
    @Override
    public void errorMultipageSubmitFromModel(String message) {
        iMultiPagePrinting.errorMultipageSubmitFromPresenter(message);
    }
}
