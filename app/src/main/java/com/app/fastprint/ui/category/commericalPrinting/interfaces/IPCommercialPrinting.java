package com.app.fastprint.ui.category.commericalPrinting.interfaces;

import com.app.fastprint.ui.category.commericalPrinting.responseModel.CommercialFromSubmitResponseModel;
import com.app.fastprint.ui.category.commericalPrinting.responseModel.CommericalPrintingResponseModel;

import okhttp3.MultipartBody;

public
interface IPCommercialPrinting {
    void getCommercialPrinting(String category_id);

    void successResponseFromModel(CommericalPrintingResponseModel commericalPrintingResponseModel);

    void errorResponseFromModel(String message);


    // submitted forms values
    void submitCommercialForm(
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
            String current_address);
    void successSubmitResponseFromModel(CommercialFromSubmitResponseModel commercialFromSubmitResponseModel);
    void errorSubmitResponseFromModel(String message);
}
