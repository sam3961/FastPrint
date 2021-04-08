package com.app.fastprint.ui.galleryImages.viewModel;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.app.fastprint.services.APIInterface;
import com.app.fastprint.services.RetrofitCalls;
import com.app.fastprint.ui.galleryImages.interfaces.IMGalleryImage;
import com.app.fastprint.ui.galleryImages.interfaces.IPGalleryImage;
import com.app.fastprint.ui.galleryImages.presenters.PGalleryImage;
import com.app.fastprint.ui.galleryImages.responseModel.GalleryImageResponseModel;

public
class MGalleryImage implements IMGalleryImage {
    IPGalleryImage ipGalleryImage;
    public MGalleryImage(PGalleryImage pGalleryImage) {
        this.ipGalleryImage=pGalleryImage;
    }

    @Override
    public void galleryRestCalls(String photo_id) {
        RetrofitCalls retrofitCalls=new RetrofitCalls();
        retrofitCalls.galleryImageApi(photo_id,mHandler);
    }

    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case APIInterface.GALLERY_IMAGE_SUCCESS:
                    GalleryImageResponseModel galleryImageResponseModel = ((GalleryImageResponseModel) msg.obj);
                    ipGalleryImage.successResponseFromModel(galleryImageResponseModel);
                    break;
                case APIInterface.GALLERY_IMAGE_FAILED:
                    String invalidRequest = String.valueOf(msg.obj);
                    ipGalleryImage.errorResponseFromModel(invalidRequest);
                    break;

            }
        }
    };
}
