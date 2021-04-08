package com.app.fastprint.ui.findus;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.fastprint.R;
import com.app.fastprint.baseClass.BaseClass;
import com.app.fastprint.networks.NetworkUtils;
import com.app.fastprint.ui.findus.interfaces.IFindUs;
import com.app.fastprint.ui.findus.interfaces.IPFindUs;
import com.app.fastprint.ui.findus.presenters.PFindUs;
import com.app.fastprint.ui.findus.responseModel.FindUsResponseModel;
import com.app.fastprint.ui.map.MapsActivity;
import com.app.fastprint.utills.UtilsAlertDialog;
import com.app.fastprint.utills.UtilsFontFamily;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FindUsActivity extends BaseClass implements IFindUs {

    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.tvTittle)
    TextView tvTittle;
    @BindView(R.id.imgLocation)
    ImageView imgLocation;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.tvLocation)
    TextView tvLocation;
    @BindView(R.id.adddress)
    TextView adddress;
    @BindView(R.id.layoutLocation)
    RelativeLayout layoutLocation;
    @BindView(R.id.layoutFindUs)
    RelativeLayout layoutFindUs;
    Dialog dialog;
    Context context;
    String strAddress = "";
    IPFindUs ipFindUs;
    String latitude = "";
    String longitude = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_us);
        ButterKnife.bind(this);
        ipFindUs = new PFindUs(this);
        context = FindUsActivity.this;
        layoutFindUs.setVisibility(View.GONE);
        if (NetworkUtils.isNetworkConnectionAvailable(context)) {
            dialog = UtilsAlertDialog.ShowDialog(context);
            ipFindUs.findUs();
        }

        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));
        tvAddress.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));
        adddress.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));

    }

    @OnClick({R.id.imgBack, R.id.layoutLocation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.layoutLocation:
                openLocationDialog();
                break;
        }
    }

    private void openLocationDialog() {

        dialog = new Dialog(context, R.style.DialogThemes);
        dialog.setContentView(R.layout.layout_location);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);

        TextView tvCancel = (TextView) dialog.findViewById(R.id.tvCancel);
        TextView tvShare = (TextView) dialog.findViewById(R.id.tvShare);
        TextView tvMap = (TextView) dialog.findViewById(R.id.tvMap);
        TextView tvDirection = (TextView) dialog.findViewById(R.id.tvDirection);

        tvShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Double mlatitude = Double.valueOf(latitude);
                Double mlongitude = Double.valueOf(longitude);

                String uri = "http://maps.google.com/maps?saddr=" + mlatitude + "," + mlongitude;
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String ShareSub = "Here is my location";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, ShareSub);
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, uri);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));

                dialog.dismiss();
            }
        });

        tvMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindUsActivity.this, MapsActivity.class);
                intent.putExtra("latitude", latitude);
                intent.putExtra("longitude", longitude);
                intent.putExtra("strAddress", strAddress);
                startActivity(intent);
                dialog.dismiss();
            }
        });
        tvDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?daddr=%f,%f", Double.parseDouble(latitude)
                        , Double.parseDouble(longitude));
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setPackage("com.google.android.apps.maps");
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException ex) {
                    try {
                        Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                        startActivity(unrestrictedIntent);
                    } catch (ActivityNotFoundException innerEx) {
                        Toast.makeText(context, "Please install a maps application", Toast.LENGTH_SHORT).show();
                    }
                }
                dialog.dismiss();
            }
        });

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    @Override
    public void successResponseFromPresenter(FindUsResponseModel findUsResponseModel) {
        dialog.dismiss();
        if (findUsResponseModel != null) {
            layoutFindUs.setVisibility(View.VISIBLE);
            strAddress = findUsResponseModel.getData().getAddress();
            tvLocation.setText(strAddress);
            if (findUsResponseModel.getData().getLatitude() != null) {
                //latitude = findUsResponseModel.getData().getLatitude();
                latitude ="32.007549";
            }
            if (findUsResponseModel.getData().getLongitude() != null) {
                //longitude = findUsResponseModel.getData().getLongitude();
                longitude = "35.787868";
            }
        }
    }

    @Override
    public void errorResponseFromPresenter(String message) {
        dialog.dismiss();
        layoutFindUs.setVisibility(View.GONE);
        String error_message = message;
        Toast.makeText(context, "" + error_message, Toast.LENGTH_SHORT).show();
    }
}