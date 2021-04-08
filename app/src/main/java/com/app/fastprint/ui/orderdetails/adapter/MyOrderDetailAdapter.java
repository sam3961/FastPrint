package com.app.fastprint.ui.orderdetails.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.app.fastprint.R;
import com.app.fastprint.ui.orderdetails.orderDetailResponse.LineItemsItem;

import android.view.View;
import android.view.LayoutInflater;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public
class MyOrderDetailAdapter extends RecyclerView.Adapter<MyOrderDetailAdapter.ViewHolder> {

    Context context;
    List<LineItemsItem> ordersResponses;
    String currencySymbol;


    public MyOrderDetailAdapter(Context context, List<LineItemsItem> ordersResponses, String currencySymbol) {
        this.context = context;
        this.ordersResponses = ordersResponses;
        this.currencySymbol = currencySymbol;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_order_details, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DecimalFormat format = new DecimalFormat("#");
        format.setMinimumFractionDigits(2);
        holder.tvOrderName.setTypeface(holder.tvOrderName.getTypeface(), Typeface.BOLD);
        holder.tvOrderName.setText(ordersResponses.get(position).getName());
        holder.tvOrderPrice.setText(Html.fromHtml(currencySymbol).toString() +
                format.format(ordersResponses.get(position).getPrice()));
        for (int i = 0; i < ordersResponses.get(position).getMetaData().size(); i++) {
            if (ordersResponses.get(position).getMetaData().get(i).getKey().equalsIgnoreCase("quantity")) {
                holder.tvOrderQuantity.setText(ordersResponses.get(position).getMetaData().get(i).getDisplayValue() + "");
            }
        }
        if (holder.tvOrderQuantity.getText().toString().isEmpty())
            holder.tvOrderQuantity.setText(ordersResponses.get(position).getQuantity()+"");
        Glide.with(context).load(ordersResponses.get(position).getProductImage()).into(holder.imgProduct);

    }

    @Override
    public int getItemCount() {
        return ordersResponses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textViewName)
        TextView tvOrderName;
        @BindView(R.id.textViewPrice)
        TextView tvOrderPrice;
        @BindView(R.id.tvOrderQuantity)
        TextView tvOrderQuantity;
        @BindView(R.id.imgProduct)
        ImageView imgProduct;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}