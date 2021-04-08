package com.app.fastprint.ui.category.commericalPrinting.interfaces;

import okhttp3.MultipartBody;

public
interface IMCommericalPrinting {
    void getCommercialPrintingRestCalls(String category_id);
    void submitCommercialFromRestCalls(
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
}
