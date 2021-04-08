package com.app.fastprint.ui.fragments.main.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fastprint.R;
import com.app.fastprint.ui.productDetails.ProductDetailsActivity;
import com.app.fastprint.utills.UtilsFontFamily;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductCategoriesAdapter2 extends RecyclerView.Adapter<ProductCategoriesAdapter2.ViewHolder> {

    Context context;


    public ProductCategoriesAdapter2(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_product_categories, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvDiscountPrice.setPaintFlags(holder.tvDiscountPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.tvDiscountPrice.setText("$" + "120.00");

        holder.tvPrice.setText("$ " + "110.00");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailsActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.tvProductName)
        TextView tvProductName;

        @BindView(R.id.tvDiscountPrice)
        TextView tvDiscountPrice;

        @BindView(R.id.tvPrice)
        TextView tvPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            tvProductName.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
            tvDiscountPrice.setTypeface(UtilsFontFamily.typeFaceForRobotoRegular(context));
            tvPrice.setTypeface(UtilsFontFamily.typeFaceForRobotoRegular(context));
        }
    }
}
