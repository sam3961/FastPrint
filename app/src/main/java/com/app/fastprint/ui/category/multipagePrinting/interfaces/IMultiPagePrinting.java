package com.app.fastprint.ui.category.multipagePrinting.interfaces;

import com.app.fastprint.ui.category.multipagePrinting.responseModel.MultiPageFormSubmitResponseModel;
import com.app.fastprint.ui.category.multipagePrinting.responseModel.MultiPagePrintingResponseModel;

public
interface IMultiPagePrinting {
    void successResponseFromPresenter(MultiPagePrintingResponseModel multiPagePrintingResponseModel);
    void errorResponseFromPresenter(String message);

    void successMultipageSubmitResponseFromPresenter(MultiPageFormSubmitResponseModel multiPageFormSubmitResponseModel);
    void errorMultipageSubmitFromPresenter(String message);
}
