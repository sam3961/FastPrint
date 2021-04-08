package com.app.fastprint.ui.category.gallery.presenters;

import com.app.fastprint.ui.category.gallery.GalleryActivity;
import com.app.fastprint.ui.category.gallery.interfaces.IGallery;
import com.app.fastprint.ui.category.gallery.interfaces.IMGallery;
import com.app.fastprint.ui.category.gallery.interfaces.IPGallery;
import com.app.fastprint.ui.category.gallery.responseModel.GalleryResponseModel;
import com.app.fastprint.ui.category.gallery.viewModel.MGallery;

public
class PGallery implements IPGallery {
    IGallery iGallery;
    IMGallery imGallery;
    public PGallery(GalleryActivity galleryActivity) {
        this.iGallery=galleryActivity;
    }

    @Override
    public void getGallery() {
        imGallery=new MGallery(this);
        imGallery.galleryRestCalls();
    }

    @Override
    public void successResponseFromModel(GalleryResponseModel galleryResponseModel) {
        iGallery.successResponseFromPresenters(galleryResponseModel);
    }

    @Override
    public void errorResponseFromModel(String message) {
        iGallery.errorResponseFromPresenters(message);
    }
}
