package com.app.fastprint.ui.main;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.app.fastprint.R;
import com.app.fastprint.baseClass.BaseClass;
import com.app.fastprint.networks.NetworkUtils;
import com.app.fastprint.ui.cart.CartActivity;
import com.app.fastprint.ui.category.aboutus.AboutusActivity;
import com.app.fastprint.ui.category.contactus.ContactUsActivity;
import com.app.fastprint.ui.category.enquiryforms.EnquiryFormsActivity;
import com.app.fastprint.ui.category.extras.ExtrasActivity;
import com.app.fastprint.ui.category.gallery.GalleryActivity;
import com.app.fastprint.ui.category.logo.DefinationofLogoActivity;
import com.app.fastprint.ui.category.services.ServicesActivity;
import com.app.fastprint.ui.login.LoginActivity;
import com.app.fastprint.ui.main.adapter.CategoriesAdapter;
import com.app.fastprint.ui.main.interfaces.ICategories;
import com.app.fastprint.ui.main.interfaces.IPCategories;
import com.app.fastprint.ui.main.presenters.PCategories;
import com.app.fastprint.ui.main.responseModel.MenuListResponseModel;
import com.app.fastprint.ui.product.ProductListingActivity;
import com.app.fastprint.ui.main.adapter.ImageSlidingAdapter;
import com.app.fastprint.ui.settings.SettingActivity;
import com.app.fastprint.ui.socialmedia.SocialMedialActivity;
import com.app.fastprint.utills.AppConstants;
import com.app.fastprint.utills.AppControler;
import com.app.fastprint.utills.UtilsAlertDialog;
import com.app.fastprint.utills.UtilsFontFamily;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.testing.FakeReviewManager;
import com.google.android.play.core.tasks.Task;
import com.varunest.sparkbutton.SparkButton;
import com.varunest.sparkbutton.SparkEventListener;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseClass implements ICategories {

    boolean doubleBackToExitPressedOnce = false;

    @BindView(R.id.searchView)
    SearchView searchView;

    @BindView(R.id.tvHome)
    TextView tvHome;

    @BindView(R.id.tvServices)
    TextView tvServices;

    @BindView(R.id.tvDefinitionlogo)
    TextView tvDefinitionlogo;

    @BindView(R.id.tvContactus)
    TextView tvContactus;

    @BindView(R.id.tvEnquiry)
    TextView tvEnquiry;

    @BindView(R.id.tvStore)
    TextView tvStore;

    @BindView(R.id.tvSocialMedia)
    TextView tvSocialMedia;

    @BindView(R.id.tvSettings)
    TextView tvSettings;

    @BindView(R.id.tvAboutUs)
    TextView tvAboutUs;

    @BindView(R.id.tvExtras)
    TextView tvExtras;

    @BindView(R.id.tvCategoreis)
    TextView tvCategoreis;

    @BindView(R.id.imgCart)
    SparkButton imgCart;

    @BindView(R.id.imgMenu)
    ImageView imgMenu;

    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    @BindView(R.id.layoutProfile)
    RelativeLayout layoutProfile;

    @BindView(R.id.layoutGuestUser)
    RelativeLayout layoutGuestUser;

    @BindView(R.id.ImgUser)
    CircleImageView ImgUser;

    @BindView(R.id.tvUserName)
    TextView tvUserName;

    @BindView(R.id.tvUserEmail)
    TextView tvUserEmail;

    @BindView(R.id.tvLogin)
    TextView tvLogin;

    @BindView(R.id.tvGuestUser)
    TextView tvGuestUser;

    @BindView(R.id.ImgGuestUser)
    CircleImageView ImgGuestUser;

    Context context;

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;


    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000; // time in milliseconds between successive task executions.


    @BindView(R.id.indicator)
    CirclePageIndicator indicator;

    CategoriesAdapter categoriesAdapter;
    IPCategories ipCategories;

    @BindView(R.id.RecyclerCategories)
    RecyclerView RecyclerCategories;
    @BindView(R.id.textViewCountCart)
    TextView textViewCountCart;
    Dialog dialog;

    ArrayList<MenuListResponseModel.Data.Banner> imageModelArrayList;
    String auth_token = "";
    String user_image = "";
    String user_email = "";
    String user_first_name = "";
    String user_last_name = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        ButterKnife.bind(this);
        ipCategories = new PCategories(this);
        auth_token = AppControler.getInstance(context).getString(AppControler.Key.AUTH_TOKEN);
        user_first_name = AppControler.getInstance(context).getString(AppControler.Key.USER_FIRST_NAME);
        user_last_name = AppControler.getInstance(context).getString(AppControler.Key.USER_LAST_NAME);
        user_image = AppControler.getInstance(context).getString(AppControler.Key.USER_IMAGE);
        user_email = AppControler.getInstance(context).getString(AppControler.Key.USER_EMAIL);
        textViewCountCart.setText(AppControler.getInstance(context).getCartList(AppConstants.KEY_CART).size() + "");

        if (auth_token != null && !auth_token.isEmpty()) {
            layoutProfile.setVisibility(View.VISIBLE);
            layoutGuestUser.setVisibility(View.INVISIBLE);
            if (user_image != null) {
                Glide.with(context).load(user_image)
                        .placeholder(R.drawable.ic_guest_user)
                        .into(ImgUser);
            } else {
                Glide.with(context).load(user_image)
                        .placeholder(R.drawable.ic_guest_user)
                        .into(ImgUser);
            }
            tvUserName.setText(user_first_name + " " + user_last_name);
            tvUserEmail.setText(user_email);
        } else {
            layoutGuestUser.setVisibility(View.VISIBLE);
            layoutProfile.setVisibility(View.INVISIBLE);
        }
        if (NetworkUtils.isNetworkConnectionAvailable(context)) {
            dialog = UtilsAlertDialog.ShowDialog(context);
            ipCategories.getMenuList();
        }

        imgCart.setEventListener(new SparkEventListener() {
            @Override
            public void onEvent(ImageView button, boolean buttonState) {
                new Handler().postDelayed(() -> {
                    if (NetworkUtils.isNetworkConnectionAvailable(context)) {
                        Intent intent = new Intent(MainActivity.this, CartActivity.class);
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
        viewInitialization();


    }

    private void viewInitialization() {

        tvHome.setTypeface(UtilsFontFamily.typeFaceForRobotoRegular(context));
        tvServices.setTypeface(UtilsFontFamily.typeFaceForRobotoRegular(context));
        tvDefinitionlogo.setTypeface(UtilsFontFamily.typeFaceForRobotoRegular(context));
        tvContactus.setTypeface(UtilsFontFamily.typeFaceForRobotoRegular(context));
        tvEnquiry.setTypeface(UtilsFontFamily.typeFaceForRobotoRegular(context));
        tvStore.setTypeface(UtilsFontFamily.typeFaceForRobotoRegular(context));
        tvSocialMedia.setTypeface(UtilsFontFamily.typeFaceForRobotoRegular(context));
        tvSettings.setTypeface(UtilsFontFamily.typeFaceForRobotoRegular(context));
        tvAboutUs.setTypeface(UtilsFontFamily.typeFaceForRobotoRegular(context));
        tvAboutUs.setTypeface(UtilsFontFamily.typeFaceForRobotoRegular(context));
        tvGuestUser.setTypeface(UtilsFontFamily.typeFaceForRobotoRegular(context));
        tvCategoreis.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        tvLogin.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));

        searchView.setQueryHint("Search here");
       /* searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvCategoreis.setVisibility(View.GONE);
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                tvCategoreis.setVisibility(View.VISIBLE);
                return false;
            }
        });*/
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText != null&&categoriesAdapter!=null) {
                    categoriesAdapter.getFilter().filter(newText);
                }
                return true;
            }
        });
    }


    @Override
    public void onBackPressed() {
        drawerLayout.closeDrawer(Gravity.LEFT);

        //Checking for fragment count on backstack
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else if (!doubleBackToExitPressedOnce) {
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, R.string.back_agin, Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        } else {
            super.onBackPressed();
            return;
        }
    }

    private void openCloseDrawer() {

        if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
            drawerLayout.closeDrawer(Gravity.LEFT);

        } else {
            drawerLayout.openDrawer(Gravity.LEFT);
        }
    }

    @OnClick({R.id.imgMenu, R.id.tvAboutUs, R.id.tvHome, R.id.tvServices, R.id.tvDefinitionlogo, R.id.tvContactus,
            R.id.tvEnquiry, R.id.tvStore, R.id.tvSocialMedia, R.id.tvSettings, R.id.tvGallery, R.id.tvExtras, R.id.tvLogin})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
