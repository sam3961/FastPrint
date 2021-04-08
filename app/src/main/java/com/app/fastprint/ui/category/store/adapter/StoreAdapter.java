package com.app.fastprint.ui.category.store.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fastprint.R;

import com.app.fastprint.ui.main.responseModel.CategoriesModel;
import com.app.fastprint.utills.UtilsFontFamily;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.ViewHolder> {

    Context context;
    CategoriesModel[] categoriesModel;

    public StoreAdapter(Context context, CategoriesModel[] categoriesModel) {
        this.context = context;
        this.categoriesModel = categoriesModel;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_store, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvStoreType.setText(categoriesModel[position].getName());
        holder.imgStore.setImageResource(categoriesModel[position].getImages());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoriesModel.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imgStore)
        ImageView imgStore;

        @BindView(R.id.tvStoreType)
        TextView tvStoreType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            tvStoreType.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));

        }
    }
}
