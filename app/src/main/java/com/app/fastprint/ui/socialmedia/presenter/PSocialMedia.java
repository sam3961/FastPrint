package com.app.fastprint.ui.socialmedia.presenter;

import com.app.fastprint.ui.socialmedia.SocialMedialActivity;
import com.app.fastprint.ui.socialmedia.interfaces.IMSocialMedia;
import com.app.fastprint.ui.socialmedia.interfaces.IPSocialMedia;
import com.app.fastprint.ui.socialmedia.interfaces.ISocialMedia;
import com.app.fastprint.ui.socialmedia.responseModel.SocialMediaResponseModel;
import com.app.fastprint.ui.socialmedia.viewModel.MSocialMedia;

public
class PSocialMedia implements IPSocialMedia {

    ISocialMedia iSocialMedia;
    IMSocialMedia imSocialMedia;

    public PSocialMedia(SocialMedialActivity socialMedialActivity) {

        this.iSocialMedia=socialMedialActivity;

    }

    @Override
    public void getSocialMedia() {
        imSocialMedia=new MSocialMedia(this);
        imSocialMedia.getSocialMediaRestCalls();
    }

    @Override
    public void successResponseFromModel(SocialMediaResponseModel socialMediaResponseModel) {
        iSocialMedia.successResponseFromPresenters(socialMediaResponseModel);
    }

    @Override
    public void errorResponseFromModel(String message) {
        iSocialMedia.errorResponseFromPresenters(message);
    }
}
