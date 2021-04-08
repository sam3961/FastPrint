package com.app.fastprint.ui.uploadfile.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.app.fastprint.R;
import com.app.fastprint.ui.uploadfile.responnseModel.UploadFileResponseModel;
import com.app.fastprint.utills.UtilsFontFamily;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UploadAdapter extends RecyclerView.Adapter<UploadAdapter.ViewHolder> {

    Context context;
    List<UploadFileResponseModel.Data.UploadFile> uploadFile;

    public UploadAdapter(Context context, List<UploadFileResponseModel.Data.UploadFile> uploadFile) {
        this.context = context;
        this.uploadFile = uploadFile;
        
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_upload, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mediaName.setText(uploadFile.get(position).getName());
        if (uploadFile.get(position).getImage() != null) {
            Glide.with(context).load(uploadFile.get(position).getImage())
                    .placeholder(R.drawable.ic_refresh)
                    .into(holder.image);
        } else {
            Glide.with(context).load(uploadFile.get(position).getImage())
                    .placeholder(R.drawable.ic_refresh)
                    .into(holder.image);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return uploadFile.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image)
        ImageView image;

        @BindView(R.id.mediaName)
        TextView mediaName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mediaName.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        }
    }
}
