package com.app.fastprint.ui.category.gallery.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.app.fastprint.R;
import com.app.fastprint.ui.category.gallery.GalleryActivity;
import com.app.fastprint.ui.category.gallery.responseModel.GalleryResponseModel;
import com.app.fastprint.ui.galleryImages.GalleryImagesActivity;
import com.app.fastprint.utills.UtilsFontFamily;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    Context context;
    List<GalleryResponseModel.Data.Gallery> gallery;

    public GalleryAdapter(GalleryActivity context, List<GalleryResponseModel.Data.Gallery> gallery) {
        this.context = context;
        this.gallery = gallery;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_gallery, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvCatgegoriesName.setText(gallery.get(position).getName());
        if (gallery.get(position).getImage() != null) {
            Glide.with(context).load(gallery.get(position).getImage())
                    .placeholder(R.drawable.ic_refresh)
                    .into(holder.imgCatgegories);
        } else {
            Glide.with(context).load(gallery.get(position).getImage())
                    .placeholder(R.drawable.ic_refresh)
                    .into(holder.imgCatgegories);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, GalleryImagesActivity.class);
                intent.putExtra("photo_id",gallery.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return gallery.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imgCatgegories)
        RoundedImageView imgCatgegories;

        @BindView(R.id.tvCatgegoriesName)
        TextView tvCatgegoriesName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            tvCatgegoriesName.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        }
    }
}
