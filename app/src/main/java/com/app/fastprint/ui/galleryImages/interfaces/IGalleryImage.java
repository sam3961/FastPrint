package com.app.fastprint.ui.galleryImages.interfaces;

import com.app.fastprint.ui.galleryImages.responseModel.GalleryImageResponseModel;


public interface IGalleryImage {

    void successResponseFromPresenters(GalleryImageResponseModel galleryResponseModel);
    void errorResponseFromPresenters(String message);
}
