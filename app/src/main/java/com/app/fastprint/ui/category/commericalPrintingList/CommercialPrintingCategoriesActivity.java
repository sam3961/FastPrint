package com.app.fastprint.ui.category.commericalPrintingList;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fastprint.R;
import com.app.fastprint.baseClass.BaseClass;
import com.app.fastprint.networks.NetworkUtils;
import com.app.fastprint.ui.category.commericalPrintingList.adapter.CommercialPrintingCategoriesAdapter;
import com.app.fastprint.ui.category.commericalPrintingList.interfaces.ICPrintingCategory;
import com.app.fastprint.ui.category.commericalPrintingList.interfaces.IPCPrintingCategory;
import com.app.fastprint.ui.category.commericalPrintingList.presenter.PCommercialPrintingCategories;
import com.app.fastprint.ui.category.commericalPrintingList.responseModel.CommercialPrintingCategoriesResponseModel;
import com.app.fastprint.utills.UtilsAlertDialog;
import com.app.fastprint.utills.UtilsFontFamily;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CommercialPrintingCategoriesActivity extends BaseClass implements ICPrintingCategory {

    Context context;
    IPCPrintingCategory ipcPrintingCategory;
    Dialog dialog;
    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.tvTittle)
    TextView tvTittle;
    @BindView(R.id.recyclerCommericalPrintingCategories)
    RecyclerView recyclerCommericalPrintingCategories;
    CommercialPrintingCategoriesAdapter commercialPrintingCategoriesAdapter;

    String printing_id="";
    String printing_name="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commerical_printing_listing);
        context = CommercialPrintingCategoriesActivity.this;
        ButterKnife.bind(this);
        printing_id=getIntent().getStringExtra("printing_id");
        printing_name=getIntent().getStringExtra("printing_name");
        ipcPrintingCategory = new PCommercialPrintingCategories(this);
        if (NetworkUtils.isNetworkConnectionAvailable(context)) {
            dialog = UtilsAlertDialog.ShowDialog(context);
            ipcPrintingCategory.getCPrintingCategory(printing_id);
        }
        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        tvTittle.setText(printing_name);

    }

    @Override
    public void successResponseFromPresenter(CommercialPrintingCategoriesResponseModel commercialPrintingCategoriesResponseModel) {
        dialog.dismiss();
        if (commercialPrintingCategoriesResponseModel != null && commercialPrintingCategoriesResponseModel.getData().getCommericalCategory().size()>0) {
            updateListUI(commercialPrintingCategoriesResponseModel);
        }
    }

    private void updateListUI(CommercialPrintingCategoriesResponseModel commercialPrintingCategoriesResponseModel) {
        commercialPrintingCategoriesAdapter = new CommercialPrintingCategoriesAdapter(context, commercialPrintingCategoriesResponseModel.getData().getCommericalCategory(),printing_name);
        recyclerCommericalPrintingCategories.setLayoutManager(new LinearLayoutManager(this));
        recyclerCommericalPrintingCategories.setAdapter(commercialPrintingCategoriesAdapter);
    }

    @Override
    public void errorResponseFromPresenter(String message) {
        dialog.dismiss();
        String error_message = message;
        Toast.makeText(context, "" + error_message, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.imgBack)
    public void onViewClicked() {
        finish();
    }
}