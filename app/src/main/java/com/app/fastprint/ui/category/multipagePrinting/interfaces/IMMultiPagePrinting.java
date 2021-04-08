package com.app.fastprint.ui.category.multipagePrinting.interfaces;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public
interface IMMultiPagePrinting {
    void getMultiPagePrintingRestCalls(String id);

    void submitMultiPagePrintingFromRestCalls(String category_id, String name,
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
                                              MultipartBody.Part file_name, MultipartBody.Part signature_file);

}
