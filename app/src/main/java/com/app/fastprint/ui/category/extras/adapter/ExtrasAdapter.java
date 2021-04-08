package com.app.fastprint.ui.category.extras.adapter;
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
import com.app.fastprint.ui.category.extras.responseModel.ExtrasResponseModel;
import com.app.fastprint.ui.sendPayment.SendPaymentActivity;
import com.app.fastprint.ui.uploadFileForm.FormFileUploadActivity;
import com.app.fastprint.utills.UtilsFontFamily;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ExtrasAdapter extends RecyclerView.Adapter<ExtrasAdapter.ViewHolder> {

    Context context;
    List<ExtrasResponseModel.Data.Extra> extras;

    public ExtrasAdapter(Context context, List<ExtrasResponseModel.Data.Extra> extras) {

        this.context = context;
        this.extras = extras;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_extra, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if (extras.get(position).getName() != null) {
            holder.tvPaymentTpe.setText(extras.get(position).getName());
        }

        if (extras.get(position).getImage() != null) {
            Glide.with(context).load(extras.get(position).getImage())
                    .placeholder(R.drawable.ic_refresh)
                    .into(holder.imgPayment);
        } else {
            Glide.with(context).load(extras.get(position).getImage())
                    .placeholder(R.drawable.ic_refresh)
                    .into(holder.imgPayment);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (extras.get(position).getName().equalsIgnoreCase("Upload File")) {
                 /*   Intent intent = new Intent(context, UploadFileActivity.class);
                    intent.putExtra("intent_From","extras");
                    context.startActivity(intent);
                   */
                    Intent intent = new Intent(context, FormFileUploadActivity.class);
                    intent.putExtra("intent_From","extras");
                    context.startActivity(intent);

                } else if (extras.get(position).getName().equalsIgnoreCase("Send Payment")) {
                    Intent intent = new Intent(context, SendPaymentActivity.class);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {

        return extras.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imgPayment)
        ImageView imgPayment;

        @BindView(R.id.tvPaymentTpe)
        TextView tvPaymentTpe;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            tvPaymentTpe.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        }
    }
}
