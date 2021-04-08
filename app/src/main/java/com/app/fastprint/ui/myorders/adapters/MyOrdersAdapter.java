package com.app.fastprint.ui.myorders.adapters;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fastprint.R;
import com.app.fastprint.ui.myorders.ordersResonse.DataItem;
import com.app.fastprint.ui.orderdetails.OrderDetailsActivity;

import android.view.View;
import android.view.LayoutInflater;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public
class MyOrdersAdapter extends RecyclerView.Adapter<MyOrdersAdapter.ViewHolder> {

    Context context;
    List<DataItem> ordersResponses;
    private String consumer_key,consumer_secret;


    public MyOrdersAdapter(Context context, List<DataItem> ordersResponses, String consumer_key, String consumer_secret) {
        this.context = context;
        this.ordersResponses = ordersResponses;
        this.consumer_secret = consumer_secret;
        this.consumer_key = consumer_key;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_orders, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if (ordersResponses.get(position).getLineItems().size()==2) {
            holder.tvOrderName.setText(ordersResponses.get(position).getLineItems().get(0).getName()
                    + " & "+(ordersResponses.get(position).getLineItems().size()-1) +" other");

        }else if (ordersResponses.get(position).getLineItems().size()>2){
            holder.tvOrderName.setText(ordersResponses.get(position).getLineItems().get(0).getName()
                    + " & "+(ordersResponses.get(position).getLineItems().size()-1)+" others");
        }
        else{
            holder.tvOrderName.setText(ordersResponses.get(position).getLineItems().get(0).getName());
        }

        holder.tvOrderPrice.setText(Html.fromHtml(ordersResponses.get(position).getCurrencySymbol()).toString()+
                ordersResponses.get(position).getTotal());
        holder.tvOrderStatus.setText(ordersResponses.get(position).getStatus());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, OrderDetailsActivity.class);
                intent.putExtra("orderid",ordersResponses.get(position).getId());
                intent.putExtra("consumer_key",consumer_key);
                intent.putExtra("consumer_secret",consumer_secret);
                context.startActivity(intent);

            }
        });
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
        @BindView(R.id.textViewStatus)
        TextView tvOrderStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}