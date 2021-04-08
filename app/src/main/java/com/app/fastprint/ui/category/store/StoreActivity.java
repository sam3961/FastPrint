package com.app.fastprint.ui.category.store;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fastprint.R;
import com.app.fastprint.baseClass.BaseClass;
import com.app.fastprint.networks.NetworkUtils;
import com.app.fastprint.ui.category.store.adapter.StoreAdapter;
import com.app.fastprint.ui.product.interfaces.IPProductListing;
import com.app.fastprint.ui.main.responseModel.CategoriesModel;
import com.app.fastprint.utills.UtilsAlertDialog;
import com.app.fastprint.utills.UtilsFontFamily;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StoreActivity extends BaseClass {

    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.tvTittle)
    TextView tvTittle;
    @BindView(R.id.RecyclerStore)
    RecyclerView RecyclerStore;
    Dialog dialog;
    Context context;
    StoreAdapter storeAdapter;
    IPProductListing ipStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        ButterKnife.bind(this);
        context= StoreActivity.this;
        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        if (NetworkUtils.isNetworkConnectionAvailable(context)) {
            dialog = UtilsAlertDialog.ShowDialog(context);
            ipStore.getStore();
        }
        CategoriesModel[] categoriesModel = new CategoriesModel[]{
                new CategoriesModel("Shop", R.drawable.ic_view),
                new CategoriesModel("Upload Order File", R.drawable.ic_view),

        };
        storeAdapter = new StoreAdapter(context, categoriesModel);
        RecyclerStore.setLayoutManager(new LinearLayoutManager(this));
        RecyclerStore.setAdapter(storeAdapter);

    }

    @OnClick(R.id.imgBack)
    public void onViewClicked() {
        finish();
    }


}