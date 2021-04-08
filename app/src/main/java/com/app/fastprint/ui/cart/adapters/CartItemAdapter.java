package com.app.fastprint.ui.cart.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
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
import com.app.fastprint.ui.cart.responseModel.CartListing;
import com.app.fastprint.ui.productDetails.ProductDetailsActivity;
import com.app.fastprint.utills.UtilsFontFamily;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.ViewHolder> {

    Context context;
    OnDeleteCartListener onDeleteCartListener;
    ArrayList<CartListing> cartListing;


    public CartItemAdapter(Context context, ArrayList<CartListing> cartListings, OnDeleteCartListener onDeleteCartListener) {
        this.context = context;
        this.cartListing = cartListings;
        this.onDeleteCartListener = onDeleteCartListener;
    }

    public void customNotify(ArrayList<CartListing> cartListings) {
        this.cartListing = cartListings;
        notifyDataSetChanged();
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
        holder.tvDiscountPrice.setText(cartListing.get(position).getCurrencySymbol() + cartListing.get(position).getDiscount_amount());
        holder.tvPrice.setText(cartListing.get(position).getCurrencySymbol() + cartListing.get(position).getAmount());
        holder.tvProductName.setText(cartListing.get(position).getProduct_name());
        holder.tvQuantity.setText(cartListing.get(position).getQuantity());
        holder.tvtotalRating.setText(cartListing.get(position).getTotal_rating());
        Glide.with(context).load(cartListing.get(position).getProduct_image()).into(holder.imgProduct);

        holder.rating.setRating(cartListing.get(position).getRating());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailsActivity.class);
                intent.putExtra("product_id", cartListing.get(position).getProduct_id());
                context.startActivity(intent);
            }
        });
        holder.imgDelete.setOnClickListener(v -> onDeleteCartListener.onDeleteProduct(position));
    }

    @Override
    public int getItemCount() {
        return cartListing.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.tvProductName)
        TextView tvProductName;
        @BindView(R.id.imgDelete)
        ImageView imgDelete;

        @BindView(R.id.tvDiscountPrice)
        TextView tvDiscountPrice;

        @BindView(R.id.tvPrice)
        TextView tvPrice;
        @BindView(R.id.tvQuantity)
        TextView tvQuantity;
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

    public interface OnDeleteCartListener {
        void onDeleteProduct(int position);
    }
}
