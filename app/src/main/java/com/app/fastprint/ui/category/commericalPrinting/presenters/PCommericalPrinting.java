package com.app.fastprint.ui.category.commericalPrinting.presenters;

import com.app.fastprint.ui.category.commericalPrinting.CommercialPrintingActivity;
import com.app.fastprint.ui.category.commericalPrinting.interfaces.ICommercialPrinting;
import com.app.fastprint.ui.category.commericalPrinting.interfaces.IMCommericalPrinting;
import com.app.fastprint.ui.category.commericalPrinting.interfaces.IPCommercialPrinting;
import com.app.fastprint.ui.category.commericalPrinting.responseModel.CommercialFromSubmitResponseModel;
import com.app.fastprint.ui.category.commericalPrinting.responseModel.CommericalPrintingResponseModel;
import com.app.fastprint.ui.category.commericalPrinting.viewModel.MCommericalPrinting;

import okhttp3.MultipartBody;

public
class PCommericalPrinting implements IPCommercialPrinting {

    ICommercialPrinting iCommercialPrinting;
    IMCommericalPrinting imCommericalPrinting;

    public PCommericalPrinting(CommercialPrintingActivity commercialPrintingActivity) {
        this.iCommercialPrinting = commercialPrintingActivity;

    }

    @Override
    public void getCommercialPrinting(String category_id) {
        imCommericalPrinting = new MCommericalPrinting(this);
        imCommericalPrinting.getCommercialPrintingRestCalls(category_id);
    }

    @Override
    public void successResponseFromModel(CommericalPrintingResponseModel commericalPrintingResponseModel) {
        iCommercialPrinting.successResponseFromPresenter(commericalPrintingResponseModel);
    }

    @Override
    public void errorResponseFromModel(String message) {
        iCommercialPrinting.errorResponseFromPresenter(message);
    }

    @Override
    public void submitCommercialForm(
            String category_id,
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
        imCommericalPrinting = new MCommericalPrinting(this);
        imCommericalPrinting.submitCommercialFromRestCalls(category_id,
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
                current_address);

    }

    @Override
    public void successSubmitResponseFromModel(CommercialFromSubmitResponseModel commercialFromSubmitResponseModel) {
        iCommercialPrinting.successSubmitResponseFromPresenter(commercialFromSubmitResponseModel);
    }

    @Override
    public void errorSubmitResponseFromModel(String message) {
        iCommercialPrinting.errorSubmitResponseFromPresenter(message);
    }
}
