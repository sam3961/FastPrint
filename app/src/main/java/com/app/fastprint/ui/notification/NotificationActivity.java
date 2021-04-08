package com.app.fastprint.ui.notification;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fastprint.R;
import com.app.fastprint.services.APIClient;
import com.app.fastprint.services.APIInterface;
import com.app.fastprint.ui.notification.adapters.NotificationAdapter;
import com.app.fastprint.ui.notification.notificationResponse.DataItem;
import com.app.fastprint.ui.notification.notificationResponse.NotificationResponse;
import com.app.fastprint.utills.UtilsAlertDialog;
import com.app.fastprint.utills.UtilsFontFamily;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationActivity extends AppCompatActivity {

    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.tvTittle)
    TextView tvTittle;
    @BindView(R.id.recyclerNotification)
    RecyclerView recyclerNotification;
    Context context;
    NotificationAdapter notificationAdapter;
    private Dialog progressDialog;
    private ArrayList<DataItem> dataItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);
        context = NotificationActivity.this;
        progressDialog = UtilsAlertDialog.progressDialog(context);
        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));
        notificationAdapter = new NotificationAdapter(context,dataItems);
        recyclerNotification.setLayoutManager(new LinearLayoutManager(this));
        recyclerNotification.setAdapter(notificationAdapter);

        hitApiForFetchNotifications();
    }


    @OnClick(R.id.imgBack)
    public void onViewClicked() {
        finish();
    }


    private void hitApiForFetchNotifications() {

        progressDialog.show();

        APIInterface apiService = APIClient.getRetrofitInstance().create(APIInterface.class);
        Call<NotificationResponse> callGenerateToken = apiService.getNotifications();

        callGenerateToken.enqueue(new Callback<NotificationResponse>() {
            @Override
            public void onResponse(Call<NotificationResponse> call, Response<NotificationResponse> response) {
                if (response.isSuccessful()&&response.body().getData().size()>0) {
                    dataItems.addAll(response.body().getData());
                    notificationAdapter.customNotify(dataItems);
                } else
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<NotificationResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, t.getMessage() + "", Toast.LENGTH_SHORT).show();
            }
        });


    }

}