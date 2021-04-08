package com.app.fastprint.ui.payment.adapter;
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
import com.app.fastprint.ui.payment.paymentResponseModel.PaymentResponseModel;
import com.app.fastprint.utills.UtilsFontFamily;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PaymentOptionAdapter extends RecyclerView.Adapter<PaymentOptionAdapter.ViewHolder> {

    Context context;
    List<PaymentResponseModel.Data.SendPayment> sendPayment;


    public PaymentOptionAdapter(Context context, List<PaymentResponseModel.Data.SendPayment> sendPayment) {
        this.context = context;
        this.sendPayment = sendPayment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_paymnet_options, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvPaymentTpe.setText(sendPayment.get(position).getName());
        if (sendPayment.get(position).getImage() != null) {
            Glide.with(context).load(sendPayment.get(position).getImage())
                    .placeholder(R.drawable.ic_refresh)
                    .into(holder.imgPayment);
        } else {
            Glide.with(context).load(sendPayment.get(position).getImage())
                    .placeholder(R.drawable.ic_refresh)
                    .into(holder.imgPayment);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            Intent intent = null;

            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return sendPayment.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imgPayment)
        ImageView imgPayment;
        @BindView(R.id.tvPaymentTpe)
        TextView tvPaymentTpe;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            tvPaymentTpe.setTypeface(UtilsFontFamily.typeFaceForRobotoRegular(context));
        }
    }
}
