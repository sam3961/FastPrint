package com.app.fastprint.ui.productDetails.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fastprint.R;
import com.app.fastprint.ui.productDetails.ProductDetailsActivity;
import com.app.fastprint.utills.UtilsFontFamily;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductPageIncludeAdapter extends RecyclerView.Adapter<ProductPageIncludeAdapter.ViewHolder> {

    Context context;
    List<String> productPageIncludeList;
    private int mCheckedPostion = -1;// no selection by default
    ProductDetailsActivity productDetailsActivity;


    public ProductPageIncludeAdapter(Context context, List<String> productPageIncludeList, ProductDetailsActivity productDetailsActivity) {
        this.context = context;
        this.productPageIncludeList = productPageIncludeList;
        this.productDetailsActivity = productDetailsActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_page_include, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.chPageInclude.setChecked(position == mCheckedPostion);
        holder.chPageInclude.setText(productPageIncludeList.get(position));
        holder.chPageInclude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position == mCheckedPostion) {
                    holder.chPageInclude.setChecked(false);
                    mCheckedPostion = -1;
                } else {
                    mCheckedPostion = position;
                    notifyDataSetChanged();
                    ProductDetailsActivity.tvPageincluding.setText(productPageIncludeList.get(position));
                    ((ProductDetailsActivity) productDetailsActivity).
                            getVariation(productDetailsActivity.tvQuantity.getText().toString(),
                                    productDetailsActivity.tvCoveroptions.getText().toString(),
                                    productPageIncludeList.get(position));
                    ProductDetailsActivity.pageIncludeDialog.dismiss();

                }
            }


        });
    }

    @Override
    public int getItemCount() {
        return productPageIncludeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.chPageInclude)
        RadioButton chPageInclude;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            chPageInclude.setTypeface(UtilsFontFamily.typeFaceForRobotoRegular(context));
        }
    }
}
