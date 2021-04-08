package com.app.fastprint.ui.others.interfaces;

import com.app.fastprint.ui.others.responseModel.OtherResponseModel;

public
interface IOther {
    void successResponseFromPresenter(OtherResponseModel otherResponseModel);
    void errorResponseFromPresenter(String message);
}
