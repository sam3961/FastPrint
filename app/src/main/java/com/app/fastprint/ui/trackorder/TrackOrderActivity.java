package com.app.fastprint.ui.trackorder;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.fastprint.R;
import com.app.fastprint.baseClass.BaseClass;
import com.app.fastprint.utills.UtilsFontFamily;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TrackOrderActivity extends BaseClass {

    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.tvTittle)
    TextView tvTittle;
    @BindView(R.id.ordernumber)
    TextView ordernumber;
    @BindView(R.id.tvordernumber)
    TextView tvordernumber;
    @BindView(R.id.arrival)
    TextView arrival;
    @BindView(R.id.tvarrival)
    TextView tvarrival;
    @BindView(R.id.tvOrderProcessed)
    TextView tvOrderProcessed;
    @BindView(R.id.tvOrderShipped)
    TextView tvOrderShipped;
    @BindView(R.id.tvOrderRoute)
    TextView tvOrderRoute;
    @BindView(R.id.tvOrderArrived)
    TextView tvOrderArrived;
    @BindView(R.id.layoutImage)
    LinearLayout layoutImage;
    @BindView(R.id.tvCancelOrder)
    TextView tvCancelOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_order);
        ButterKnife.bind(this);
        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));
        tvOrderShipped.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));
        tvOrderArrived.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));
        tvOrderRoute.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));
        tvOrderProcessed.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));
        ordernumber.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));
        arrival.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));
        tvCancelOrder.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));

    }

    @OnClick({R.id.imgBack, R.id.tvCancelOrder})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.tvCancelOrder:
                break;
        }
    }
}