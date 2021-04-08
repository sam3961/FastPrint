package com.app.fastprint.ui.galleryImages.interfaces;

import com.app.fastprint.ui.galleryImages.responseModel.GalleryImageResponseModel;


public interface IPGalleryImage {

    void getGallery(String photo_id);
    void successResponseFromModel(GalleryImageResponseModel galleryImageResponseModel);
    void errorResponseFromModel(String message);
}
