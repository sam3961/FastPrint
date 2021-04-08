package com.app.fastprint.ui.product.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fastprint.R;
import com.app.fastprint.ui.product.ProductListingActivity;
import com.app.fastprint.ui.product.responseModel.StoreListingResponseModel;
import com.app.fastprint.utills.UtilsFontFamily;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StoreSubCategoryAdapter extends RecyclerView.Adapter<StoreSubCategoryAdapter.ViewHolder> {

    private int currentPosition =-1;
    Context context;
    List<StoreListingResponseModel.Data.StoreCategory.SubCategory> storeSubCategoryCategory;
    ProductListingActivity productListingActivity;
    public StoreSubCategoryAdapter(Context context, List<StoreListingResponseModel.Data.StoreCategory.SubCategory> storeSubCategoryCategory,ProductListingActivity productListingActivity) {
        this.context = context;
        this.storeSubCategoryCategory = storeSubCategoryCategory;
        this.productListingActivity = productListingActivity;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_store_sub_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvStoreSubCategories.setText(storeSubCategoryCategory.get(position).getName());
        holder.tvStoreSubCategories.setTextColor(Color.BLACK);
        holder.layoutItems.setBackgroundResource(R.drawable.bg_grey_selection);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if(currentPosition>=0) {
                    notifyItemChanged(currentPosition);
                }
                holder.tvStoreSubCategories.setTextColor(Color.WHITE);
                holder.layoutItems.setBackgroundResource(R.drawable.bg_skyblue);
                currentPosition=position;
                ((ProductListingActivity) productListingActivity).OnStoreSubItemClick(String.valueOf(storeSubCategoryCategory.get(position).getId()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return storeSubCategoryCategory.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvStoreSubCategories)
        TextView tvStoreSubCategories;

        @BindView(R.id.layoutItems)
        RelativeLayout layoutItems;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            tvStoreSubCategories.setTypeface(UtilsFontFamily.typeFaceForRobotoRegular(context));
        }

    }
}
