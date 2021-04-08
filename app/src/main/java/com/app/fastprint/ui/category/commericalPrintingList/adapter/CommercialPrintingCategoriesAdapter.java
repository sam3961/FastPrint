package com.app.fastprint.ui.category.commericalPrintingList.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.app.fastprint.R;
import com.app.fastprint.ui.category.commericalPrinting.CommercialPrintingActivity;
import com.app.fastprint.ui.category.commericalPrintingList.responseModel.CommercialPrintingCategoriesResponseModel;
import com.app.fastprint.utills.UtilsFontFamily;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommercialPrintingCategoriesAdapter extends RecyclerView.Adapter<CommercialPrintingCategoriesAdapter.ViewHolder> {

    Context context;
    List<CommercialPrintingCategoriesResponseModel.Data.CommericalCategory> commericalCategory;
    String printing_name;

    public CommercialPrintingCategoriesAdapter(Context context, List<CommercialPrintingCategoriesResponseModel.Data.CommericalCategory> commericalCategory, String printing_name) {
        this.context = context;
        this.printing_name = printing_name;
        this.commericalCategory = commericalCategory;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_commerical_printing_categories, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if (commericalCategory.get(position).getTitle() != null) {
            holder.tvCategoriesType.setText(commericalCategory.get(position).getTitle().toLowerCase().substring(0,1).toUpperCase()+commericalCategory.get(position).getTitle().substring(1).toLowerCase());
        }

        if (commericalCategory.get(position).getImage() != null) {
            Glide.with(context).load(commericalCategory.get(position).getImage())
                    .placeholder(R.drawable.ic_refresh)
                    .into(holder.imgCatgegories);
        } else {
            Glide.with(context).load(commericalCategory.get(position).getImage())
                    .placeholder(R.drawable.ic_refresh)
                    .into(holder.imgCatgegories);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, CommercialPrintingActivity.class);
                intent.putExtra("commercial_category_id",commericalCategory.get(position).getFormId());
                intent.putExtra("commercial_category_name",commericalCategory.get(position).getTitle());
                intent.putExtra("printing_name",printing_name);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return commericalCategory.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imgCatgegories)
        ImageView imgCatgegories;

        @BindView(R.id.tvCategoriesType)
        TextView tvCategoriesType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            tvCategoriesType.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        }
    }
}
