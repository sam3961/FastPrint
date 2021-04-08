package com.app.fastprint.ui.socialmedia.viewModel;
import android.os.Handler;
import android.os.Message;
import com.app.fastprint.services.APIInterface;
import com.app.fastprint.services.RetrofitCalls;
import com.app.fastprint.ui.socialmedia.interfaces.IMSocialMedia;
import com.app.fastprint.ui.socialmedia.interfaces.IPSocialMedia;
import com.app.fastprint.ui.socialmedia.presenter.PSocialMedia;
import com.app.fastprint.ui.socialmedia.responseModel.SocialMediaResponseModel;

public
class MSocialMedia implements IMSocialMedia {

    IPSocialMedia ipSocialMedia;
    public MSocialMedia(PSocialMedia pSocialMedia) {
        this.ipSocialMedia=pSocialMedia;
    }

    @Override
    public void getSocialMediaRestCalls() {
        RetrofitCalls retrofitCalls=new RetrofitCalls();
        retrofitCalls.socailMediaApi(mHandler);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case APIInterface.SOCAIL_MEDIA_LIST_SUCCESS:
                    SocialMediaResponseModel socialMediaResponseModel = ((SocialMediaResponseModel) msg.obj);
                    ipSocialMedia.successResponseFromModel(socialMediaResponseModel);
                    break;
                case APIInterface.SOCAIL_MEDIA_LIST_FAILED:
                    String invalidRequest = String.valueOf(msg.obj);
                    ipSocialMedia.errorResponseFromModel(invalidRequest);
                    break;

            }
        }
    };
}
