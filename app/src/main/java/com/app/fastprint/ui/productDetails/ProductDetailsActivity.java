package com.app.fastprint.ui.productDetails;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.app.fastprint.R;
import com.app.fastprint.baseClass.BaseClass;
import com.app.fastprint.networks.NetworkUtils;
import com.app.fastprint.ui.cart.CartActivity;
import com.app.fastprint.ui.cart.responseModel.BuyNowListing;
import com.app.fastprint.ui.cart.responseModel.CartListing;
import com.app.fastprint.ui.login.LoginActivity;
import com.app.fastprint.ui.main.MainActivity;
import com.app.fastprint.ui.productDetails.adapter.ImageSlidingsAdapter;
import com.app.fastprint.ui.productDetails.adapter.ProductCoverOptionAdapter;
import com.app.fastprint.ui.productDetails.adapter.ProductPageIncludeAdapter;
import com.app.fastprint.ui.productDetails.adapter.ProductQuantityAdapter;
import com.app.fastprint.ui.productDetails.adapter.SimilarProductsAdapter;
import com.app.fastprint.ui.productDetails.interfaces.IPProductDetails;
import com.app.fastprint.ui.productDetails.interfaces.IProductDetails;
import com.app.fastprint.ui.productDetails.presenter.PProductDetails;
import com.app.fastprint.ui.productDetails.responseModel.ProductDetailsResponseModel;
import com.app.fastprint.ui.productDetails.responseModel.variationResponse.VariationResponseModel;
import com.app.fastprint.ui.reviewListing.ReviewListingActivity;
import com.app.fastprint.ui.uploadFileForm.FormFileUploadActivity;
import com.app.fastprint.utills.AppConstants;
import com.app.fastprint.utills.AppControler;
import com.app.fastprint.utills.UtilsAlertDialog;
import com.app.fastprint.utills.UtilsFontFamily;
import com.varunest.sparkbutton.SparkButton;
import com.varunest.sparkbutton.SparkEventListener;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductDetailsActivity extends BaseClass implements IProductDetails {
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    @BindView(R.id.imgCart)
    SparkButton imgCart;
    @BindView(R.id.tvTittle)
    TextView tvTittle;
    @BindView(R.id.textViewCountCart)
    TextView textViewCountCart;
    @BindView(R.id.indicator)
    CirclePageIndicator indicator;
    @BindView(R.id.tvProductName)
    TextView tvProductName;
    @BindView(R.id.tvDiscountPrice)
    TextView tvDiscountPrice;
    @BindView(R.id.tvPrice)
    TextView tvPrice;
    @BindView(R.id.tvPriceRange)
    TextView tvPriceRange;
    @BindView(R.id.tvtotalRating)
    TextView tvtotalRating;
    @BindView(R.id.tvTotalReview)
    TextView tvTotalReview;
    @BindView(R.id.tvDescriptions)
    TextView tvDescriptions;
    @BindView(R.id.quantity)
    TextView quantity;

    @BindView(R.id.coveroptions)
    TextView coveroptions;

    @BindView(R.id.pageincluding)
    TextView pageincluding;

    public static TextView tvPageincluding;
    public static TextView tvCoveroptions;
    public static TextView tvQuantity;

    @BindView(R.id.remove_item_from_cart)
    ImageView removeItemFromCart;

    @BindView(R.id.tvAddItem)
    TextView tvAddItem;

    @BindView(R.id.tvBuyNow)
    TextView tvBuyNow;
    @BindView(R.id.rating)
    RatingBar rating;
    @BindView(R.id.tvAddToCart)
    TextView tvAddToCart;
    @BindView(R.id.add_item_from_cart)
    ImageView addItemFromCart;
    @BindView(R.id.layoutAddtoCart)
    LinearLayout layoutAddtoCart;
    @BindView(R.id.layoutPageincluding)
    RelativeLayout layoutPageincluding;
    @BindView(R.id.layoutQuantity)
    RelativeLayout layoutQuantity;
    @BindView(R.id.layoutBuy)
    RelativeLayout layoutBuy;
    @BindView(R.id.layoutAll)
    RelativeLayout layoutAll;
    @BindView(R.id.layoutCoveroptions)
    RelativeLayout layoutCoveroptions;
    @BindView(R.id.layoutBuyNow)
    LinearLayout layoutBuyNow;
    @BindView(R.id.tvSimilarProducts)
    TextView tvSimilarProducts;
    @BindView(R.id.recyclerSimilarsProduct)
    RecyclerView recyclerSimilarsProduct;
    //private ArrayList<ProductDetailsResponseModel.Data> imageModelArrayList;
    List<String> imageModelArrayList;
    List<String> productQuantityList;
    List<String> productPageIncludeList;
    List<String> coverOptionList;
    SimilarProductsAdapter similarProductsAdapter;
    private int[] myImageList = new int[]{R.drawable.img_1, R.drawable.img_2,
            R.drawable.img_3};
    Context context;
    String product_id = "";
    String currencySymbol = "";
    int variationId = 0;
    IPProductDetails ipProductDetails;
    Dialog dialog;
    public static Dialog productQuantityDialog;
    public static Dialog pageIncludeDialog;
    public static Dialog coverOptionDialog;

    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000; // time in milliseconds between successive task executions.

    int i = 0;
    String _stringVal;


    String mquantity = "";
    String mcover_option = "";
    String mpage_including = "";
    String auth_token = "";
    String product_image_url = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        ButterKnife.bind(this);
        context = ProductDetailsActivity.this;
        auth_token = AppControler.getInstance(context).getString(AppControler.Key.AUTH_TOKEN);
        ipProductDetails = new PProductDetails(this);
        product_id = getIntent().getStringExtra("product_id");
        if (NetworkUtils.isNetworkConnectionAvailable(context)) {
            dialog = UtilsAlertDialog.ShowDialog(context);
            if (product_id == null && product_id.equals("")) {
                Toast.makeText(context, "Invalid product id", Toast.LENGTH_SHORT).show();
            } else {
                ipProductDetails.getProductDetails(product_id);
            }

        }
        viewInitialization();
    }

    private void viewInitialization() {
        tvPageincluding = (TextView) findViewById(R.id.tvPageincluding);
        tvCoveroptions = (TextView) findViewById(R.id.tvCoveroptions);
        tvQuantity = (TextView) findViewById(R.id.tvQuantity);

        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        tvSimilarProducts.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        tvProductName.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        tvDiscountPrice.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        tvPrice.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        quantity.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        coveroptions.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        pageincluding.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        tvAddToCart.setTypeface(UtilsFontFamily.typeFaceForRobotoBold(context));
        tvBuyNow.setTypeface(UtilsFontFamily.typeFaceForRobotoBold(context));

        imgCart.setEventListener(new SparkEventListener() {
            @Override
            public void onEvent(ImageView button, boolean buttonState) {
                new Handler().postDelayed(() -> {
                    if (NetworkUtils.isNetworkConnectionAvailable(context)) {
                        Intent intent = new Intent(ProductDetailsActivity.this, CartActivity.class);
                        startActivity(intent);
                        imgCart.setChecked(false);
                    }
                }, 300);


            }

            @Override
            public void onEventAnimationEnd(ImageView button, boolean buttonState) {

            }

            @Override
            public void onEventAnimationStart(ImageView button, boolean buttonState) {

            }
        });

        layoutBuy.setVisibility(View.GONE);
        layoutAll.setVisibility(View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        textViewCountCart.setText(AppControler.getInstance(context).getCartList(AppConstants.KEY_CART).size() + "");
    }

    @OnClick({R.id.imgBack, R.id.remove_item_from_cart, R.id.add_item_from_cart, R.id.layoutAddtoCart, R.id.layoutBuyNow, R.id.tvTotalReview,
            R.id.tvPageincluding, R.id.tvCoveroptions, R.id.tvQuantity,})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                if (isTaskRoot()) {
                    startActivity(new Intent(ProductDetailsActivity.this, MainActivity.class));
                    finish();
                } else
                    finish();
                break;
            case R.id.remove_item_from_cart:
                Log.d("src", "Decreasing value...");
                if (i > 0) {
                    i = i - 1;
                    _stringVal = String.valueOf(i);
                    tvAddItem.setText(_stringVal);
                } else {
                    Log.d("src", "Value can't be less than 0");
                    Toast.makeText(this, "Value can't be less than 0", Toast.LENGTH_SHORT).show();
                    Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibe.vibrate(50);
                }
                break;
            case R.id.add_item_from_cart:
                Log.d("src", "Increasing value...");
                i = i + 1;
                _stringVal = String.valueOf(i);
                tvAddItem.setText(_stringVal);

                break;
            case R.id.layoutAddtoCart:
                if (mquantity.isEmpty()) {
                    Toast.makeText(context, "Product don't have quantity. Please ", Toast.LENGTH_SHORT).show();
                } else {
                    addProductToCart();
                    imgCart.playAnimation();
                    textViewCountCart.setText(AppControler.getInstance(context).getCartList(AppConstants.KEY_CART).size() + "");
                }

                break;
            case R.id.layoutBuyNow:
                if (auth_token != null && !auth_token.isEmpty()) {
                    saveForBuyNow();
                    Intent in2 = new Intent(ProductDetailsActivity.this, FormFileUploadActivity.class);
                    in2.putExtra("intent_From", "productdetails");
                    startActivity(in2);
                } else {
                    Toast.makeText(context, "You must be logged in to add to cart", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ProductDetailsActivity.this, LoginActivity.class);
                    intent.putExtra("intent_type", "from_product_details");
                    intent.putExtra("product_id", product_id);
                    startActivity(intent);
                }


                break;
            case R.id.tvTotalReview:
                Intent in3 = new Intent(ProductDetailsActivity.this, ReviewListingActivity.class);
                in3.putExtra("product_id", product_id);
                startActivity(in3);
                break;
            case R.id.tvPageincluding:
                dialogPageincluding();
                break;
            case R.id.tvCoveroptions:
                dialogCoveroptions();
                break;
            case R.id.tvQuantity:
                dialogQuantity();
                break;
        }
    }

    private void addProductToCart() {
        ArrayList<CartListing> cartListings = AppControler.getInstance().getCartList(AppConstants.KEY_CART);
        if (cartListings == null)
            cartListings = new ArrayList<>();

        CartListing cartListing = new CartListing(product_id,
                tvProductName.getText().toString(),
                tvQuantity.getText().toString()
                , tvCoveroptions.getText().toString(),
                pageincluding.getText().toString(),
                tvPrice.getText().toString().replace(currencySymbol, ""),
                tvDiscountPrice.getText().toString().replace(currencySymbol, ""),
                product_image_url,
                tvtotalRating.getText().toString(),
                rating.getRating(),variationId,currencySymbol);

        cartListings.add(cartListing);
        AppControler.getInstance(this).addProductToCart(cartListings, AppConstants.KEY_CART);


        Toast.makeText(context, "Added to cart.", Toast.LENGTH_SHORT).show();
    }

    private void saveForBuyNow() {
        ArrayList<BuyNowListing> buyNowListings = new ArrayList<>();

        BuyNowListing buyNowListing = new BuyNowListing(product_id,
                tvProductName.getText().toString(),
                tvQuantity.getText().toString()
                , tvCoveroptions.getText().toString(),
                pageincluding.getText().toString(),
                tvPrice.getText().toString().replace(currencySymbol, ""),
                tvDiscountPrice.getText().toString().replace(currencySymbol, ""),
                product_image_url,
                tvtotalRating.getText().toString(),
                rating.getRating(),currencySymbol);

        buyNowListings.add(buyNowListing);
        AppControler.getInstance(this).addProductToBuyNow(buyNowListings, AppConstants.KEY_BUY_NOW);
    }

    private void dialogPageincluding() {

        pageIncludeDialog = new Dialog(ProductDetailsActivity.this);
        pageIncludeDialog.setContentView(R.layout.dialog_page_include);
        pageIncludeDialog.setCancelable(true);
        RecyclerView recyclerPageInclude = pageIncludeDialog.findViewById(R.id.recyclerPageInclude);
        ProductPageIncludeAdapter slidesAdapter = new ProductPageIncludeAdapter(context, productPageIncludeList, ProductDetailsActivity.this);
        recyclerPageInclude.setLayoutManager(new LinearLayoutManager(this));
        recyclerPageInclude.setAdapter(slidesAdapter);
        DividerItemDecoration divider = new DividerItemDecoration(recyclerPageInclude.getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.custom_divider));
        recyclerPageInclude.addItemDecoration(divider);
        pageIncludeDialog.show();
    }

    private void dialogCoveroptions() {
        coverOptionDialog = new Dialog(ProductDetailsActivity.this);
        coverOptionDialog.setContentView(R.layout.dialog_cover_options);
        coverOptionDialog.setCancelable(true);
        RecyclerView recyclerCoverPage = coverOptionDialog.findViewById(R.id.recyclerCoverPage);
        ProductCoverOptionAdapter slidesAdapter = new ProductCoverOptionAdapter(context, coverOptionList, ProductDetailsActivity.this);
        recyclerCoverPage.setLayoutManager(new LinearLayoutManager(this));
        recyclerCoverPage.setAdapter(slidesAdapter);
        DividerItemDecoration divider = new DividerItemDecoration(recyclerCoverPage.getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.custom_divider));
        recyclerCoverPage.addItemDecoration(divider);
        coverOptionDialog.show();
    }

    private void dialogQuantity() {
        productQuantityDialog = new Dialog(ProductDetailsActivity.this);
        productQuantityDialog.setContentView(R.layout.dialog_quantity);
        productQuantityDialog.setCancelable(true);
        RecyclerView recyclerQuantity = productQuantityDialog.findViewById(R.id.recyclerQuantity);
        ProductQuantityAdapter slidesAdapter = new ProductQuantityAdapter(context, tvQuantity, productQuantityList, ProductDetailsActivity.this);
        recyclerQuantity.setLayoutManager(new LinearLayoutManager(this));
        recyclerQuantity.setAdapter(slidesAdapter);
        DividerItemDecoration divider = new DividerItemDecoration(recyclerQuantity.getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.custom_divider));
        recyclerQuantity.addItemDecoration(divider);
        productQuantityDialog.show();
    }

    @Override
    public void successResponseFromPresenter(ProductDetailsResponseModel productDetailsResponseModel) {
        dialog.dismiss();
        if (productDetailsResponseModel.getData() != null) {
            layoutBuy.setVisibility(View.VISIBLE);
            layoutAll.setVisibility(View.VISIBLE);

            if (productDetailsResponseModel.getData().getProductImages().size() > 0)
                product_image_url = productDetailsResponseModel.getData().getProductImages().get(0);

            imageModelArrayList = productDetailsResponseModel.getData().getProductImages();
            currencySymbol = Html.fromHtml(productDetailsResponseModel.getData().getProductDetail().get(0).getCurrencySymbol()).toString();

            if (productDetailsResponseModel.getData().getQuantity() != null && productDetailsResponseModel.getData().getQuantity().size() > 0) {
                layoutQuantity.setVisibility(View.VISIBLE);
                productQuantityList = productDetailsResponseModel.getData().getQuantity();
                Collections.sort(productQuantityList, new NumericalStringComparator());
                tvQuantity.setText(productDetailsResponseModel.getData().getQuantity().get(0));
                mquantity = tvQuantity.getText().toString();

            } else {
                layoutQuantity.setVisibility(View.GONE);

            }


            if (productDetailsResponseModel.getData().getPages() != null && productDetailsResponseModel.getData().getPages().size() > 0) {
                layoutPageincluding.setVisibility(View.VISIBLE);
                productPageIncludeList = productDetailsResponseModel.getData().getPages();
                Collections.sort(productPageIncludeList, new NumericalStringComparator());
                tvPageincluding.setText(productDetailsResponseModel.getData().getPages().get(0));
                mpage_including = tvPageincluding.getText().toString();

            } else {
                layoutPageincluding.setVisibility(View.GONE);

            }
            if (productDetailsResponseModel.getData().getCover() != null && productDetailsResponseModel.getData().getCover().size() > 0) {
                layoutCoveroptions.setVisibility(View.VISIBLE);
                coverOptionList = productDetailsResponseModel.getData().getCover();
                tvCoveroptions.setText(productDetailsResponseModel.getData().getCover().get(0));
                mcover_option = tvCoveroptions.getText().toString();
            } else {
                layoutCoveroptions.setVisibility(View.GONE);
            }
            getVariation(tvQuantity.getText().toString(),
                    tvCoveroptions.getText().toString(), tvPageincluding.getText().toString());

            updateProductBanner(productDetailsResponseModel.getData());
            updateProductDetails(productDetailsResponseModel.getData());
            updateSimilarProduct(productDetailsResponseModel.getData());
        } else {
            layoutBuy.setVisibility(View.GONE);
            layoutAll.setVisibility(View.GONE);
        }
    }

    public class NumericalStringComparator implements Comparator<String> {
        @Override
        public int compare (String s1, String s2) {
            if (s1.endsWith("-pages")) {
                s1 = s1.replaceAll("-pages", "");
                s2 = s2.replaceAll("-pages", "");
            }
            int i1 = Integer.parseInt(s1.split(" ")[0]);
            int i2 = Integer.parseInt(s2.split(" ")[0]);
            int cmp = Integer.compare(i1, i2);
            if (cmp != 0) {
                return cmp;
            }
            return s1.compareTo(s2);
        }
    }

    @Override
    public void errorResponseFromPresenter(String message) {
        layoutBuy.setVisibility(View.GONE);
        layoutAll.setVisibility(View.GONE);
        dialog.dismiss();
        Toast.makeText(context, "" + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void successPriceResponseFromPresenter(VariationResponseModel priceResponseModel) {
        dialog.dismiss();
        currencySymbol = Html.fromHtml(priceResponseModel.getData().getCurrencySymbol()).toString();
        String getProductPrice = priceResponseModel.getData().getVariationPrice();
        variationId = priceResponseModel.getData().getVariationId();
        tvPrice.setText(Html.fromHtml(priceResponseModel.getData().getCurrencySymbol()+ getProductPrice));
    }

    @Override
    public void errorPriceResponseFromPresenter(String message) {
        dialog.dismiss();
        Toast.makeText(context, "" + message, Toast.LENGTH_SHORT).show();
    }

    private void updateSimilarProduct(ProductDetailsResponseModel.Data data) {
        similarProductsAdapter = new SimilarProductsAdapter(context, data.getSimilar());
        recyclerSimilarsProduct.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerSimilarsProduct.setAdapter(similarProductsAdapter);
    }

    private void updateProductDetails(ProductDetailsResponseModel.Data data) {
        tvProductName.setText(data.getProductDetail().get(0).getProductName().toUpperCase());
        tvDiscountPrice.setPaintFlags(tvDiscountPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        tvDiscountPrice.setText(Html.fromHtml(data.getProductDetail().get(0).getCurrencySymbol())
                +data.getProductDetail().get(0).getProductRegularPrice());
        tvPrice.setText(Html.fromHtml(data.getProductDetail().get(0).getCurrencySymbol())+
                data.getProductDetail().get(0).getProductPrice());
        tvPriceRange.setText(Html.fromHtml(data.getProductDetail().get(0).getCurrencySymbol())+
                data.getProductDetail().get(0).getMin_price()
                + " - " + Html.fromHtml(data.getProductDetail().get(0).getCurrencySymbol())+
                data.getProductDetail().get(0).getMax_price());

        tvDescriptions.setText((Html.fromHtml(data.getProductDetail().get(0).getDescription())));
        String total_reviews = String.valueOf(data.getProductDetail().get(0).getReviewCount());
        tvTotalReview.setText(total_reviews + " " + "Customer Reviews");
        tvtotalRating.setText("(" + data.getProductDetail().get(0).getAverage() + ")");
        float ratingCount = data.getProductDetail().get(0).getRatingCount();
        rating.setRating(ratingCount);
    }


    private void updateProductBanner(ProductDetailsResponseModel.Data data) {
        imageModelArrayList = new ArrayList<>();
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new ImageSlidingsAdapter(ProductDetailsActivity.this, data.getProductImages()));
        CirclePageIndicator indicator = (CirclePageIndicator)
                findViewById(R.id.indicator);
        indicator.setViewPager(mPager);
        final float density = getResources().getDisplayMetrics().density;
        //Set circle indicator radius
        indicator.setRadius(5 * density);
        NUM_PAGES = imageModelArrayList.size();
        /*After setting the adapter use the timer */
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);
        // timer.scheduleAtFixedRate(new SliderTimer(), 4000, 6000);
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int pos) {
                Log.d("state++", "state" + pos);
            }
        });
    }

    private class SliderTimer extends TimerTask {

        @Override
        public void run() {
            ProductDetailsActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mPager.getCurrentItem() < imageModelArrayList.size() - 1) {
                        mPager.setCurrentItem(mPager.getCurrentItem() + 1);

                    } else {
                        mPager.setCurrentItem(0);
                    }
                }
            });
        }
    }

    public void getVariation(String mquantity, String mcover_option, String mpage_including) {
        if (NetworkUtils.isNetworkConnectionAvailable(context)) {
            dialog = UtilsAlertDialog.ShowDialog(context);
            ipProductDetails.getPrice(product_id, mquantity,mpage_including, mcover_option );
        }
    }
}
