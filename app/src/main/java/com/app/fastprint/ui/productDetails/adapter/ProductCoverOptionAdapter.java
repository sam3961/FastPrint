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

public class ProductCoverOptionAdapter extends RecyclerView.Adapter<ProductCoverOptionAdapter.ViewHolder> {

    Context context;
    List<String> coverOptionList;
    private int mCheckedPostion = -1;// no selection by default
    ProductDetailsActivity productDetailsActivity;


    public ProductCoverOptionAdapter(Context context, List<String> coverOptionList, ProductDetailsActivity productDetailsActivity) {
        this.context = context;
        this.coverOptionList = coverOptionList;
        this.productDetailsActivity = productDetailsActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_cover_options, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.chCoverPage.setChecked(position == mCheckedPostion);
        holder.chCoverPage.setText(coverOptionList.get(position));
        holder.chCoverPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position == mCheckedPostion) {
                    holder.chCoverPage.setChecked(false);
                    mCheckedPostion = -1;
                } else {
                    mCheckedPostion = position;
                    notifyDataSetChanged();
                    ProductDetailsActivity.tvCoveroptions.setText(coverOptionList.get(position));
                    ((ProductDetailsActivity)productDetailsActivity).
                            getVariation(productDetailsActivity.tvQuantity.getText().toString(),
                                    coverOptionList.get(position),
                                    productDetailsActivity.tvPageincluding.getText().toString());
                    ProductDetailsActivity.coverOptionDialog.dismiss();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return coverOptionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.chCoverPage)
        RadioButton chCoverPage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            chCoverPage.setTypeface(UtilsFontFamily.typeFaceForRobotoRegular(context));
        }
    }
}
