package com.app.fastprint.ui.socialmedia.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.app.fastprint.R;

import com.app.fastprint.ui.socialmedia.SocialMedialActivity;
import com.app.fastprint.ui.socialmedia.responseModel.SocialMediaResponseModel;
import com.app.fastprint.utills.UtilsFontFamily;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SocialMediaAdapter extends RecyclerView.Adapter<SocialMediaAdapter.ViewHolder> {

    Context context;
    List<SocialMediaResponseModel.Data.Social> social;

    public SocialMediaAdapter(SocialMedialActivity context, List<SocialMediaResponseModel.Data.Social> social) {
        this.context = context;
        this.social = social;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_social_media, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvLoginName.setText(social.get(position).getName());
        if (social.get(position).getImage() != null) {
            Glide.with(context).load(social.get(position).getImage())
                    .placeholder(R.drawable.ic_refresh)
                    .into(holder.imgSocail);
        } else {
            Glide.with(context).load(social.get(position).getImage())
                    .placeholder(R.drawable.ic_refresh)
                    .into(holder.imgSocail);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            Intent intent = null;

            @Override
            public void onClick(View v) {
                Uri uri = null;
                // Uri uri = Uri.parse(url);
                if (social.get(position).getName().equalsIgnoreCase("Facebook"))
                    uri = Uri.parse("http://www.facebook.com/FastPrintAmman");
                else if (social.get(position).getName().equalsIgnoreCase("Instagram"))
                    uri = Uri.parse("http://www.instagram.com.app.fastprint.mman/");
                else if (social.get(position).getName().equalsIgnoreCase("Youtube"))
                    uri = Uri.parse("http://www.youtube.com/user/fastprintamman");
                else if (social.get(position).getName().equalsIgnoreCase("LinkedIn"))
                    uri = Uri.parse("http://www.linkedin.com/company/fast-print-amman?trk=cp_followed_name_fast-print-amman");
                else
                    uri = Uri.parse("https://youtu.be/YZsnV2GhzTk");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return social.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imgSocail)
        ImageView imgSocail;

        @BindView(R.id.tvLoginName)
        TextView tvLoginName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            tvLoginName.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        }
    }
}
