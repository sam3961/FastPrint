package com.app.fastprint.ui.product.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.app.fastprint.R;
import com.app.fastprint.ui.product.responseModel.StoreProductResponseModel;
import com.app.fastprint.ui.productDetails.ProductDetailsActivity;
import com.app.fastprint.utills.UtilsFontFamily;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    Context context;
    List<StoreProductResponseModel.Data.Product> product;

    public ProductAdapter(Context context, List<StoreProductResponseModel.Data.Product> product) {

        this.context = context;
        this.product = product;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_products, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String product_id= String.valueOf(product.get(position).getProductId());
        holder.tvProductName.setText(product.get(position).getProductName());
        holder.tvtotalRating.setText("(" + product.get(position).getAverage() + ")");
        holder.rating.setRating(product.get(position).getRatingCount());
        if (product.get(position).getProductImage() != null) {
            Glide.with(context).load(product.get(position).getProductImage())
                    .placeholder(R.drawable.ic_refresh)
                    .into(holder.imgProduct);
        } else {
            Glide.with(context).load(product.get(position).getProductImage())
                    .placeholder(R.drawable.ic_refresh)
                    .into(holder.imgProduct);
        }
        holder.tvDiscountPrice.setPaintFlags(holder.tvDiscountPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.tvDiscountPrice.setText( Html.fromHtml(product.get(position).getCurrencySymbol())+product.get(position).getProductRegularPrice()
                );

        holder.tvPrice.setText(Html.fromHtml(product.get(position).getCurrencySymbol())
                        +product.get(position).getProductPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailsActivity.class);
                intent.putExtra("product_id",product_id);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return product.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.tvProductName)
        TextView tvProductName;

        @BindView(R.id.tvDiscountPrice)
        TextView tvDiscountPrice;

        @BindView(R.id.tvPrice)
        TextView tvPrice;

        @BindView(R.id.tvtotalRating)
        TextView tvtotalRating;

        @BindView(R.id.imgProduct)
        ImageView imgProduct;

        @BindView(R.id.rating)
        RatingBar rating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            tvProductName.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
            tvDiscountPrice.setTypeface(UtilsFontFamily.typeFaceForRobotoRegular(context));
            tvPrice.setTypeface(UtilsFontFamily.typeFaceForRobotoRegular(context));
        }
    }
}