//            case R.id.imgCart:
//                if (NetworkUtils.isNetworkConnectionAvailable(context)) {
//                   // intent = new Intent(MainActivity.this, CartActivity.class);
//                    //startActivity(intent);
//                }
//                break;
            case R.id.imgMenu:
                openCloseDrawer();
                break;
            case R.id.tvAboutUs:
                intent = new Intent(MainActivity.this, AboutusActivity.class);
                startActivity(intent);
                break;
            case R.id.tvHome:
                intent = new Intent(MainActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.tvServices:
                intent = new Intent(MainActivity.this, ServicesActivity.class);
                startActivity(intent);
                break;
            case R.id.tvDefinitionlogo:
                intent = new Intent(MainActivity.this, DefinationofLogoActivity.class);
                startActivity(intent);
                break;
            case R.id.tvContactus:
                intent = new Intent(MainActivity.this, ContactUsActivity.class);
                startActivity(intent);
                break;
            case R.id.tvEnquiry:
                intent = new Intent(MainActivity.this, EnquiryFormsActivity.class);
                startActivity(intent);
                break;
            case R.id.tvStore:
                intent = new Intent(MainActivity.this, ProductListingActivity.class);
                context.startActivity(intent);
                break;
            case R.id.tvSocialMedia:
                intent = new Intent(MainActivity.this, SocialMedialActivity.class);
                startActivity(intent);
                break;
            case R.id.tvSettings:
                intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.tvGallery:
                intent = new Intent(MainActivity.this, GalleryActivity.class);
                startActivity(intent);
                break;
            case R.id.tvExtras:
                intent = new Intent(MainActivity.this, ExtrasActivity.class);
                startActivity(intent);
                break;
            case R.id.tvLogin:
                intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.putExtra("intent_type", "from_main");
                startActivity(intent);
                break;
        }
    }

    @Override
    public void successResponseFromPresenter(MenuListResponseModel menuListResponseModel) {
        dialog.dismiss();
        if (menuListResponseModel != null && menuListResponseModel.getData().getMenu().size() > 0
                && menuListResponseModel.getData().getBanner().size() > 0) {

            updateUI(menuListResponseModel.getData());

        } else {

        }
    }

    private void updateUI(MenuListResponseModel.Data data) {
        updateBannerUI(data);
        udpateMenuListUI(data);


    }

    private void udpateMenuListUI(MenuListResponseModel.Data data) {
        int columns = 2;
        categoriesAdapter = new CategoriesAdapter(this, data.getMenu());
        RecyclerCategories.setLayoutManager(new GridLayoutManager(context, columns));
        RecyclerCategories.setAdapter(categoriesAdapter);
    }

    @Override
    public void errorResponseFromPresenter(String message) {
        dialog.dismiss();
        String error_message = message;
        Toast.makeText(context, "" + error_message, Toast.LENGTH_SHORT).show();

    }

    private void updateBannerUI(MenuListResponseModel.Data data) {
        imageModelArrayList = new ArrayList<>();
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new ImageSlidingAdapter(MainActivity.this, data.getBanner()));
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

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        textViewCountCart.setText(AppControler.getInstance(context).getCartList(AppConstants.KEY_CART).size()+"");
    }


}
