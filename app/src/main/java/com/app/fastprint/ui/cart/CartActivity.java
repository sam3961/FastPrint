package com.app.fastprint.ui.cart;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fastprint.R;
import com.app.fastprint.baseClass.BaseClass;
import com.app.fastprint.services.APIClient;
import com.app.fastprint.services.APIInterface;
import com.app.fastprint.ui.cart.adapters.CartItemAdapter;
import com.app.fastprint.ui.cart.couponsResponse.CouponsResponse;
import com.app.fastprint.ui.cart.couponsResponse.DataItem;
import com.app.fastprint.ui.cart.responseModel.CartListing;
import com.app.fastprint.ui.login.LoginActivity;
import com.app.fastprint.ui.payment.generateTokens.GenerateTokenResponse;
import com.app.fastprint.ui.product.ProductListingActivity;
import com.app.fastprint.ui.uploadFileForm.FormFileUploadActivity;
import com.app.fastprint.utills.AppConstants;
import com.app.fastprint.utills.AppControler;
import com.app.fastprint.utills.UtilsAlertDialog;
import com.app.fastprint.utills.UtilsFontFamily;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends BaseClass implements CartItemAdapter.OnDeleteCartListener {

    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.tvTittle)
    TextView tvTittle;
    @BindView(R.id.tvyouhave)
    TextView tvyouhave;
    @BindView(R.id.tvItems)
    TextView tvItems;
    @BindView(R.id.recyclerCartItems)
    RecyclerView recyclerCartItems;
    @BindView(R.id.subtotal)
    TextView subtotal;
    @BindView(R.id.tvSubtotal)
    TextView tvSubtotal;
    @BindView(R.id.shipping)
    TextView shipping;
    @BindView(R.id.tvTax)
    TextView tvTax;
    @BindView(R.id.tvshipping)
    TextView tvshipping;
    @BindView(R.id.total)
    TextView total;
    @BindView(R.id.tvtotal)
    TextView tvtotal;
    @BindView(R.id.tvUploadFile)
    TextView tvUploadFile;
    @BindView(R.id.tvApplyCoupon)
    TextView tvApplyCoupon;
    @BindView(R.id.tvContinueshopping)
    TextView tvContinueshopping;
    @BindView(R.id.linearLayoutEmptyCart)
    LinearLayout linearLayoutEmptyCart;
    @BindView(R.id.relativeLayoutCartMain)
    RelativeLayout relativeLayoutCartMain;
    @BindView(R.id.editTextCoupon)
    EditText editTextCoupon;
    Context context;
    CartItemAdapter adapter;
    CartItemAdapter.OnDeleteCartListener onDeleteCartListener;
    private double tax = 0.00;
    private double subtotalAmount = 0.00;
    private final double shipping_fee = 0.00;
    private String consumer_key, consumer_secret,currencySymbol="";
    private ArrayList<CartListing> cartListings;
    private Dialog progressDialog;
    private ArrayList<DataItem> couponsList = new ArrayList<>();
    private DataItem selectedCoupon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ButterKnife.bind(this);
        context = CartActivity.this;
        onDeleteCartListener = this;
        AppControler.getInstance(this).put(AppConstants.KEY_COUPON_ID, "");
        progressDialog = UtilsAlertDialog.progressDialog(context);

        tvItems.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        tvUploadFile.setTypeface(UtilsFontFamily.typeFaceForRobotoBold(context));
        tvContinueshopping.setTypeface(UtilsFontFamily.typeFaceForRobotoBold(context));


        initalizeCartList();
        adapter = new CartItemAdapter(this, cartListings, onDeleteCartListener);
        recyclerCartItems.setLayoutManager(new LinearLayoutManager(this));
        recyclerCartItems.setAdapter(adapter);


        setTotalPricing();
        hitApiForFetchTokens();
    }

    private void initalizeCartList() {
        cartListings = AppControler.getInstance(this).getCartList(AppConstants.KEY_CART);
        if (cartListings == null || cartListings.size() < 1) {
            cartListings = new ArrayList<>();
            relativeLayoutCartMain.setVisibility(View.GONE);
            linearLayoutEmptyCart.setVisibility(View.VISIBLE);
        }
        if (cartListings.size() == 1)
            tvItems.setText(" " + cartListings.size() + " Item");
        else
            tvItems.setText(" " + cartListings.size() + " Items");

    }

    private void setTotalPricing() {
        double amount = shipping_fee;
        if (cartListings.size()>0) {
            currencySymbol = cartListings.get(0).getCurrencySymbol();
            for (int i = 0; i < cartListings.size(); i++) {
                String strAmount = cartListings.get(i).getAmount();
                if (strAmount.contains(","))
                    strAmount = strAmount.replace(",", "");
                amount = amount + Double.parseDouble(strAmount);
            }
        }
        subtotalAmount = amount;
        setAmountTexts(amount);

    }

    private void setAmountTexts(double amount) {
        DecimalFormat format = new DecimalFormat("#");
        format.setMinimumFractionDigits(2);
        tax = amount * 0.16; //16 % tax
        tvtotal.setText(currencySymbol + format.format(amount + tax));
        tvSubtotal.setText(currencySymbol + format.format(amount));
        tvTax.setText(currencySymbol+ format.format(tax));
        tvshipping.setText(currencySymbol + "0" + format.format(shipping_fee));
    }

    @OnClick({R.id.imgBack, R.id.tvUploadFile, R.id.tvContinueshopping, R.id.tvApplyCoupon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvContinueshopping:
                Intent intent = new Intent(CartActivity.this, ProductListingActivity.class);
                startActivity(intent);
            case R.id.imgBack:
                finish();
            case R.id.tvApplyCoupon:
                if (editTextCoupon.getText().toString().length() > 0)
                    checkIfCouponValidate();
                break;
            case R.id.tvUploadFile:
                String auth_token = AppControler.getInstance(context).getString(AppControler.Key.AUTH_TOKEN);
                if (auth_token != null && !auth_token.isEmpty()) {
                    AppControler.getInstance(this).put(AppControler.Key.CART_TOTAL, tvtotal.getText().toString()
                            .replaceAll(cartListings.get(0).getCurrencySymbol(), ""));
                    Intent intent1 = new Intent(CartActivity.this, FormFileUploadActivity.class);
                    intent1.putExtra("intent_From", AppConstants.KEY_CART);
                    startActivity(intent1);
                } else {
                    Toast.makeText(context, "You must be logged in to proceed to checkout", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(CartActivity.this, LoginActivity.class);
                    intent1.putExtra("intent_type", "from_cart_details");
                    startActivity(intent1);
                }
                break;
        }
    }

    private void checkIfCouponValidate() {
        boolean isCouponFound = false;
        double percentage = 0.0;
        double fixed = 0.0;
        String coupon = editTextCoupon.getText().toString();
        String expiryDate = "";
        for (int i = 0; i < couponsList.size(); i++) {
            if (couponsList.get(i).getCode().equalsIgnoreCase(coupon)) {
                isCouponFound = true;
                selectedCoupon = couponsList.get(i);
                expiryDate = selectedCoupon.getDateExpires();
                AppControler.getInstance(this).put(AppConstants.KEY_COUPON_ID, selectedCoupon.getCode());
                if (selectedCoupon.getDiscountType().equalsIgnoreCase("percent")) {
                    percentage = Double.parseDouble(selectedCoupon.getAmount());
                } else {
                    fixed = Double.parseDouble(selectedCoupon.getAmount());
                }
                break;
            }
        }
        if (isCouponFound) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date strDate = null;
            try {
                strDate = sdf.parse(expiryDate);
                if (new Date().after(strDate)) {
                    Toast.makeText(context, "Incorrect coupon code", Toast.LENGTH_SHORT).show();
                    return;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Toast.makeText(context, "Coupon Applied Successfully", Toast.LENGTH_SHORT).show();
            AppControler.getInstance(this).dismissKeyboard(this, editTextCoupon);
            if (fixed > 0.0) {
                setAmountTexts(subtotalAmount - fixed);
            } else if (percentage > 0.0) {
                double amount = percentage * subtotalAmount;
                double finalPercentAmount = amount / 100;
                setAmountTexts(subtotalAmount - finalPercentAmount);
            }
        } else
            Toast.makeText(context, "Incorrect coupon code", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDeleteProduct(int position) {
        cartListings.remove(position);
        AppControler.getInstance(this).addProductToCart(cartListings, AppConstants.KEY_CART);

        initalizeCartList();
        adapter.customNotify(cartListings);
        setTotalPricing();
    }

    private void fetchCouponListing(String consumer_key, String consumer_secret) {
        APIInterface apiService = APIClient.getRetrofitInstance().create(APIInterface.class);
        Call<CouponsResponse> callGenerateToken = apiService.getCouponListing(
                consumer_key, consumer_secret, "coupons");

        callGenerateToken.enqueue(new Callback<CouponsResponse>() {
            @Override
            public void onResponse(Call<CouponsResponse> call, Response<CouponsResponse> response) {
                if (response.isSuccessful() && response.body().getData().size() > 0) {
                    couponsList.addAll(response.body().getData());
                } else
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<CouponsResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, t.getMessage() + "", Toast.LENGTH_SHORT).show();
            }
        });

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
                    fetchCouponListing(consumer_key, consumer_secret);
                } else
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<GenerateTokenResponse> call, Throwable t) {
                Toast.makeText(context, t.getMessage() + "", Toast.LENGTH_SHORT).show();
            }
        });


    }

}
