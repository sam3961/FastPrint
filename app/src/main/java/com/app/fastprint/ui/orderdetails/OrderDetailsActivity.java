package com.app.fastprint.ui.orderdetails;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fastprint.R;
import com.app.fastprint.baseClass.BaseClass;
import com.app.fastprint.services.APIClient;
import com.app.fastprint.services.APIInterface;
import com.app.fastprint.ui.orderdetails.adapter.MyOrderDetailAdapter;
import com.app.fastprint.ui.orderdetails.orderDetailResponse.LineItemsItem;
import com.app.fastprint.ui.orderdetails.orderDetailResponse.OrderDetailResponse;
import com.app.fastprint.ui.trackorder.TrackOrderActivity;
import com.app.fastprint.utills.UtilsAlertDialog;
import com.app.fastprint.utills.UtilsFontFamily;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailsActivity extends BaseClass {

    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.tvTittle)
    TextView tvTittle;
    @BindView(R.id.imgProduct)
    ImageView imgProduct;
    @BindView(R.id.tvProductName)
    TextView tvProductName;
    @BindView(R.id.tvDiscountPrice)
    TextView tvDiscountPrice;
    @BindView(R.id.tvPrice)
    TextView tvPrice;
    @BindView(R.id.tvorderID)
    TextView tvorderID;
    @BindView(R.id.tvorderDate)
    TextView tvorderDate;
    @BindView(R.id.tvTrackOrder)
    TextView tvTrackOrder;
    @BindView(R.id.tvOrderStatus)
    TextView tvOrderStatus;
    @BindView(R.id.tvDescriptions)
    TextView tvDescriptions;
    @BindView(R.id.quantity)
    TextView quantity;
    @BindView(R.id.tvQuantity)
    TextView tvQuantity;
    @BindView(R.id.coveroptions)
    TextView coveroptions;
    @BindView(R.id.tvCoveroptions)
    TextView tvCoveroptions;
    @BindView(R.id.pageincluding)
    TextView pageincluding;
    @BindView(R.id.tvPageincluding)
    TextView tvPageincluding;
    @BindView(R.id.tvOrderSummery)
    TextView tvOrderSummery;
    @BindView(R.id.bagTotal)
    TextView bagTotal;
    @BindView(R.id.bagDiscount)
    TextView bagDiscount;
    @BindView(R.id.subtotal)
    TextView subtotal;
    @BindView(R.id.estimate)
    TextView estimate;
    @BindView(R.id.copunDiscount)
    TextView copunDiscount;
    @BindView(R.id.dChargres)
    TextView dChargres;
    @BindView(R.id.totalPayable)
    TextView totalPayable;
    @BindView(R.id.tvbagTotal)
    TextView tvbagTotal;
    @BindView(R.id.tvbagDiscount)
    TextView tvbagDiscount;
    @BindView(R.id.tvsubtotal)
    TextView tvsubtotal;
    @BindView(R.id.tvVatCst)
    TextView tvVatCst;
    @BindView(R.id.tvCoupunDiscount)
    TextView tvCoupunDiscount;
    @BindView(R.id.tvDCharges)
    TextView tvDCharges;
    @BindView(R.id.tvTotalPay)
    TextView tvTotalPay;
    @BindView(R.id.shippingAddress)
    TextView shippingAddress;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvEmail)
    TextView tvEmail;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.tvshippingaddress)
    TextView tvshippingaddress;
    @BindView(R.id.billingAddress)
    TextView billingAddress;
    @BindView(R.id.tvBName)
    TextView tvBName;
    @BindView(R.id.tvBEmail)
    TextView tvBEmail;
    @BindView(R.id.tvBPhone)
    TextView tvBPhone;
    @BindView(R.id.tvbillingaddress)
    TextView tvbillingaddress;
    @BindView(R.id.tvEmailInvoice)
    TextView tvEmailInvoice;
    @BindView(R.id.tvNeedHelp)
    TextView tvNeedHelp;
    @BindView(R.id.recyclerViewOrders)
    RecyclerView recyclerViewOrders;
    private Dialog progressDialog;
    private List<LineItemsItem> ordersResponses;
    private OrderDetailResponse orderDetailResponse;
    private MyOrderDetailAdapter myOrderDetailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        ButterKnife.bind(this);

        progressDialog = UtilsAlertDialog.progressDialog(this);

        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));
        tvProductName.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));
        tvPrice.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));
        tvorderDate.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));
        tvTrackOrder.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));
        quantity.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));
        coveroptions.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));
        pageincluding.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));
        tvEmailInvoice.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));
        tvNeedHelp.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));
        tvorderID.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));
        tvOrderStatus.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));
        tvOrderSummery.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));
        shippingAddress.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));
        billingAddress.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));
        tvDiscountPrice.setPaintFlags(tvDiscountPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        tvDiscountPrice.setText("$" + "120.00");

        recyclerViewOrders.setLayoutManager(new LinearLayoutManager(this));

        apiOrderDetails(getIntent().getStringExtra("consumer_key"),
                getIntent().getStringExtra("consumer_secret"));
    }

    @OnClick({R.id.imgBack, R.id.tvTrackOrder, R.id.tvEmailInvoice, R.id.tvNeedHelp})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.tvTrackOrder:
                Intent intent = new Intent(OrderDetailsActivity.this, TrackOrderActivity.class);
                startActivity(intent);
                break;
            case R.id.tvEmailInvoice:
                break;
            case R.id.tvNeedHelp:
                break;
        }
    }

    public void apiOrderDetails(String consumerKey, String consumerSecret) {
        progressDialog.show();

        String orderId =String.valueOf(getIntent().getIntExtra("orderid",0));
        APIInterface apiService = APIClient.getRetrofitInstance().create(APIInterface.class);
        Call<OrderDetailResponse> callOrder = apiService.orderDetail(orderId,consumerKey,consumerSecret,"get_order");

        callOrder.enqueue(new Callback<OrderDetailResponse>() {
            @Override
            public void onResponse(Call<OrderDetailResponse> call, Response<OrderDetailResponse> response) {
                progressDialog.dismiss();
                ordersResponses=new ArrayList<>();
                if (response.isSuccessful()) {
                    orderDetailResponse= response.body();
                    ordersResponses.addAll(response.body().getData().getLineItems());
                    setAdapter();
                } else {
                    Toast.makeText(OrderDetailsActivity.this,  "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OrderDetailResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(OrderDetailsActivity.this, t.getMessage() + "", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void setAdapter() {
        myOrderDetailAdapter = new MyOrderDetailAdapter(this,ordersResponses,orderDetailResponse.getData().getCurrencySymbol());
        recyclerViewOrders.setAdapter(myOrderDetailAdapter);
    }

}