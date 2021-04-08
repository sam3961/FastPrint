package com.app.fastprint.ui.galleryImages.presenters;

import com.app.fastprint.ui.galleryImages.GalleryImagesActivity;
import com.app.fastprint.ui.galleryImages.interfaces.IGalleryImage;
import com.app.fastprint.ui.galleryImages.interfaces.IMGalleryImage;
import com.app.fastprint.ui.galleryImages.interfaces.IPGalleryImage;
import com.app.fastprint.ui.galleryImages.responseModel.GalleryImageResponseModel;
import com.app.fastprint.ui.galleryImages.viewModel.MGalleryImage;

public
class PGalleryImage implements IPGalleryImage {
    IGalleryImage iGalleryImage;
    IMGalleryImage imGalleryImage;
    public PGalleryImage(GalleryImagesActivity galleryImagesActivity) {
        this.iGalleryImage=galleryImagesActivity;
    }

    @Override
    public void getGallery(String photo_id) {
        imGalleryImage=new MGalleryImage(this);
        imGalleryImage.galleryRestCalls(photo_id);
    }

    @Override
    public void successResponseFromModel(GalleryImageResponseModel galleryImageResponseModel) {
        iGalleryImage.successResponseFromPresenters(galleryImageResponseModel);
    }

    @Override
    public void errorResponseFromModel(String message) {
        iGalleryImage.errorResponseFromPresenters(message);
    }
}
