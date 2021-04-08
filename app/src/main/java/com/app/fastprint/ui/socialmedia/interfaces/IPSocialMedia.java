package com.app.fastprint.ui.socialmedia.interfaces;
import com.app.fastprint.ui.socialmedia.responseModel.SocialMediaResponseModel;

public interface IPSocialMedia {
    void getSocialMedia();
    void successResponseFromModel(SocialMediaResponseModel socialMediaResponseModel);
    void errorResponseFromModel(String message);
}
