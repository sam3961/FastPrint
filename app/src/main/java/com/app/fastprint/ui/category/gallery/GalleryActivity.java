package com.app.fastprint.ui.category.gallery;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fastprint.R;
import com.app.fastprint.baseClass.BaseClass;

import com.app.fastprint.networks.NetworkUtils;
import com.app.fastprint.ui.category.gallery.adapters.GalleryAdapter;
import com.app.fastprint.ui.category.gallery.interfaces.IGallery;
import com.app.fastprint.ui.category.gallery.interfaces.IPGallery;
import com.app.fastprint.ui.category.gallery.presenters.PGallery;
import com.app.fastprint.ui.category.gallery.responseModel.GalleryResponseModel;
import com.app.fastprint.utills.UtilsAlertDialog;
import com.app.fastprint.utills.UtilsFontFamily;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GalleryActivity extends BaseClass implements IGallery {

    @BindView(R.id.imgBack)
    ImageView imgBack;

    @BindView(R.id.tvTittle)
    TextView tvTittle;

    @BindView(R.id.RecyclerGallery)
    RecyclerView RecyclerGallery;

    GalleryAdapter galleryAdapter;
    Context context;
    Dialog dialog;
    IPGallery ipGallery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        ButterKnife.bind(this);
        context = GalleryActivity.this;
        ipGallery=new PGallery(this);
        if (NetworkUtils.isNetworkConnectionAvailable(context)) {
            dialog = UtilsAlertDialog.ShowDialog(context);
            ipGallery.getGallery();
        }
        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));

    }

    @OnClick(R.id.imgBack)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void successResponseFromPresenters(GalleryResponseModel galleryResponseModel) {
        dialog.dismiss();
        if (galleryResponseModel!=null){
            updateListUI(galleryResponseModel);
        }
    }

    private void updateListUI(GalleryResponseModel galleryResponseModel) {
        int columns = 2;
        galleryAdapter = new GalleryAdapter(this,galleryResponseModel.getData().getGallery());
        RecyclerGallery.setLayoutManager(new GridLayoutManager(this, columns));
        RecyclerGallery.setAdapter(galleryAdapter);
    }

    @Override
    public void errorResponseFromPresenters(String message) {
        dialog.dismiss();
        String error_message = message;
        Toast.makeText(context, "" + error_message, Toast.LENGTH_SHORT).show();
    }
}
