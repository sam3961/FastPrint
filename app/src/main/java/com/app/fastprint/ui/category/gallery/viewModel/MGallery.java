package com.app.fastprint.ui.category.gallery.viewModel;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.app.fastprint.services.APIInterface;
import com.app.fastprint.services.RetrofitCalls;
import com.app.fastprint.ui.category.gallery.interfaces.IMGallery;
import com.app.fastprint.ui.category.gallery.interfaces.IPGallery;
import com.app.fastprint.ui.category.gallery.presenters.PGallery;
import com.app.fastprint.ui.category.gallery.responseModel.GalleryResponseModel;

public
class MGallery implements IMGallery {
    IPGallery ipGallery;
    public MGallery(PGallery pGallery) {
        this.ipGallery=pGallery;

    }

    @Override
    public void galleryRestCalls() {
        RetrofitCalls retrofitCalls=new RetrofitCalls();
        retrofitCalls.galleryApi(mHandler);
    }
    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case APIInterface.GALLERY_SUCCESS:
                    GalleryResponseModel galleryResponseModel = ((GalleryResponseModel) msg.obj);
                    ipGallery.successResponseFromModel(galleryResponseModel);
                    break;
                case APIInterface.GALLERY_FAILED:
                    String invalidRequest = String.valueOf(msg.obj);
                    ipGallery.errorResponseFromModel(invalidRequest);
                    break;

            }
        }
    };
}
