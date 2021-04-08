package com.app.fastprint.ui.others.interfaces;

import com.app.fastprint.ui.others.responseModel.OtherResponseModel;

public
interface IPOther {
    void getOther();
    void successResponseFromModel(OtherResponseModel otherResponseModel);
    void errorResponseFromModel(String message);
}
