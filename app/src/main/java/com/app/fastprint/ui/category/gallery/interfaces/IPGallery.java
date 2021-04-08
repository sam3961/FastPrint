package com.app.fastprint.ui.category.gallery.interfaces;

import com.app.fastprint.ui.category.gallery.responseModel.GalleryResponseModel;


public interface IPGallery {

    void getGallery();
    void successResponseFromModel(GalleryResponseModel galleryResponseModel);
    void errorResponseFromModel(String message);
}
