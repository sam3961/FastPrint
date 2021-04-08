package com.app.fastprint.ui.category.contactus;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.app.fastprint.R;
import com.app.fastprint.networks.NetworkUtils;
import com.app.fastprint.ui.thankyou.ThankyouActivity;
import com.app.fastprint.utills.AppConstants;
import com.app.fastprint.utills.UtilsAlertDialog;
import com.app.fastprint.utills.UtilsFontFamily;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WebViewActivity extends AppCompatActivity {

    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.tvTittle)
    TextView tvTittle;
    @BindView(R.id.webView)
    WebView webView;

    String webSiteUrl = "";
    String intentFrom = "";
    String orderId = "";
    Context context;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        context = WebViewActivity.this;
        dialog = UtilsAlertDialog.progressDialog(context);
        webSiteUrl = getIntent().getStringExtra("webSiteUrl");
        intentFrom = getIntent().getStringExtra("intent_from");
        if (getIntent().hasExtra("orderId"))
            orderId = getIntent().getStringExtra("orderId");

        tvTittle.setText(intentFrom);
        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));

        if (webSiteUrl != null) {
            if (NetworkUtils.isNetworkConnectionAvailable(context)) {
                webView.getSettings().setSupportMultipleWindows(true);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.getSettings().setLoadWithOverviewMode(true);
                webView.getSettings().setUseWideViewPort(true);

                webView.getSettings().setSupportZoom(true);
                webView.getSettings().setBuiltInZoomControls(true);
                webView.getSettings().setDisplayZoomControls(false);
                webView.getSettings().setDomStorageEnabled(true);
                webView.getSettings().setAppCacheEnabled(true);
                webView.getSettings().setLoadsImagesAutomatically(true);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
                }
                webView.setScrollBarStyle(webView.SCROLLBARS_OUTSIDE_OVERLAY);
                webView.setScrollbarFadingEnabled(false);
                webView.setWebViewClient(new WebViewClient() {
                    @Override
                    public void onPageStarted(WebView view, String url, Bitmap favicon) {
                        try {
                            dialog.show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onPageFinished(WebView view, String url) {
                        dialog.dismiss();
                    }

                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                        if (request.getUrl().toString().startsWith(AppConstants.PAYPAL_URL_SUCCESS)) {
                            Toast.makeText(context, "Your Payment has been Successful", Toast.LENGTH_LONG).show();
                            navigateToFeedBack();
                        } else if (request.getUrl().toString().startsWith(AppConstants.PAYPAL_URL_FAIL)
                                || request.getUrl().toString().startsWith(AppConstants.PAYPAL_URL_SEND_FAIL)) {
                            Toast.makeText(context, "Your Payment has Failed", Toast.LENGTH_LONG).show();
                            finish();
                        } else if (request.getUrl().toString().startsWith(AppConstants.PAYPAL_URL_CANCEL) ||
                                request.getUrl().toString().startsWith(AppConstants.PAYPAL_URL__SEND_CANCEL)) {
                            Toast.makeText(context, "Your PayPal Transaction has been Canceled", Toast.LENGTH_LONG).show();
                            finish();
                        } else if (request.getUrl().toString().startsWith(AppConstants.PAYPAL_URL_SEND_MONEY_SUCCESS)) {
                            Toast.makeText(context, "Money Sent Successfully", Toast.LENGTH_LONG).show();
                            finish();
                        }
                        return super.shouldOverrideUrlLoading(view, request);

                    }
                });

                webView.loadUrl(webSiteUrl);

            }
        }

    }

    private void navigateToFeedBack() {
        Intent intentPayNow = new Intent(WebViewActivity.this, ThankyouActivity.class);
        intentPayNow.putExtra("orderId", orderId);
        startActivity(intentPayNow);
        finish();
    }


    @OnClick(R.id.imgBack)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.destroy();
    }
}