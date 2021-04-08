package com.app.fastprint.ui.login;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Base64;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.app.fastprint.R;
import com.app.fastprint.baseClass.BaseClass;
import com.app.fastprint.networks.NetworkUtils;
import com.app.fastprint.ui.cart.CartActivity;
import com.app.fastprint.ui.login.interfaces.ILogin;
import com.app.fastprint.ui.login.interfaces.IPLogin;
import com.app.fastprint.ui.login.loginResponse.LoginResponseModel;
import com.app.fastprint.ui.login.loginResponse.SocialLoginResponseModel;
import com.app.fastprint.ui.login.presenter.PLogin;
import com.app.fastprint.ui.login.runtimePermission.Runtimepermission;
import com.app.fastprint.ui.main.MainActivity;
import com.app.fastprint.ui.others.OthersActivity;
import com.app.fastprint.ui.productDetails.ProductDetailsActivity;
import com.app.fastprint.ui.resetPassword.ResetPasswordActivity;
import com.app.fastprint.ui.signup.SignUpActivity;
import com.app.fastprint.utills.AppConstants;
import com.app.fastprint.utills.AppControler;
import com.app.fastprint.utills.GpsUtils;
import com.app.fastprint.utills.UtilsAlertDialog;
import com.app.fastprint.utills.UtilsFontFamily;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseClass implements ILogin {

    @BindView(R.id.editMail)
    EditText editMail;
    @BindView(R.id.editPassword)
    EditText editPassword;
    @BindView(R.id.tvSignIn)
    TextView tvSignIn;
    @BindView(R.id.tvForgotPassword)
    TextView tvForgotPassword;
    @BindView(R.id.tvTermsCondition)
    TextView tvTermsCondition;
    @BindView(R.id.tvLoginWithFaceBook)
    TextView tvLoginWithFaceBook;
    @BindView(R.id.tvLoginWithGoogle)
    TextView tvLoginWithGoogle;

    @BindView(R.id.layoutgoogle)
    RelativeLayout layoutgoogle;
    @BindView(R.id.layoutFb)
    RelativeLayout layoutFb;

    @BindView(R.id.sign_in_button)
    SignInButton sign_in_button;

    @BindView(R.id.login_button)
    LoginButton login_button;

    @BindView(R.id.tvSignUp)
    TextView tvSignUp;
    Context context;

    private FusedLocationProviderClient mFusedLocationClient;
    private double wayLatitude = 0.0, wayLongitude = 0.0;
    String currentLatitude = "";
    String currentLongitude = "";
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private boolean isGPS = false;
    private SparseIntArray mErrorString;
    String device_token = "";
    IPLogin ipLogin;
    Dialog dialog;
    String login_type = "";
    String intent_type = "";
    GoogleSignInClient mGoogleSignInClient;
    public static int RC_SIGN_IN = 121;
    CallbackManager callbackManager;
    String social_username = "", socail_email = "", socail_id = "", socail_image = "", socail_firstname = "", social_lastname = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        context = LoginActivity.this;
        new Runtimepermission(this);
        callbackManager = CallbackManager.Factory.create();


        // Build a GoogleSignInClient with the options specified by gso.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        //  printKeyHash(this);
        intent_type = getIntent().getStringExtra("intent_type");
        device_token = AppControler.getInstance(this).getString(AppControler.Key.DEVICE_TOKEN);
        ipLogin = new PLogin(this);
        tvLoginWithFaceBook.setTypeface(UtilsFontFamily.typeFaceForRobotoBold(context));
        tvLoginWithGoogle.setTypeface(UtilsFontFamily.typeFaceForRobotoBold(context));
        tvForgotPassword.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        tvTermsCondition.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        tvSignUp.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        tvSignIn.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        getCurrentLocation();
        if (!isGPS) {
            Toast.makeText(this, "Please turn on GPS", Toast.LENGTH_SHORT).show();
            return;
        }
        getLocations();
        faceBookLogin();
        generateToken();
    }

    private void faceBookLogin() {
        login_button.setReadPermissions(Arrays.asList("email", "public_profile"));
        login_button.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                dialog = UtilsAlertDialog.ShowDialog(context);
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken()
                        , new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {


                                try {
                                    socail_email = object.getString("email");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                try {
                                    socail_id = object.getString("id");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                try {
                                    socail_image = "http://graph.facebook.com/" + socail_id + "/picture?type=large";
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                try {
                                    social_username = object.getString("name");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                try {
                                    socail_firstname = object.getString("first_name");

                                } catch (JSONException e) {
                                    e.printStackTrace();

                                }
                                try {
                                    social_lastname = object.getString("last_name");


                                } catch (JSONException e) {
                                    e.printStackTrace();

                                }

                                Log.d("data++", "data++" + socail_email);
                                Log.d("data++", "data++" + socail_id);
                                Log.d("data++", "data++" + socail_image);
                                Log.d("data++", "data++" + social_username);
                                Log.d("data++", "data++" + socail_firstname);
                                Log.d("data++", "data++" + social_lastname);

                                ipLogin.socialLogin(socail_email, socail_firstname, social_lastname,
                                        socail_image, login_type,
                                        "A",
                                        currentLatitude, currentLongitude, device_token);

                            }
                        });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,first_name,last_name,email");
                request.setParameters(parameters);
                request.executeAsync();
                dialog.dismiss();
            }

            @Override
            public void onCancel() {
                if (dialog!=null)
                dialog.dismiss();
                Toast.makeText(LoginActivity.this, "Facebook Login Cancel", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(LoginActivity.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getLocations() {
        if (ActivityCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(LoginActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    AppConstants.LOCATION_REQUEST);

        } else {
            mFusedLocationClient.getLastLocation().addOnSuccessListener(LoginActivity.this, location -> {
                if (location != null) {
                    wayLatitude = location.getLatitude();
                    wayLongitude = location.getLongitude();
                    currentLatitude = String.valueOf(wayLongitude);
                    currentLongitude = String.valueOf(wayLongitude);
                    Log.d("mydata++", "first" + wayLatitude + "" + wayLongitude);
                    //    txtLocation.setText(String.format(Locale.US, "%s - %s", wayLatitude, wayLongitude));
                }
            });
        }
    }

    private void getCurrentLocation() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10 * 1000); // 10 seconds
        locationRequest.setFastestInterval(5 * 1000); // 5 seconds
        new GpsUtils(this).turnGPSOn(new GpsUtils.onGpsListener() {
            @Override
            public void gpsStatus(boolean isGPSEnable) {
                // turn on GPS
                isGPS = isGPSEnable;
            }
        });
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    if (location != null) {
                        wayLatitude = location.getLatitude();
                        wayLongitude = location.getLongitude();
                        currentLatitude = String.valueOf(wayLongitude);
                        currentLongitude = String.valueOf(wayLongitude);
                        Log.d("mydata++", "second" + wayLatitude + "" + wayLongitude);
                        // txtLocation.setText(String.format(Locale.US, "%s - %s", wayLatitude, wayLongitude));
                        if (mFusedLocationClient != null) {
                            mFusedLocationClient.removeLocationUpdates(locationCallback);
                        }
                    }
                }
            }
        };
    }

    // check requested permissions are on or off
    public void requestAppPermissions(final String[] requestedPermissions, final int stringId, final int requestCode) {
        mErrorString.put(requestCode, stringId);
        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        boolean shouldShowRequestPermissionRationale = false;
        for (String permission : requestedPermissions) {
            permissionCheck = permissionCheck + ContextCompat.checkSelfPermission(this, permission);
            shouldShowRequestPermissionRationale = shouldShowRequestPermissionRationale || ActivityCompat.shouldShowRequestPermissionRationale(this, permission);
        }
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale) {
                Snackbar snack = Snackbar.make(findViewById(android.R.id.content), stringId, Snackbar.LENGTH_INDEFINITE);
                View view = snack.getView();
                TextView tv = (TextView) view.findViewById(com.google.android.material.R.id.snackbar_text);
                tv.setTextColor(Color.WHITE);
                snack.setAction("GRANT", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityCompat.requestPermissions(LoginActivity.this, requestedPermissions, requestCode);
                    }
                }).show();
            } else {
                ActivityCompat.requestPermissions(this, requestedPermissions, requestCode);
            }
        } else {
            onPermissionsGranted(requestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int permission : grantResults) {
            permissionCheck = permissionCheck + permission;
        }
        if ((grantResults.length > 0) && permissionCheck == PackageManager.PERMISSION_GRANTED) {
            onPermissionsGranted(requestCode);
        } else {
        }
    }

    // if permissions granted succesfully
    private void onPermissionsGranted(int requestcode) {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
    }

    @OnClick({R.id.tvSignIn, R.id.layoutgoogle, R.id.layoutFb, R.id.tvForgotPassword, R.id.tvSignUp, R.id.tvTermsCondition})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.tvSignIn:
                login_type = "simple_login";
                validationOnLogin();
                break;
            case R.id.layoutgoogle:
                login_type = "google_login";
                if (NetworkUtils.isNetworkConnectionAvailable(context)) {
                    Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                    startActivityForResult(signInIntent, RC_SIGN_IN);
                }
                break;
            case R.id.layoutFb:
                login_type = "facebook_login";
                if (NetworkUtils.isNetworkConnectionAvailable(context)) {
                    login_button.performClick();
                }
                break;
            case R.id.tvForgotPassword:
                intent = new Intent(LoginActivity.this, ResetPasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.tvSignUp:
                intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                break;
            case R.id.tvTermsCondition:
                intent = new Intent(LoginActivity.this, OthersActivity.class);
                intent.putExtra("intentType", "terms_and_conditions");
                startActivity(intent);
                break;
        }
    }

    private void validationOnLogin() {
        if (editMail.getText().toString().trim().isEmpty()) {
            editMail.setError("Enter register email");
        } else if (editPassword.getText().toString().trim().isEmpty()) {
            editPassword.setError("Enter password");
        } else {
            if (NetworkUtils.isNetworkConnectionAvailable(context)) {
                dialog = UtilsAlertDialog.ShowDialog(context);
                ipLogin.login(editMail.getText().toString().trim(), editPassword.getText().toString().trim(), device_token,
                        "A", login_type, currentLatitude, currentLongitude);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppConstants.GPS_REQUEST && requestCode == RESULT_OK) {
            isGPS = true; // flag maintain before get location
        }
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            // Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            Log.d("", "" + +e.getStatusCode());
            updateUI(null);
        }
    }

    private void updateUI(GoogleSignInAccount account) {

        if (account != null) {
            String personName = account.getDisplayName();
            String personGivenName = account.getGivenName();
            String personFamilyName = account.getFamilyName();
            String personEmail = account.getEmail();
            String personId = account.getId();
            Uri personPhoto = account.getPhotoUrl();
            String personPhotos = "";
            if (personPhoto != null) {
                personPhotos = account.getPhotoUrl().toString();
            }
            String str = personName;
            String[] splited = str.split("\\s+");

            String split_one = splited[0];
            String split_second = splited[1];

            Log.d("data++", "data++" + personName);
            Log.d("data++", "data++" + personGivenName);
            Log.d("data++", "data++" + personFamilyName);
            Log.d("data++", "data++" + personEmail);
            Log.d("data++", "data++" + personId);
            Log.d("data++", "data++" + personPhoto);

            Log.d("data++", "data++" + split_one);
            Log.d("data++", "data++" + split_second);
            if (NetworkUtils.isNetworkConnectionAvailable(context)) {
                dialog = UtilsAlertDialog.ShowDialog(context);
                ipLogin.socialLogin(personEmail, personGivenName, personFamilyName,
                        personPhotos, login_type, "A", currentLatitude, currentLongitude, device_token);
            }
        }
    }

    @Override
    public void successResponseFromPresenter(@NotNull LoginResponseModel loginResponseModel) {
        dialog.dismiss();
        if (loginResponseModel.getData() != null) {
            if (loginResponseModel.getData().getAuthToken() != null) {
                String auth_token = loginResponseModel.getData().getAuthToken();
                AppControler.getInstance().put(AppControler.Key.AUTH_TOKEN, auth_token);
            }
            if (loginResponseModel.getData().getUserId() != null) {
                String user_id = String.valueOf(loginResponseModel.getData().getUserId());
                AppControler.getInstance().put(AppControler.Key.USER_ID, user_id);
            }
            if (loginResponseModel.getData().getUserName() != null) {
                String user_name = loginResponseModel.getData().getUserName();
                AppControler.getInstance().put(AppControler.Key.USER_NAME, user_name);
            }
            if (loginResponseModel.getData().getFirstName() != null) {
                String user_first_name = loginResponseModel.getData().getFirstName();
                AppControler.getInstance().put(AppControler.Key.USER_FIRST_NAME, user_first_name);
            }
            if (loginResponseModel.getData().getLastName() != null) {
                String user_last_name = loginResponseModel.getData().getLastName();
                AppControler.getInstance().put(AppControler.Key.USER_LAST_NAME, user_last_name);
            }
            if (loginResponseModel.getData().getEmail() != null) {
                String user_email = loginResponseModel.getData().getEmail();
                AppControler.getInstance().put(AppControler.Key.USER_EMAIL, user_email);
            }
            if (loginResponseModel.getData().getPhoneNumber() != null) {
                String user_phone = loginResponseModel.getData().getPhoneNumber();
                AppControler.getInstance().put(AppControler.Key.USER_PHONE, user_phone);
            }

            if (loginResponseModel.getData().getUserImage() != null) {
                String user_image = loginResponseModel.getData().getUserImage();
                AppControler.getInstance().put(AppControler.Key.USER_IMAGE, user_image);
            }
            if (loginResponseModel.getData().getCurrentLattitude() != null) {
                String current_latitiude = loginResponseModel.getData().getCurrentLattitude();
                AppControler.getInstance().put(AppControler.Key.USER_CURRENT_LATITUDE, current_latitiude);
            }
            if (loginResponseModel.getData().getCurrentLongitude() != null) {
                String current_longitude = loginResponseModel.getData().getCurrentLongitude();
                AppControler.getInstance().put(AppControler.Key.USER_CURRENT_LONGITUDE, current_longitude);
            }
            if (loginResponseModel.getData().getConsumerKey() != null) {
                String consumer_key = loginResponseModel.getData().getConsumerKey();
                AppControler.getInstance().put(AppControler.Key.CONSUMER_KEY, consumer_key);
            }
            if (loginResponseModel.getData().getSecretKey() != null) {
                String secret_key = loginResponseModel.getData().getSecretKey();
                AppControler.getInstance().put(AppControler.Key.SECRECT_KEY, secret_key);
            }
            if (loginResponseModel.getData().getAddress() != null && !loginResponseModel.getData().getAddress().equals("")) {
                String address = loginResponseModel.getData().getAddress();
                AppControler.getInstance().put(AppControler.Key.ADDRESS, address);
            }
            if (intent_type != null && intent_type.equalsIgnoreCase("from_product_details")) {
                Intent intent = new Intent(LoginActivity.this, ProductDetailsActivity.class);
                intent.putExtra("product_id", getIntent().getStringExtra("product_id"));
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            } else if (intent_type != null && intent_type.equalsIgnoreCase("from_cart_details")) {
                Intent intent = new Intent(LoginActivity.this, CartActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            } else {
//                Intent intent = new Intent(LoginActivity.this, AddressActivity.class);
//                intent.putExtra("intent_type", "from_login");
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
                navigateToHome();
            }


        }
        Toast.makeText(context, "" + loginResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
    }

    private void navigateToHome() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void errorResponseFromPresenter(String message) {
        dialog.dismiss();
        Toast.makeText(context, "" + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void successSocialLoginResponseFromPresenter(SocialLoginResponseModel socialLoginResponseModel) {
        dialog.dismiss();
        if (socialLoginResponseModel.getData() != null) {
            if (socialLoginResponseModel.getData().getAuthToken() != null) {
                String auth_token = socialLoginResponseModel.getData().getAuthToken();
                AppControler.getInstance().put(AppControler.Key.AUTH_TOKEN, auth_token);
            }
            if (socialLoginResponseModel.getData().getUserId() != null) {
                String user_id = String.valueOf(socialLoginResponseModel.getData().getUserId());
                AppControler.getInstance().put(AppControler.Key.USER_ID, user_id);
            }
            if (socialLoginResponseModel.getData().getUserName() != null) {
                String user_name = socialLoginResponseModel.getData().getUserName();
                AppControler.getInstance().put(AppControler.Key.USER_NAME, user_name);
            }
            if (socialLoginResponseModel.getData().getFirstName() != null) {
                String user_first_name = socialLoginResponseModel.getData().getFirstName();
                AppControler.getInstance().put(AppControler.Key.USER_FIRST_NAME, user_first_name);
            }
            if (socialLoginResponseModel.getData().getLastName() != null) {
                String user_last_name = socialLoginResponseModel.getData().getLastName();
                AppControler.getInstance().put(AppControler.Key.USER_LAST_NAME, user_last_name);
            }
            if (socialLoginResponseModel.getData().getEmail() != null) {
                String user_email = socialLoginResponseModel.getData().getEmail();
                AppControler.getInstance().put(AppControler.Key.USER_EMAIL, user_email);
            }
            if (socialLoginResponseModel.getData().getPhoneNumber() != null) {
                String user_phone = socialLoginResponseModel.getData().getPhoneNumber();
                AppControler.getInstance().put(AppControler.Key.USER_PHONE, user_phone);
            }

            if (socialLoginResponseModel.getData().getUserImage() != null) {
                String user_image = socialLoginResponseModel.getData().getUserImage();
                AppControler.getInstance().put(AppControler.Key.USER_IMAGE, user_image);
            }
            if (socialLoginResponseModel.getData().getCurrentLattitude() != null) {
                String current_latitiude = socialLoginResponseModel.getData().getCurrentLattitude();
                AppControler.getInstance().put(AppControler.Key.USER_CURRENT_LATITUDE, current_latitiude);
            }
            if (socialLoginResponseModel.getData().getCurrentLongitude() != null) {
                String current_longitude = socialLoginResponseModel.getData().getCurrentLongitude();
                AppControler.getInstance().put(AppControler.Key.USER_CURRENT_LONGITUDE, current_longitude);
            }
            if (socialLoginResponseModel.getData().getConsumerKey() != null) {
                String consumer_key = socialLoginResponseModel.getData().getConsumerKey();
                AppControler.getInstance().put(AppControler.Key.CONSUMER_KEY, consumer_key);
            }
            if (socialLoginResponseModel.getData().getSecretKey() != null) {
                String secret_key = socialLoginResponseModel.getData().getSecretKey();
                AppControler.getInstance().put(AppControler.Key.SECRECT_KEY, secret_key);
            }
            if (socialLoginResponseModel.getData().getAddress() != null && !socialLoginResponseModel.getData().getAddress().equals("")) {
                String address = socialLoginResponseModel.getData().getAddress();
                AppControler.getInstance().put(AppControler.Key.ADDRESS, address);

            }

            if (intent_type != null && intent_type.equalsIgnoreCase("from_product_details")) {
                Intent intent = new Intent(LoginActivity.this, ProductDetailsActivity.class);
                intent.putExtra("product_id", getIntent().getStringExtra("product_id"));
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            } else if (intent_type != null && intent_type.equalsIgnoreCase("from_cart_details")) {
                Intent intent = new Intent(LoginActivity.this, CartActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            } else {
//                Intent intent = new Intent(LoginActivity.this, AddressActivity.class);
//                intent.putExtra("intent_type", "from_login");
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
                navigateToHome();
            }


        }
        Toast.makeText(context, "" + socialLoginResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void errorSocialLoginResponseFromPresenter(String message) {
        dialog.dismiss();
        Toast.makeText(context, "" + message, Toast.LENGTH_SHORT).show();
    }

    // hash key for social login google/facebook
    public static String printKeyHash(Activity context) {
        PackageInfo packageInfo;
        String key = null;
        try {
            //getting application package name, as defined in manifest
            String packageName = context.getApplicationContext().getPackageName();
            //Retriving package info
            packageInfo = context.getPackageManager().getPackageInfo(packageName,
                    PackageManager.GET_SIGNATURES);
            Log.e("Package Name=", context.getApplicationContext().getPackageName());
            for (android.content.pm.Signature signature : packageInfo.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                key = new String(Base64.encode(md.digest(), 0));
                Log.e("Key Hash======", key);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("Name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("No such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }
        return key;
    }

    private void generateToken() {
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.w("TAG", "Fetching FCM registration token failed", task.getException());
                        return;
                    }

                    // Get new FCM registration token
                    device_token = task.getResult();
                });
    }

}
