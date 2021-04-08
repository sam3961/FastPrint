package com.app.fastprint.ui.reviewListing.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fastprint.R;
import com.app.fastprint.ui.reviewListing.responseModel.ReviewResponseModel;
import com.app.fastprint.utills.UtilsFontFamily;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    Context context;
    List<ReviewResponseModel.Data.Review> review;
    public ReviewAdapter(Context context, List<ReviewResponseModel.Data.Review> review) {
        this.context = context;
        this.review = review;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_review, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvName.setText(review.get(position).getName());
        holder.tvComment.setText(review.get(position).getName());
        long timestampString =  Long.parseLong(String.valueOf(review.get(position).getDate()));
        String value = new java.text.SimpleDateFormat("dd/MM/yyyy").
                format(new java.util.Date(timestampString * 1000));
        holder.tvDate.setText(value);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }

    @Override
    public int getItemCount() {
        return review.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvName)
        TextView tvName;

        @BindView(R.id.tvComment)
        TextView tvComment;

        @BindView(R.id.tvDate)
        TextView tvDate;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            tvName.setTypeface(UtilsFontFamily.typeFaceForRobotoRegular(context));
            tvComment.setTypeface(UtilsFontFamily.typeFaceForRobotoRegular(context));
            tvDate.setTypeface(UtilsFontFamily.typeFaceForRobotoRegular(context));
        }
    }

}
