package com.app.fastprint.ui.productDetails.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fastprint.R;
import com.app.fastprint.ui.productDetails.ProductDetailsActivity;
import com.app.fastprint.utills.UtilsFontFamily;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductQuantityAdapter extends RecyclerView.Adapter<ProductQuantityAdapter.ViewHolder> {
    Context context;
    List<String> productQuantityList;
    private int mCheckedPostion = -1;// no selection by default
    ProductDetailsActivity productDetailsActivity;
    TextView tvQuantity;
    public ProductQuantityAdapter(Context context, TextView tvQuantity, List<String> productQuantityList, ProductDetailsActivity productDetailsActivity) {
        this.context = context;
        this.productQuantityList = productQuantityList;
        this.productDetailsActivity = productDetailsActivity;
        this.tvQuantity = tvQuantity;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_quantity, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.chQuantity.setChecked(position == mCheckedPostion);
        holder.chQuantity.setText(productQuantityList.get(position));
        holder.chQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == mCheckedPostion) {
                    holder.chQuantity.setChecked(false);
                    mCheckedPostion = -1;
                } else {
                    mCheckedPostion = position;
                    notifyDataSetChanged();
                    ProductDetailsActivity.tvQuantity.setText(productQuantityList.get(position));
                    ((ProductDetailsActivity)productDetailsActivity).getVariation(productQuantityList.get(position),
                            productDetailsActivity.tvCoveroptions.getText().toString(),productDetailsActivity.tvPageincluding.getText().toString());
                    ProductDetailsActivity.productQuantityDialog.dismiss();
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return productQuantityList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.chQuantity)
        RadioButton chQuantity;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            chQuantity.setTypeface(UtilsFontFamily.typeFaceForRobotoRegular(context));
        }
    }
}
