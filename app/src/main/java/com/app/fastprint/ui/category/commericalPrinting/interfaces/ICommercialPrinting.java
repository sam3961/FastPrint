package com.app.fastprint.ui.category.commericalPrinting.interfaces;

import com.app.fastprint.ui.category.commericalPrinting.responseModel.CommercialFromSubmitResponseModel;
import com.app.fastprint.ui.category.commericalPrinting.responseModel.CommericalPrintingResponseModel;

public
interface ICommercialPrinting {

    // get forms values
    void successResponseFromPresenter(CommericalPrintingResponseModel commericalPrintingResponseModel);
    void errorResponseFromPresenter(String message);

    // submitted forms values
    void successSubmitResponseFromPresenter(CommercialFromSubmitResponseModel commercialFromSubmitResponseModel);
    void errorSubmitResponseFromPresenter(String message);
}
