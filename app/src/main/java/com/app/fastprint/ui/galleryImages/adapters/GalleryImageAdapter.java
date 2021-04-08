package com.app.fastprint.ui.galleryImages.adapters;

import android.Manifest;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.app.fastprint.R;
import com.app.fastprint.ui.galleryImages.GalleryImagesActivity;
import com.app.fastprint.ui.galleryImages.responseModel.GalleryImageResponseModel;
import com.app.fastprint.utills.UtilsAlertDialog;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;



public class GalleryImageAdapter extends RecyclerView.Adapter<GalleryImageAdapter.ViewHolder> {

    List<GalleryImageResponseModel.Data.Image> images;
    String imageURL="";
    public static final int PERMISSION_WRITE = 0;
    GalleryImagesActivity activity;
    Dialog dialog;
    public GalleryImageAdapter(GalleryImagesActivity activity, List<GalleryImageResponseModel.Data.Image> images) {
        this.activity = activity;
        this.images = images;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_photos, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        imageURL=images.get(position).getFull();
        if (images.get(position).getFull() != null) {
            Glide.with(activity).load(images.get(position).getFull())
                    .placeholder(R.drawable.ic_refresh)
                    .into(holder.imgphotos);
        } else {
            Glide.with(activity).load(images.get(position).getFull())
                    .placeholder(R.drawable.ic_refresh)
                    .into(holder.imgphotos);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageDialog(images.get(position).getFull());
            }
        });
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imgphotos)
        ImageView imgphotos;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void imageDialog(String imageURL) {
        Dialog dialog = new Dialog(activity, R.style.FullScreenDialog);
        dialog.setContentView(R.layout.layout_imagedownload);
        dialog.setCancelable(false);
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setAttributes(layoutParams);
        checkPermission();
        ImageView imgDismiss=(ImageView)dialog.findViewById(R.id.imgDismiss);
        ImageView imgSave=(ImageView)dialog.findViewById(R.id.imgSave);
        ImageView imgGallery=(ImageView)dialog.findViewById(R.id.imgGallery);

        if (imageURL != null) {
            Glide.with(activity).load(imageURL)
                    .placeholder(R.drawable.ic_refresh)
                    .into(imgGallery);
        } else {
            Glide.with(activity).load(imageURL)
                    .placeholder(R.drawable.ic_refresh)
                    .into(imgGallery);
        }

        imgDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        imgSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkPermission()) {
                    new Downloading().execute(imageURL);
                }
            }
        });

        dialog.show();
    }

    public class Downloading extends AsyncTask<String, Integer, String> {

        @Override
        public void onPreExecute() {
            super .onPreExecute();
            dialog = UtilsAlertDialog.ShowDialog(activity);
        }

        @Override
        protected String doInBackground(String... url) {
            File mydir = new File(Environment.getExternalStorageDirectory() + "/FastPrint");
            if (!mydir.exists()) {
                mydir.mkdirs();
            }

            DownloadManager manager = (DownloadManager) activity.getSystemService(Context.DOWNLOAD_SERVICE);
            Uri downloadUri = Uri.parse(url[0]);
            DownloadManager.Request request = new DownloadManager.Request(downloadUri);

            SimpleDateFormat dateFormat = new SimpleDateFormat("mmddyyyyhhmmss");
            String date = dateFormat.format(new Date());

            request.setAllowedNetworkTypes(
                    DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
                    .setAllowedOverRoaming(false)
                    .setTitle("Downloading")
                    .setDestinationInExternalPublicDir("/FastPrint", date + ".jpg");

            manager.enqueue(request);
            return mydir.getAbsolutePath() + File.separator + date + ".jpg";
        }

        @Override
        public void onPostExecute(String s) {
            super .onPostExecute(s);
            dialog.dismiss();
            Toast.makeText(activity, "Image Saved", Toast.LENGTH_SHORT).show();
        }
    }

    //runtime storage permission
    public boolean checkPermission() {
        int READ_EXTERNAL_PERMISSION = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);
        if((READ_EXTERNAL_PERMISSION != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_WRITE);
            return false;
        }
        return true;
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode==PERMISSION_WRITE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //do somethings
        }
    }
}
