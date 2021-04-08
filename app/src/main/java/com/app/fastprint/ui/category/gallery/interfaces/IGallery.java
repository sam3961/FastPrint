package com.app.fastprint.ui.category.gallery.interfaces;

import com.app.fastprint.ui.category.gallery.responseModel.GalleryResponseModel;


public interface IGallery {

    void successResponseFromPresenters(GalleryResponseModel galleryResponseModel);
    void errorResponseFromPresenters(String message);
}
