package com.app.fastprint.ui.payment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fastprint.R;
import com.app.fastprint.baseClass.BaseClass;
import com.app.fastprint.networks.NetworkUtils;
import com.app.fastprint.services.APIClient;
import com.app.fastprint.services.APIInterface;
import com.app.fastprint.ui.cart.responseModel.BuyNowListing;
import com.app.fastprint.ui.cart.responseModel.CartListing;
import com.app.fastprint.ui.category.contactus.WebViewActivity;
import com.app.fastprint.ui.payment.adapter.PaymentOptionAdapter;
import com.app.fastprint.ui.payment.generateTokens.GenerateTokenResponse;
import com.app.fastprint.ui.payment.interfaces.IPPayment;
import com.app.fastprint.ui.payment.interfaces.IPayment;
import com.app.fastprint.ui.payment.orderCreateRequest.Billing;
import com.app.fastprint.ui.payment.orderCreateRequest.CouponsItem;
import com.app.fastprint.ui.payment.orderCreateRequest.LineItemsItem;
import com.app.fastprint.ui.payment.orderCreateRequest.OrderCreateRequest;
import com.app.fastprint.ui.payment.orderCreateRequest.Shipping;
import com.app.fastprint.ui.payment.orderCreateRequest.ShippingLinesItem;
import com.app.fastprint.ui.payment.orderCreateResponse.OrderCreateResponse;
import com.app.fastprint.ui.payment.paymentResponseModel.GetAddressResponseModel;
import com.app.fastprint.ui.payment.presenter.PPayment;
import com.app.fastprint.ui.payment.paymentResponseModel.PaymentResponseModel;
import com.app.fastprint.ui.thankyou.ThankyouActivity;
import com.app.fastprint.ui.uploadFileForm.FormFileUploadActivity;
import com.app.fastprint.ui.uploadfile.UploadFileActivity;
import com.app.fastprint.updateAddress.UpdateAddressActivity;
import com.app.fastprint.utills.AppConstants;
import com.app.fastprint.utills.AppControler;
import com.app.fastprint.utills.UtilsAlertDialog;
import com.app.fastprint.utills.UtilsFontFamily;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentActivity extends BaseClass implements IPayment {

    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.tvTittle)
    TextView tvTittle;
    @BindView(R.id.tvPaymentMothods)
    TextView tvPaymentMothods;
    @BindView(R.id.imgPaypal)
    ImageView imgPaypal;
    @BindView(R.id.imgVisa)
    ImageView imgVisa;
    @BindView(R.id.tvDetails)
    TextView tvDetails;
    @BindView(R.id.tvcards)
    TextView tvcards;
    @BindView(R.id.card)
    TextView card;
    @BindView(R.id.tvCard)
    TextView tvCard;
    @BindView(R.id.expriy)
    TextView expriy;
    @BindView(R.id.tvExpriy)
    TextView tvExpriy;
    @BindView(R.id.cvc)
    TextView cvc;
    @BindView(R.id.tvCVC)
    TextView tvCVC;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.imgAddress)
    ImageView imgAddress;
    @BindView(R.id.layoutPayment)
    RelativeLayout layoutPayment;
    @BindView(R.id.tvPayNow)
    TextView tvPayNow;
    @BindView(R.id.recyclerPaymentOption)
    RecyclerView recyclerPaymentOption;
    @BindView(R.id.radioButtonBank)
    RadioButton radioButtonBank;
    @BindView(R.id.radioButtonPaypal)
    RadioButton radioButtonPaypal;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroupPayment;
    @BindView(R.id.textViewDirectInfo)
    TextView textViewDirectInfo;
    @BindView(R.id.textViewPaypalInfo)
    TextView textViewPaypalInfo;
    Context context;
    Dialog dialog;
    IPPayment ipPayment;
    PaymentOptionAdapter paymentOptionAdapter;
    String user_id = "";
    String first_name = "";
    String last_name = "";
    String address_1 = "";
    String address_2 = "";
    String post_code = "";
    String city_name = "";
    String state_name = "";
    String country_name = "";
    String intent_From = "";
    String selectedPaymentMethod = "0";
    private ArrayList<CartListing> cartListing;
    private ArrayList<BuyNowListing> buyNowListing;
    private Dialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        ButterKnife.bind(this);
        context = PaymentActivity.this;
        ipPayment = new PPayment(this);
        progressDialog = UtilsAlertDialog.progressDialog(context);


        //  intent_From = getIntent().getStringExtra("intent_From");

        cartListing = AppControler.getInstance(this).getCartList(AppConstants.KEY_CART);


        user_id = AppControler.getInstance(context).getString(AppControler.Key.USER_ID);
        layoutPayment.setVisibility(View.GONE);
        tvPayNow.setVisibility(View.GONE);

        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoBold(this));
        tvPaymentMothods.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));
        tvDetails.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));
        address.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));
        tvPayNow.setTypeface(UtilsFontFamily.typeFaceForRobotoBold(this));
        if (NetworkUtils.isNetworkConnectionAvailable(context)) {
            dialog = UtilsAlertDialog.ShowDialog(context);
            ipPayment.getPaymentOption();
        }

        //radioButtonListener();

    }

    private void radioButtonListener() {
        radioGroupPayment.setOnCheckedChangeListener((radioGroup, i) -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();

            RadioButton radioButton = findViewById(selectedId);
            if (radioButton.getId() == R.id.radioButtonPaypal) {

            } else {

            }

        });
    }

    @OnClick({R.id.imgBack, R.id.imgAddress, R.id.tvPayNow, R.id.linearLayoutPaypal, R.id.linearLayoutBank})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.linearLayoutPaypal:
                textViewPaypalInfo.setVisibility(View.VISIBLE);
                textViewDirectInfo.setVisibility(View.GONE);
                radioButtonPaypal.setChecked(true);
                radioButtonBank.setChecked(false);
                selectedPaymentMethod = "1";
                break;
            case R.id.linearLayoutBank:
                textViewDirectInfo.setVisibility(View.VISIBLE);
                textViewPaypalInfo.setVisibility(View.GONE);
                radioButtonPaypal.setChecked(false);
                radioButtonBank.setChecked(true);
                selectedPaymentMethod = "0";
                break;

            case R.id.imgAddress:
                Intent intent = new Intent(PaymentActivity.this, UpdateAddressActivity.class);
                intent.putExtra("first_name", first_name);
                intent.putExtra("last_name", last_name);
                intent.putExtra("address_1", address_1);
                intent.putExtra("address_2", address_2);
                intent.putExtra("post_code", post_code);
                intent.putExtra("city_name", city_name);
                intent.putExtra("state_name", state_name);
                intent.putExtra("country_name", country_name);
                startActivity(intent);
                break;

            case R.id.tvPayNow:
                hitApiForFetchTokens();
                break;
        }
    }

    private void hitApiForFetchTokens() {

        progressDialog.show();

        APIInterface apiService = APIClient.getRetrofitInstance().create(APIInterface.class);
        Call<GenerateTokenResponse> callGenerateToken = apiService.generateToken();

        callGenerateToken.enqueue(new Callback<GenerateTokenResponse>() {
            @Override
            public void onResponse(Call<GenerateTokenResponse> call, Response<GenerateTokenResponse> response) {
                if (response.isSuccessful()) {
                    hitApiForCreateOrder(response.body().getData().getConsumerKey(), response.body().getData().getConsumerSecret());
                } else
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<GenerateTokenResponse> call, Throwable t) {
                Toast.makeText(context, t.getMessage() + "", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void hitApiForCreateOrder(String consumerKey, String consumerSecret) {
        APIInterface apiService = APIClient.getRetrofitInstance().create(APIInterface.class);
        Call<OrderCreateResponse> callCreateOrder = null;
        String user_phone = AppControler.getInstance(context).getString(AppControler.Key.USER_PHONE);
        String user_email = AppControler.getInstance(context).getString(AppControler.Key.USER_EMAIL);
        String couponCode = AppControler.getInstance(context).getString(AppConstants.KEY_COUPON_ID);
        String customerId = AppControler.getInstance(context).getString(AppControler.Key.USER_ID);
        String paymentMethod = "";
        String paymentMethodTitle = "";
        String status = "on-hold";
        String total = AppControler.getInstance(this).getString(AppControler.Key.CART_TOTAL).trim();
        String methodId = "fixed_rate";
        String methodTitle = "Fixed Rate";

        HashMap<String, RequestBody> params = new HashMap<>();


        if (selectedPaymentMethod.equalsIgnoreCase("0")) {
            paymentMethod = "bacs";
            paymentMethodTitle = "Direct Bank Transfer";

            RequestBody body_status = RequestBody.create(MediaType.parse("text/plain"), status);
            params.put("status", body_status);
        } else {
            paymentMethod = "paypal";
            paymentMethodTitle = "PayPal";
        }

        for (int i = 0; i < cartListing.size(); i++) {

            RequestBody body_product_id = RequestBody.create(MediaType.parse("text/plain"),
                    (cartListing.get(i).getProduct_id()));
            RequestBody body_quantity = RequestBody.create(MediaType.parse("text/plain"),
                    "1");
            RequestBody body_variationId = RequestBody.create(MediaType.parse("text/plain"),
                    String.valueOf(cartListing.get(i).getVariationId()));

            params.put("line_items[" + i + "][product_id]", body_product_id);
            params.put("line_items[" + i + "][quantity]", body_quantity);
            params.put("line_items[" + i + "][variation_id]", body_variationId);
        }

        RequestBody body_user_phone = RequestBody.create(MediaType.parse("text/plain"), user_phone);
        RequestBody body_total = RequestBody.create(MediaType.parse("text/plain"), "0.0");
        RequestBody body_method_title = RequestBody.create(MediaType.parse("text/plain"), "Free Shipping");
        RequestBody body_flat_method_id= RequestBody.create(MediaType.parse("text/plain"), "flat_rate");
        RequestBody body_user_email = RequestBody.create(MediaType.parse("text/plain"), user_email);
        RequestBody body_couponCode = RequestBody.create(MediaType.parse("text/plain"), couponCode);
        RequestBody body_paymentMethod = RequestBody.create(MediaType.parse("text/plain"), paymentMethod);
        RequestBody body_paymentMethodTitle = RequestBody.create(MediaType.parse("text/plain"), paymentMethodTitle);
        RequestBody body_customerId = RequestBody.create(MediaType.parse("text/plain"), customerId);
        RequestBody body_address_1 = RequestBody.create(MediaType.parse("text/plain"), address_1);
        RequestBody body_address_2 = RequestBody.create(MediaType.parse("text/plain"), address_2);
        RequestBody body_city_name = RequestBody.create(MediaType.parse("text/plain"), city_name);
        RequestBody body_country_name = RequestBody.create(MediaType.parse("text/plain"), country_name);
        RequestBody body_state_name = RequestBody.create(MediaType.parse("text/plain"), state_name);
        RequestBody body_post_code = RequestBody.create(MediaType.parse("text/plain"), post_code);
        RequestBody body_first_name = RequestBody.create(MediaType.parse("text/plain"), first_name);
        RequestBody body_last_name = RequestBody.create(MediaType.parse("text/plain"), last_name);
        RequestBody body_methodId = RequestBody.create(MediaType.parse("text/plain"), methodId);
        RequestBody body_methodTitle = RequestBody.create(MediaType.parse("text/plain"), methodTitle);
        RequestBody body_amr_slug = RequestBody.create(MediaType.parse("text/plain"), "create_order");

        params.put("customer_id", body_customerId);
        params.put("payment_method_title", body_paymentMethodTitle);
        params.put("payment_method", body_paymentMethod);
        params.put("set_paid", RequestBody.create(MediaType.parse("text/plain"), "false"));
        params.put("billing[postcode]", body_post_code);
        params.put("billing[state]", body_state_name);
        params.put("billing[first_name]", body_first_name);
        params.put("billing[email]", body_user_email);
        params.put("billing[address_1]", body_address_1);
        params.put("billing[last_name]", body_last_name);
        params.put("billing[country]", body_country_name);
        params.put("billing[city]", body_city_name);
        params.put("billing[phone]", body_user_phone);
        params.put("billing[address_2]", body_address_2);
        params.put("shipping[postcode]", body_post_code);
        params.put("shipping[state]", body_state_name);
        params.put("shipping[first_name]", body_first_name);
        params.put("shipping[email]", body_user_email);
        params.put("shipping[address_1]", body_address_1);
        params.put("shipping[last_name]", body_last_name);
        params.put("shipping[country]", body_country_name);
        params.put("shipping[city]", body_city_name);
        params.put("shipping[phone]", body_user_phone);
        params.put("shipping[address_2]", body_address_2);

        params.put("shipping_lines[0][method_id]", body_methodId);
        params.put("shipping_lines[0][method_title]", body_method_title);
        params.put("shipping_lines[0][total]", body_total);

        if (!couponCode.isEmpty())
            params.put("coupon_lines[0][code]", body_couponCode);


            callCreateOrder = apiService.createOrder(consumerKey, consumerSecret,
                    "create_order", params,
                    FormFileUploadActivity.imageToUpload);


        callCreateOrder.enqueue(new Callback<OrderCreateResponse>() {
            @Override
            public void onResponse(Call<OrderCreateResponse> call, Response<OrderCreateResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    if (selectedPaymentMethod.equals("0")) {
                        navigateToFeedBack(response.body().getData().getId() + "");
                    } else {
                        navigateToPaypal(response.body().getData().getId() + "");
                    }
                } else
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<OrderCreateResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, t.getMessage() + "", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void navigateToPaypal(String orderId) {
        Intent intentwebView = new Intent(PaymentActivity.this, WebViewActivity.class);
        intentwebView.putExtra("webSiteUrl", AppConstants.PAYPAL_URL + orderId);
        intentwebView.putExtra("intent_from", "Payment");
        intentwebView.putExtra("orderId", orderId);
        startActivity(intentwebView);
    }

    private void navigateToFeedBack(String orderId) {
        Intent intentPayNow = new Intent(PaymentActivity.this, ThankyouActivity.class);
        intentPayNow.putExtra("orderId", orderId);
        startActivity(intentPayNow);
        finish();
    }

    @Override
    public void successResponseFromPresenter(PaymentResponseModel paymentResponseModel) {
        dialog.dismiss();
        // layoutPayment.setVisibility(View.VISIBLE);
        tvPayNow.setVisibility(View.VISIBLE);

        if (paymentResponseModel.getData() != null) {
            updateListUI(paymentResponseModel);
        }
        if (NetworkUtils.isNetworkConnectionAvailable(context)) {
            dialog = UtilsAlertDialog.ShowDialog(context);
            ipPayment.getBillingAddress(user_id);
        }
    }

    private void updateListUI(PaymentResponseModel paymentResponseModel) {
        int columns = 2;
        paymentOptionAdapter = new PaymentOptionAdapter(context, paymentResponseModel.getData().getSendPayment());
        recyclerPaymentOption.setLayoutManager(new GridLayoutManager(context, columns));
        recyclerPaymentOption.setAdapter(paymentOptionAdapter);

    }

    @Override
    public void errorResponseFromPresneter(String message) {
        dialog.dismiss();
        layoutPayment.setVisibility(View.GONE);
        // tvPayNow.setVisibility(View.GONE);
        String error_message = message;
        Toast.makeText(context, "" + error_message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void successAddressResponseFromPresenter(GetAddressResponseModel getAddressResponseModel) {
        dialog.dismiss();
        first_name = getAddressResponseModel.getData().getFirstName();
        last_name = getAddressResponseModel.getData().getLastName();
        address_1 = getAddressResponseModel.getData().getAddress1();
        address_2 = getAddressResponseModel.getData().getAddress2();
        post_code = getAddressResponseModel.getData().getPostCode();
        city_name = getAddressResponseModel.getData().getCityName();
        state_name = getAddressResponseModel.getData().getStateName();
        country_name = getAddressResponseModel.getData().getCountryName();
        tvAddress.setText(getAddressResponseModel.getData().getBillingAddress());
    }

    @Override
    public void errorAddressResponseFromPresenter(String message) {
        dialog.dismiss();
        Toast.makeText(context, "" + message, Toast.LENGTH_SHORT).show();
    }
}
