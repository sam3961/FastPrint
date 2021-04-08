package com.app.fastprint.ui.product;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fastprint.R;
import com.app.fastprint.baseClass.BaseClass;
import com.app.fastprint.networks.NetworkUtils;
import com.app.fastprint.ui.cart.CartActivity;
import com.app.fastprint.ui.product.adapters.ProductAdapter;
import com.app.fastprint.ui.product.adapters.StoreAdapter;
import com.app.fastprint.ui.product.interfaces.IPProductListing;
import com.app.fastprint.ui.product.interfaces.IProductListing;
import com.app.fastprint.ui.product.presenters.PProductListing;
import com.app.fastprint.ui.product.responseModel.StoreListingResponseModel;
import com.app.fastprint.ui.product.responseModel.StoreProductResponseModel;
import com.app.fastprint.utills.AppConstants;
import com.app.fastprint.utills.AppControler;
import com.app.fastprint.utills.UtilsAlertDialog;
import com.app.fastprint.utills.UtilsFontFamily;
import com.varunest.sparkbutton.SparkButton;
import com.varunest.sparkbutton.SparkEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductListingActivity extends BaseClass implements IProductListing {

    @BindView(R.id.imgBack)
    ImageView imgBack;

    @BindView(R.id.imgNoRecordFound)
    ImageView imgNoRecordFound;

    @BindView(R.id.imgCart)
    SparkButton imgCart;

    @BindView(R.id.textViewCountCart)
    TextView textViewCountCart;

    @BindView(R.id.tvTittle)
    TextView tvTittle;

    @BindView(R.id.recyclerProduct)
    RecyclerView recyclerProduct;

    @BindView(R.id.recyclerStore)
    RecyclerView recyclerStore;

    ProductAdapter productListAdapter;
    StoreAdapter storeAdapter;
    Context context;
    Dialog dialog;
    IPProductListing ipProductListing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_listing);
        ButterKnife.bind(this);
        context = ProductListingActivity.this;
        ipProductListing = new PProductListing(this);
        if (NetworkUtils.isNetworkConnectionAvailable(context)) {
            dialog = UtilsAlertDialog.ShowDialog(context);
            ipProductListing.getStore();
        }
        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));

        imgCart.setEventListener(new SparkEventListener() {
            @Override
            public void onEvent(ImageView button, boolean buttonState) {
                new Handler().postDelayed(() -> {
                    if (NetworkUtils.isNetworkConnectionAvailable(context)) {
                        Intent intent = new Intent(ProductListingActivity.this, CartActivity.class);
                        startActivity(intent);
                        imgCart.setChecked(false);
                    }
                }, 300);


            }

            @Override
            public void onEventAnimationEnd(ImageView button, boolean buttonState) {

            }

            @Override
            public void onEventAnimationStart(ImageView button, boolean buttonState) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        textViewCountCart.setText(AppControler.getInstance(context).getCartList(AppConstants.KEY_CART).size() + "");
    }

    @OnClick({R.id.imgBack})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
        }
    }

    @Override
    public void successResponseFromPresenter(StoreListingResponseModel storeResponseModel) {
        dialog.dismiss();
        if (storeResponseModel != null) {
            updateListUI(storeResponseModel);
        }
    }

    private void updateListUI(StoreListingResponseModel storeResponseModel) {

        storeAdapter = new StoreAdapter(context, storeResponseModel.getData().getStoreCategory(), ProductListingActivity.this);
        recyclerStore.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        recyclerStore.setAdapter(storeAdapter);

        if (NetworkUtils.isNetworkConnectionAvailable(context)) {
            dialog = UtilsAlertDialog.ShowDialog(context);
            ipProductListing.getStoreProduct(String.valueOf(storeResponseModel.getData().getStoreCategory().get(0).getId()));
        }
    }

    @Override
    public void errorResponseFromPresenter(String message) {
        dialog.dismiss();
        String error_message = message;
        Toast.makeText(context, "" + error_message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void successResponseFromPresenter(StoreProductResponseModel storeProductResponseModel) {
        dialog.dismiss();
        if (storeProductResponseModel != null && storeProductResponseModel.getData().getProduct().size() > 0) {
            imgNoRecordFound.setVisibility(View.GONE);
            recyclerProduct.setVisibility(View.VISIBLE);
            updateStoreProductListUI(storeProductResponseModel);
        } else {
            imgNoRecordFound.setVisibility(View.VISIBLE);
            recyclerProduct.setVisibility(View.GONE);
        }
    }

    private void updateStoreProductListUI(StoreProductResponseModel storeProductResponseModel) {
        productListAdapter = new ProductAdapter(context, storeProductResponseModel.getData().getProduct());
        recyclerProduct.setLayoutManager(new LinearLayoutManager(this));
        recyclerProduct.setAdapter(productListAdapter);
        productListAdapter.notifyDataSetChanged();

    }

    @Override
    public void errorStoreResponseFromPresenter(String message) {
        dialog.dismiss();
        imgNoRecordFound.setVisibility(View.VISIBLE);
        recyclerProduct.setVisibility(View.GONE);
        String error_message = message;
        Toast.makeText(context, "" + error_message, Toast.LENGTH_SHORT).show();
    }

    public void OnStoreItemClick(String id) {
        if (NetworkUtils.isNetworkConnectionAvailable(context)) {
            dialog = UtilsAlertDialog.ShowDialog(context);
            ipProductListing.getStoreProduct(id);
        }
    }

    public void OnStoreSubItemClick(String id) {
        if (NetworkUtils.isNetworkConnectionAvailable(context)) {
            dialog = UtilsAlertDialog.ShowDialog(context);
            ipProductListing.getStoreProduct(id);
        }
    }
}