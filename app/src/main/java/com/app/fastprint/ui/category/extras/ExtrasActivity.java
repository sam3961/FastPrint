package com.app.fastprint.ui.category.extras;

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
import com.app.fastprint.ui.category.extras.adapter.ExtrasAdapter;
import com.app.fastprint.ui.category.extras.interfaces.IExtras;
import com.app.fastprint.ui.category.extras.interfaces.IPExtras;
import com.app.fastprint.ui.category.extras.presenters.PExtras;
import com.app.fastprint.ui.category.extras.responseModel.ExtrasResponseModel;
import com.app.fastprint.utills.UtilsAlertDialog;
import com.app.fastprint.utills.UtilsFontFamily;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExtrasActivity extends BaseClass implements IExtras {

    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.tvTittle)
    TextView tvTittle;
    @BindView(R.id.RecyclerExtras)
    RecyclerView RecyclerExtras;
    Context context;
    ExtrasAdapter extrasAdapter;
    Dialog dialog;
    IPExtras ipExtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extras);
        ButterKnife.bind(this);
        context = ExtrasActivity.this;
        ipExtras = new PExtras(this);
        if (NetworkUtils.isNetworkConnectionAvailable(context)) {
            dialog = UtilsAlertDialog.ShowDialog(context);
            ipExtras.getExtras();
        }
        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
    }

    @OnClick(R.id.imgBack)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void successResponseFromPresenter(ExtrasResponseModel extrasResponseModel) {
        dialog.dismiss();
        if (extrasResponseModel != null) {
            updateListUI(extrasResponseModel);
        }
    }

    private void updateListUI(ExtrasResponseModel extrasResponseModel) {
        extrasAdapter = new ExtrasAdapter(context, extrasResponseModel.getData().getExtras());
        RecyclerExtras.setLayoutManager(new LinearLayoutManager(this));
        RecyclerExtras.setAdapter(extrasAdapter);
    }

    @Override
    public void errorResponseFromPresenter(String message) {
        dialog.dismiss();
        String error_message = message;
        Toast.makeText(context, "" + error_message, Toast.LENGTH_SHORT).show();
    }
}