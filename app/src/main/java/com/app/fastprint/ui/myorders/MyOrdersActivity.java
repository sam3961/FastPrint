package com.app.fastprint.ui.myorders;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.fastprint.R;
import com.app.fastprint.services.APIClient;
import com.app.fastprint.services.APIInterface;
import com.app.fastprint.ui.myorders.adapters.MyOrdersAdapter;
import com.app.fastprint.ui.myorders.ordersResonse.DataItem;
import com.app.fastprint.ui.myorders.ordersResonse.OrdersResponse;
import com.app.fastprint.ui.payment.generateTokens.GenerateTokenResponse;
import com.app.fastprint.utills.AppControler;
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

public class MyOrdersActivity extends AppCompatActivity {
    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.tvTittle)
    TextView tvTittle;
    @BindView(R.id.recyclerOrders)
    RecyclerView recyclerOrders;
    MyOrdersAdapter myOrdersAdapter;
    Context context;
    private Dialog progressDialog;
    private List<DataItem> ordersResponses;
    private String consumer_key,consumer_secret;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);
        ButterKnife.bind(this);

        progressDialog = UtilsAlertDialog.progressDialog(this);
        context = MyOrdersActivity.this;
        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));

        recyclerOrders.setLayoutManager(new LinearLayoutManager(this));


        hitApiForFetchTokens();
    }

    @OnClick(R.id.imgBack)
    public void onViewClicked() {
        finish();

    }

    private void hitApiForFetchTokens() {

        progressDialog.show();

        APIInterface apiService = APIClient.getRetrofitInstance().create(APIInterface.class);
        Call<GenerateTokenResponse> callGenerateToken = apiService.generateToken();

        callGenerateToken.enqueue(new Callback<GenerateTokenResponse>() {
            @Override
            public void onResponse(Call<GenerateTokenResponse> call, Response<GenerateTokenResponse> response) {
                if (response.isSuccessful()) {
                    consumer_key = response.body().getData().getConsumerKey();
                    consumer_secret = response.body().getData().getConsumerSecret();
                    apiListAllOrders(consumer_key, consumer_secret);
                } else
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<GenerateTokenResponse> call, Throwable t) {
                Toast.makeText(context, t.getMessage() + "", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void apiListAllOrders(String consumerKey, String consumerSecret) {
        APIInterface apiService = APIClient.getRetrofitInstance().create(APIInterface.class);
        Call<OrdersResponse> callOrder = apiService.listOrders(consumerKey,consumerSecret,
                AppControler.getInstance(context).getString(AppControler.Key.USER_ID),"my_orders");

        callOrder.enqueue(new Callback<OrdersResponse>() {
            @Override
            public void onResponse(Call<OrdersResponse> call, Response<OrdersResponse> response) {
                progressDialog.dismiss();
                ordersResponses =new ArrayList<>();
                if (response.isSuccessful()) {
                    ordersResponses.addAll(response.body().getData());
                    setAdapter();
                } else {
                    Toast.makeText(context,  "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OrdersResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, t.getMessage() + "", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void setAdapter() {
        myOrdersAdapter = new MyOrdersAdapter(this,ordersResponses,consumer_key,consumer_secret);
        recyclerOrders.setAdapter(myOrdersAdapter);
    }

}