package com.app.fastprint.ui.sendPayment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fastprint.R;
import com.app.fastprint.baseClass.BaseClass;
import com.app.fastprint.networks.NetworkUtils;
import com.app.fastprint.ui.category.contactus.WebViewActivity;
import com.app.fastprint.ui.sendPayment.adapter.PaymentAdapter;
import com.app.fastprint.ui.sendPayment.interfaces.IPSendPayment;
import com.app.fastprint.ui.sendPayment.interfaces.ISendPayment;
import com.app.fastprint.ui.sendPayment.presenter.PSendPayment;
import com.app.fastprint.ui.sendPayment.sendPaymentResponseModel.SendPaymentResponseModel;
import com.app.fastprint.utills.AppConstants;
import com.app.fastprint.utills.AppControler;
import com.app.fastprint.utills.UtilsAlertDialog;
import com.app.fastprint.utills.UtilsFontFamily;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SendPaymentActivity extends BaseClass implements ISendPayment, PaymentAdapter.onPaypalClickListener {

    @BindView(R.id.imgBack)
    ImageView imgBack;

    @BindView(R.id.tvTittle)
    TextView tvTittle;

    @BindView(R.id.recyclerSendPayment)
    RecyclerView recyclerSendPayment;

    PaymentAdapter paymentAdapter;
    Context context;
    Dialog dialog;
    IPSendPayment ipSendPayment;
    List<SendPaymentResponseModel.Data.SendPayment> sendPaymentsList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_payment);
        ButterKnife.bind(this);
        context=SendPaymentActivity.this;
        ipSendPayment=new PSendPayment(this);
        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        if (NetworkUtils.isNetworkConnectionAvailable(context)) {
            dialog = UtilsAlertDialog.ShowDialog(context);
            ipSendPayment.getPaymentList();
        }

    }

    @OnClick(R.id.imgBack)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void successResponseFromPresnter(SendPaymentResponseModel sendPaymentResponseModel) {
        dialog.dismiss();
        if (sendPaymentResponseModel!=null){
            updateListUI(sendPaymentResponseModel);
        }
    }

    private void updateListUI(SendPaymentResponseModel sendPaymentResponseModel) {
        sendPaymentsList.addAll(sendPaymentResponseModel.getData().getSendPayment());
        sendPaymentsList.subList(0, 3).clear();
        paymentAdapter = new PaymentAdapter(context,sendPaymentsList,this);
        recyclerSendPayment.setLayoutManager(new LinearLayoutManager(this));
        recyclerSendPayment.setAdapter(paymentAdapter);
    }

    @Override
    public void failedResponseFromPresnter(String message) {
        dialog.dismiss();
        String error_message = message;
        Toast.makeText(context, "" + error_message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaypalClick() {
        enterAmountDialog();
    }

    private Dialog enterAmountDialog() {
        Dialog dialog = null;
        try {
            dialog = new Dialog(context, R.style.Theme_Dialog);
            dialog.setContentView(R.layout.dialog_enter_amount);
            dialog.setCancelable(true);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            EditText editTextAmount = dialog.findViewById(R.id.edAmount);
            TextView tvPay = dialog.findViewById(R.id.tvPay);
            ImageView close = dialog.findViewById(R.id.close);

            Dialog finalDialog = dialog;

            tvPay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (editTextAmount.getText().toString().length()>0) {
                        double amount = Double.parseDouble(editTextAmount.getText().toString());
                        if (amount>=1.00) {
                            navigateToPaypal(editTextAmount.getText().toString());
                            finalDialog.dismiss();
                        }else{
                            editTextAmount.requestFocus();
                            editTextAmount.setError("Enter amount should be greater or equals to 1.00");
                        }
                    }else{
                        editTextAmount.requestFocus();
                        editTextAmount.setError("Enter amount should be greater or equals to 1.00");
                    }
                }
            });
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finalDialog.dismiss();
                }
            });
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dialog;

    }

    private void navigateToPaypal(String amount) {
        Intent intentwebView = new Intent(this, WebViewActivity.class);
        intentwebView.putExtra("webSiteUrl", AppConstants.PAYPAL_SEND_MONEY_URL +
                AppControler.getInstance().getString(AppControler.Key.USER_ID)+"&amount="+
                amount);
        intentwebView.putExtra("intent_from", "Send Payment");
        startActivity(intentwebView);
    }


}