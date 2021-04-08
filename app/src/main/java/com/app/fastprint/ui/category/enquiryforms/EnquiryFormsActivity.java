package com.app.fastprint.ui.category.enquiryforms;

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
import com.app.fastprint.ui.category.enquiryforms.adapter.EnquiryAdapter;
import com.app.fastprint.ui.category.enquiryforms.interfaces.IEnquiryForms;
import com.app.fastprint.ui.category.enquiryforms.interfaces.IPEnquiryForms;
import com.app.fastprint.ui.category.enquiryforms.presenters.PEnquiryForm;
import com.app.fastprint.ui.category.enquiryforms.responseModel.FormsResponseModel;
import com.app.fastprint.utills.UtilsAlertDialog;
import com.app.fastprint.utills.UtilsFontFamily;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EnquiryFormsActivity extends BaseClass implements IEnquiryForms {

    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.tvTittle)
    TextView tvTittle;
    @BindView(R.id.RecyclerEnquiryForm)
    RecyclerView RecyclerEnquiryForm;
    EnquiryAdapter enquiryAdapter;
    Context context;
    Dialog dialog;
    IPEnquiryForms ipEnquiryForms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enquiry_forms);
        ButterKnife.bind(this);
        context=EnquiryFormsActivity.this;
        ipEnquiryForms=new PEnquiryForm(this);
        if (NetworkUtils.isNetworkConnectionAvailable(context)) {
            dialog = UtilsAlertDialog.ShowDialog(context);
            ipEnquiryForms.getFromList();
        }
        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));
    }

    @OnClick(R.id.imgBack)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void successResponseFromPresenters(FormsResponseModel formsResponseModel) {
        dialog.dismiss();
        if (formsResponseModel!=null){
            updateListUI(formsResponseModel);
        }
    }

    private void updateListUI(FormsResponseModel formsResponseModel) {
        enquiryAdapter = new EnquiryAdapter(context, formsResponseModel.getData().getEnquiry());
        RecyclerEnquiryForm.setLayoutManager(new LinearLayoutManager(this));
        RecyclerEnquiryForm.setAdapter(enquiryAdapter);

    }

    @Override
    public void errorResponseFromPresenters(String message) {
        dialog.dismiss();
        String error_message = message;
        Toast.makeText(context, "" + error_message, Toast.LENGTH_SHORT).show();
    }
}
