package com.app.fastprint.ui.socialmedia.interfaces;

import com.app.fastprint.ui.socialmedia.responseModel.SocialMediaResponseModel;

public interface ISocialMedia {

    void successResponseFromPresenters(SocialMediaResponseModel socialMediaResponseModel);
    void errorResponseFromPresenters(String message);
}
