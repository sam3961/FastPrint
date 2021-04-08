package com.app.fastprint.ui.product.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fastprint.R;
import com.app.fastprint.ui.product.ProductListingActivity;
import com.app.fastprint.ui.product.responseModel.StoreListingResponseModel;
import com.app.fastprint.utills.UtilsFontFamily;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.ViewHolder> {

    Context context;
    int selectedPosition = 0;
    List<StoreListingResponseModel.Data.StoreCategory> storeCategory;
    List<StoreListingResponseModel.Data.StoreCategory.SubCategory> storeSubCategoryCategory;
    ProductListingActivity productListingActivity;

    public StoreAdapter(Context context, List<StoreListingResponseModel.Data.StoreCategory> storeCategory, ProductListingActivity productListingActivity) {
        this.context = context;
        this.storeCategory = storeCategory;
        this.productListingActivity = productListingActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_store_categories, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if (selectedPosition == position) {
            holder.tvStoreCategories.setTextColor(Color.parseColor("#FFFFFF"));
            holder.layoutItems.setBackgroundResource(R.drawable.bg_skyblue);
        } else {
            holder.tvStoreCategories.setTextColor(Color.parseColor("#000000"));
            holder.layoutItems.setBackgroundResource(R.drawable.selection_bg);
        }
        holder.tvStoreCategories.setText(storeCategory.get(position).getName());

    /*    storeSubCategoryCategory=new ArrayList<>();
        if (storeCategory.get(position).getSubCategory().size()>0){
            holder.recyclerStoreSubCategory.setVisibility(View.VISIBLE);
            for (int i=0;i<storeCategory.get(position).getSubCategory().size();i++)
            {
                StoreListingResponseModel.Data.StoreCategory.SubCategory storeSubCategory = storeCategory.get(position).getSubCategory().get(i);
                storeSubCategoryCategory.add(storeSubCategory);
                StoreSubCategoryAdapter storeSubCategoryAdapter = new StoreSubCategoryAdapter(context, storeSubCategoryCategory);
                holder.recyclerStoreSubCategory.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                holder.recyclerStoreSubCategory.setAdapter(storeSubCategoryAdapter);
                storeSubCategoryAdapter.notifyDataSetChanged();
            }

        }else {
            holder.recyclerStoreSubCategory.setVisibility(View.GONE);
        }*/

        if (storeCategory.get(position).getSubCategory().size() > 0) {
            holder.imgShow.setVisibility(View.GONE);
            holder.imgShow.setVisibility(View.VISIBLE);

            holder.imgShow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.recyclerStoreSubCategory.setVisibility(View.VISIBLE);
                    holder.imgGone.setVisibility(View.VISIBLE);
                    holder.imgShow.setVisibility(View.GONE);
                }
            });
            holder.imgGone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.recyclerStoreSubCategory.setVisibility(View.GONE);
                    holder.imgGone.setVisibility(View.GONE);
                    holder.imgShow.setVisibility(View.VISIBLE);
                }
            });

        } else {
            holder.imgShow.setVisibility(View.GONE);
            holder.imgGone.setVisibility(View.GONE);
            holder.recyclerStoreSubCategory.setVisibility(View.GONE);

        }

        StoreSubCategoryAdapter storeSubCategoryAdapter = new StoreSubCategoryAdapter(context, storeCategory.get(position).getSubCategory(),productListingActivity);
        holder.recyclerStoreSubCategory.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        holder.recyclerStoreSubCategory.setAdapter(storeSubCategoryAdapter);
        storeSubCategoryAdapter.notifyDataSetChanged();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPosition == position) {
                    selectedPosition = 0;
                    notifyDataSetChanged();
                    return;
                }
                selectedPosition = position;
                notifyDataSetChanged();
                productListingActivity.OnStoreItemClick(String.valueOf(storeCategory.get(position).getId()));

            }
        });
    }

    @Override
    public int getItemCount() {
        return storeCategory.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvStoreCategories)
        TextView tvStoreCategories;

        @BindView(R.id.imgShow)
        ImageView imgShow;

        @BindView(R.id.imgGone)
        ImageView imgGone;

        @BindView(R.id.recyclerStoreSubCategory)
        RecyclerView recyclerStoreSubCategory;

        @BindView(R.id.layoutItems)
        RelativeLayout layoutItems;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            tvStoreCategories.setTypeface(UtilsFontFamily.typeFaceForRobotoRegular(context));
        }
    }
}
