package com.app.fastprint.ui.category.multipagePrinting;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.app.fastprint.R;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fastprint.baseClass.BaseClass;
import com.app.fastprint.networks.NetworkUtils;
import com.app.fastprint.ui.category.commericalPrinting.viewModel.Model;
import com.app.fastprint.ui.category.multipagePrinting.adapters.MBindingAdapter;
import com.app.fastprint.ui.category.multipagePrinting.adapters.MCoverPageAdapter;
import com.app.fastprint.ui.category.multipagePrinting.adapters.MCoverPageGramAdapter;
import com.app.fastprint.ui.category.multipagePrinting.adapters.MCoverlAdOneAdapter;
import com.app.fastprint.ui.category.multipagePrinting.adapters.MNumberOfColorAdapter;
import com.app.fastprint.ui.category.multipagePrinting.adapters.MNumberOfPageAdapter;
import com.app.fastprint.ui.category.multipagePrinting.adapters.MOrientationAdapter;
import com.app.fastprint.ui.category.multipagePrinting.adapters.MOtherAdapter;
import com.app.fastprint.ui.category.multipagePrinting.adapters.MPapaerTypeAdapter;
import com.app.fastprint.ui.category.multipagePrinting.adapters.MPaperGramAdapter;
import com.app.fastprint.ui.category.multipagePrinting.adapters.MSelectAJobAdapter;
import com.app.fastprint.ui.category.multipagePrinting.adapters.MSelectAJobTypeAdapter;
import com.app.fastprint.ui.category.multipagePrinting.adapters.MSelectColorAdapter;
import com.app.fastprint.ui.category.multipagePrinting.adapters.MSizeAdapter;
import com.app.fastprint.ui.category.multipagePrinting.adapters.MSlidesAdapter;
import com.app.fastprint.ui.category.multipagePrinting.adapters.MStandardSizeAdapter;
import com.app.fastprint.ui.category.multipagePrinting.interfaces.IMultiPagePrinting;
import com.app.fastprint.ui.category.multipagePrinting.interfaces.IPMultiPagePrinting;
import com.app.fastprint.ui.category.multipagePrinting.presenters.PMultiPagePrinting;
import com.app.fastprint.ui.category.multipagePrinting.responseModel.MultiPageFormSubmitResponseModel;
import com.app.fastprint.ui.category.multipagePrinting.responseModel.MultiPagePrintingResponseModel;
import com.app.fastprint.utills.AppConstants;
import com.app.fastprint.utills.CommonMethods;
import com.app.fastprint.utills.GpsUtils;
import com.app.fastprint.utills.UtilsAlertDialog;
import com.app.fastprint.utills.UtilsFontFamily;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.snackbar.Snackbar;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;
import com.vincent.filepicker.Constant;
import com.vincent.filepicker.activity.NormalFilePickActivity;
import com.vincent.filepicker.filter.entity.NormalFile;
import com.williamww.silkysignature.views.SignaturePad;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MultiPagePrintingActivity extends BaseClass implements IPickResult, IMultiPagePrinting {

    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.imgClear)
    ImageView imgClear;
    @BindView(R.id.tvTittle)
    TextView tvTittle;
    @BindView(R.id.edtFirstName)
    EditText edtFirstName;

    @BindView(R.id.edtLastName)
    EditText edtLastName;

    @BindView(R.id.edtCurrentLocation)
    EditText edtCurrentLocation;

    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edtCompany)
    EditText edtCompany;
    @BindView(R.id.edtPhone)
    EditText edtPhone;
    @BindView(R.id.edtMFinishing)
    EditText edtMFinishing;
    @BindView(R.id.edtUplaodFile)
    EditText edtUplaodFile;

    @BindView(R.id.edtComment)
    EditText edtComment;
    @BindView(R.id.signature_pad)
    SignaturePad signaturePad;
    @BindView(R.id.tvSignatureHere)
    TextView tvSignatureHere;
    @BindView(R.id.tvSubmit)
    TextView tvSubmit;
    @BindView(R.id.layoutMultipage)
    LinearLayout layoutMultipage;

    Context context;
    IPMultiPagePrinting ipMultiPagePrinting;
    Dialog dialog;

    public static EditText edtSelectJob;
    public static EditText edtQuantity;
    public static EditText edtColorCode;
    public static EditText edtNumberOfColor;
    public static EditText edtOrientation;
    public static EditText edtSize;
    public static EditText edtFinishedSize;
    public static EditText edtSlide;
    public static EditText edtNumberofPage;
    public static EditText edtPaperType;
    public static EditText edtCoverPaperType;
    public static EditText edtPaperGram;
    public static EditText edtCoverPaperGram;
    public static EditText edtBinding;
    public static EditText edtOpenSize;
    public static View mViewJobType;
    public static View viewedtFinishedSize;
    public static View viewOpenSize;
    public static TextView tvOk;

    public static Dialog mseletJodialog;
    public static Dialog mseletJoTypedialog;
    public static Dialog mseletColordialog;
    public static Dialog mnumberofColordialog;
    public static Dialog morientationDialog;
    public static Dialog msizeDialog;
    public static Dialog mstandardDialog;
    public static Dialog mslidesDialog;
    public static Dialog mCoverDialog;
    public static Dialog mpaperTypeDialog;
    public static Dialog mpaperGrmDialog;
    public static Dialog motherDialog;
    public static Dialog mNumberofPage;
    public static Dialog mCoverPageType;
    public static Dialog mCoverPageGram;
    public static Dialog mBinding;

    List<String> mselectjobList;
    List<String> mselectjobtypeList;
    List<String> mselectColorList;
    List<String> mnumberofColorList;
    List<String> morientationList;
    List<String> msizeList;
    List<String> mstandardSizeList;
    List<String> mslideList;
    List<String> mNumberOfPageList;
    List<String> mpaperTypeList;
    List<String> mpaperGramList;
    List<String> motherList;
    List<String> mbindingList;
    List<String> mCoverPageTypeList;
    List<String> mCoverPageGramList;
    ArrayList<Model> categoryModelArrayList;

    private SparseIntArray mErrorString;
    private static final int REQUEST_PERMISSIONS = 20;
    private static final int REQUEST_CODE_PICK_FILE = 30;

    String printing_id = "";
    String printing_name = "";
    String current_Address = "";

    MultipartBody.Part signatureToUpload;
    MultipartBody.Part imageToUpload;
    RequestBody file_nameRequest;

    private FusedLocationProviderClient mFusedLocationClient;
    private double wayLatitude = 0.0, wayLongitude = 0.0;
    String currentLatitude = "";
    String currentLongitude = "";
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private boolean isGPS = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_page_printing);
        context = MultiPagePrintingActivity.this;
        ButterKnife.bind(this);
        printing_id = getIntent().getStringExtra("printing_id");
        printing_name = getIntent().getStringExtra("printing_name");
        ipMultiPagePrinting = new PMultiPagePrinting(this);
        if (NetworkUtils.isNetworkConnectionAvailable(context)) {
            dialog = UtilsAlertDialog.ShowDialog(context);
            ipMultiPagePrinting.getMultiPagePrinting(printing_id);
        }
        layoutMultipage.setVisibility(View.GONE);
        edtSelectJob = (EditText) findViewById(R.id.edtSelectJob);
        edtQuantity = (EditText) findViewById(R.id.edtQuantity);
        edtColorCode = (EditText) findViewById(R.id.edtColorCode);
        edtNumberOfColor = (EditText) findViewById(R.id.edtNumberOfColor);
        edtOrientation = (EditText) findViewById(R.id.edtOrientation);
        edtSize = (EditText) findViewById(R.id.edtSize);
        edtFinishedSize = (EditText) findViewById(R.id.edtFinishedSize);
        edtSlide = (EditText) findViewById(R.id.edtSlide);
        edtNumberofPage = (EditText) findViewById(R.id.edtNumberofPage);
        edtPaperType = (EditText) findViewById(R.id.edtPaperType);
        edtCoverPaperType = (EditText) findViewById(R.id.edtCoverPaperType);
        edtPaperGram = (EditText) findViewById(R.id.edtPaperGram);
        edtCoverPaperGram = (EditText) findViewById(R.id.edtCoverPaperGram);
        edtBinding = (EditText) findViewById(R.id.edtBinding);
        edtOpenSize = (EditText) findViewById(R.id.edtOpenSize);
        viewedtFinishedSize = (View) findViewById(R.id.viewedtFinishedSize);
        viewOpenSize = (View) findViewById(R.id.viewOpenSize);
        initSignatuer();

        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));
        tvTittle.setText(printing_name);

        getCurrentLocation();
        if (!isGPS) {
            Toast.makeText(this, "Please turn on GPS", Toast.LENGTH_SHORT).show();
            return;
        }
        getLocations();

    }

    private void getLocations() {
        if (ActivityCompat.checkSelfPermission(MultiPagePrintingActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(MultiPagePrintingActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MultiPagePrintingActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    AppConstants.LOCATION_REQUEST);

        } else {
            mFusedLocationClient.getLastLocation().addOnSuccessListener(MultiPagePrintingActivity.this, location -> {
                if (location != null) {
                    wayLatitude = location.getLatitude();
                    wayLongitude = location.getLongitude();
                    currentLatitude = String.valueOf(wayLongitude);
                    currentLongitude = String.valueOf(wayLongitude);
                    Log.d("mydata++", "first" + wayLatitude + "" + wayLongitude);
                    getAddress(context, wayLatitude, wayLongitude);
                    edtCurrentLocation.setText(String.format(Locale.US, "%s - %s", wayLatitude, wayLongitude));
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
                        edtCurrentLocation.setText(String.format(Locale.US, "%s - %s", wayLatitude, wayLongitude));

                        // txtLocation.setText(String.format(Locale.US, "%s - %s", wayLatitude, wayLongitude));
                        if (mFusedLocationClient != null) {
                            mFusedLocationClient.removeLocationUpdates(locationCallback);
                        }
                    }
                }
            }
        };
    }

    private void initSignatuer() {
        signaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {
                tvSignatureHere.setText("");
                imgClear.setVisibility(View.GONE);
                Toast.makeText(MultiPagePrintingActivity.this, "OnStartSigning", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSigned() {
                tvSignatureHere.setText("");
                imgClear.setVisibility(View.VISIBLE);
                Bitmap signatureBitmap = signaturePad.getSignatureBitmap();
                Log.d("data++++", "data++++" + signatureBitmap);
                sendSignatureToserver(signatureBitmap);
            }

            @Override
            public void onClear() {
                tvSignatureHere.setText(getString(R.string.signature_here));
                imgClear.setVisibility(View.GONE);
            }
        });
    }

    @OnClick({R.id.imgBack, R.id.edtSelectJob, R.id.edtQuantity, R.id.edtColorCode,
            R.id.edtNumberOfColor, R.id.edtOrientation, R.id.edtSize,
            R.id.edtSlide, R.id.edtMFinishing, R.id.edtBinding, R.id.edtNumberofPage,
            R.id.edtPaperType, R.id.edtCoverPaperType, R.id.edtPaperGram,
            R.id.edtCoverPaperGram, R.id.edtUplaodFile, R.id.tvSubmit, R.id.imgClear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.edtSelectJob:
                mSelectJobDialog();
                break;
            case R.id.edtQuantity:
                break;
            case R.id.edtColorCode:
                mColorCodeDialog();
                break;
            case R.id.edtNumberOfColor:
                mNumberColorCodeDialog();
                break;
            case R.id.edtOrientation:
                mOrientationDialog();
                break;
            case R.id.edtSize:
                mSizeDialog();
                break;
            case R.id.edtSlide:
                mSlideDialog();
                break;
            case R.id.edtMFinishing:
                mCoverAdOnDialog();
                break;
            case R.id.edtBinding:
                mBindingDialog();
                break;
            case R.id.edtNumberofPage:
                mNumberofPageDialog();
                break;
            case R.id.edtPaperType:
                mCoverofPageDialog();
                break;
            case R.id.edtCoverPaperType:
                mPaperTypeGramDialog();
                break;
            case R.id.edtPaperGram:
                mCoverofPageGramDialog();
                break;
            case R.id.edtCoverPaperGram:
                mPaperTypeDialog();
                break;
            case R.id.edtUplaodFile:
                if (runTimePermission()) {
                } else {
                    openMediaDialog(); // upload file option
                }
                break;
            case R.id.tvSubmit:
                validationOnMultiPagePrinting();
                break;
            case R.id.imgClear:

                signaturePad.clear();
                break;
        }
    }

    private void validationOnMultiPagePrinting() {
        if (edtFirstName.getText().toString().trim().isEmpty()) {
            Toast.makeText(context, "Enter your name", Toast.LENGTH_SHORT).show();
        } else if (edtFirstName.length() < 3 || edtFirstName.length() > 15) {
            Toast.makeText(context, "name should be between 3 to 15 characters", Toast.LENGTH_SHORT).show();
        } else if (edtLastName.getText().toString().trim().isEmpty()) {
            Toast.makeText(context, "name should be between 3 to 15 characters", Toast.LENGTH_SHORT).show();
        } else if (edtLastName.length() < 3 || edtLastName.length() > 15) {
            Toast.makeText(context, "last name should be between 3 to 15 characters", Toast.LENGTH_SHORT).show();
        } else if (edtEmail.getText().toString().trim().isEmpty()) {
            Toast.makeText(context, "Enter your email", Toast.LENGTH_SHORT).show();
        } else if (!CommonMethods.isValidEmail(edtEmail.getText().toString())) {
            Toast.makeText(context, "Enter valid email", Toast.LENGTH_SHORT).show();
        }
//        else if (edtCompany.getText().toString().trim().isEmpty()) {
//            Toast.makeText(context, "Enter your company name", Toast.LENGTH_SHORT).show();
//        }
//        else if (!edtCompany.getText().toString().isEmpty()&&edtCompany.length() < 5 || edtCompany.length() > 25) {
//            Toast.makeText(context, "company should be between 5 to 25 characters", Toast.LENGTH_SHORT).show();
//        }
        else if (edtPhone.getText().toString().trim().isEmpty()) {
            Toast.makeText(context, "Enter phone number", Toast.LENGTH_SHORT).show();
        } else if (!CommonMethods.isValidMobile(edtPhone.getText().toString())) {
            Toast.makeText(context, "Enter valid phone number", Toast.LENGTH_SHORT).show();
        } else if (edtSelectJob.getText().toString().trim().isEmpty()) {
            Toast.makeText(context, "Select Job type", Toast.LENGTH_SHORT).show();
        } else if (edtQuantity.getText().toString().trim().isEmpty()) {
            Toast.makeText(context, "Enter quantity", Toast.LENGTH_SHORT).show();
        } else if (edtColorCode.getText().toString().trim().isEmpty()) {
            Toast.makeText(context, "Select color code", Toast.LENGTH_SHORT).show();
        } else if (edtNumberOfColor.getText().toString().trim().isEmpty()) {
            Toast.makeText(context, "Select number of color", Toast.LENGTH_SHORT).show();
        } else if (edtOrientation.getText().toString().trim().isEmpty()) {
            Toast.makeText(context, "Select orientation", Toast.LENGTH_SHORT).show();
        } else if (edtSize.getText().toString().trim().isEmpty()) {
            Toast.makeText(context, "Select size", Toast.LENGTH_SHORT).show();
        } else if (edtFinishedSize.getText().toString().trim().isEmpty()) {
            Toast.makeText(context, "Select finished size", Toast.LENGTH_SHORT).show();
        }
//        else if (edtOpenSize.getText().toString().trim().isEmpty()) {
//            Toast.makeText(context, "Select open size", Toast.LENGTH_SHORT).show();
//        }
        else if (edtSlide.getText().toString().trim().isEmpty()) {
            Toast.makeText(context, "Select slides", Toast.LENGTH_SHORT).show();
        }
//        else if (edtMFinishing.getText().toString().trim().isEmpty()) {
//            Toast.makeText(context, "Select finishing", Toast.LENGTH_SHORT).show();
//        }
        else if (edtBinding.getText().toString().trim().isEmpty()) {
            Toast.makeText(context, "Select binding type", Toast.LENGTH_SHORT).show();
        } else if (edtNumberofPage.getText().toString().trim().isEmpty()) {
            Toast.makeText(context, "Select number of page", Toast.LENGTH_SHORT).show();
        }
//        else if (edtPaperType.getText().toString().trim().isEmpty()) {
//            Toast.makeText(context, "Select cover page type", Toast.LENGTH_SHORT).show();
//        } else if (edtCoverPaperType.getText().toString().trim().isEmpty()) {
//            Toast.makeText(context, "Select inside page type", Toast.LENGTH_SHORT).show();
//        } else if (edtPaperGram.getText().toString().trim().isEmpty()) {
//            Toast.makeText(context, "Select cover page gram", Toast.LENGTH_SHORT).show();
//        } else if (edtCoverPaperGram.getText().toString().trim().isEmpty()) {
//            Toast.makeText(context, "Select inside page gram", Toast.LENGTH_SHORT).show();
//        }
        else if (edtUplaodFile.getText().toString().trim().isEmpty()) {
            Toast.makeText(context, "Please upload file", Toast.LENGTH_SHORT).show();
        }
//        else if (edtComment.getText().toString().trim().isEmpty()) {
//            Toast.makeText(context, "Write your comment", Toast.LENGTH_SHORT).show();
//        }
        else {
            String userName=edtFirstName.getText().toString().trim()+" "+edtLastName.getText().toString().trim();
            if (NetworkUtils.isNetworkConnectionAvailable(context)) {
                dialog = UtilsAlertDialog.ShowDialog(context);
                ipMultiPagePrinting.submitMultiPagePrintingFrom(printing_name,
                        userName,
                        edtEmail.getText().toString().trim(),
                        edtCompany.getText().toString().trim(),
                        edtPhone.getText().toString().trim(),
                        edtSelectJob.getText().toString().trim(),
                        edtQuantity.getText().toString().trim(),
                        edtColorCode.getText().toString().trim(),
                        edtNumberOfColor.getText().toString().trim(),
                        edtOrientation.getText().toString().trim(),
                        edtSize.getText().toString().trim(),
                        edtFinishedSize.getText().toString().trim(),
                        edtOpenSize.getText().toString().trim(),
                        edtSlide.getText().toString().trim(),
                        edtMFinishing.getText().toString().trim(),
                        edtBinding.getText().toString().trim(),
                        edtNumberofPage.getText().toString().trim(),
                        edtPaperType.getText().toString().trim(),
                        edtCoverPaperType.getText().toString().trim(),
                        edtPaperGram.getText().toString().trim(),
                        edtCoverPaperGram.getText().toString().trim(),
                        current_Address,
                        edtComment.getText().toString().trim(),
                        file_nameRequest,
                        imageToUpload,
                        signatureToUpload);
            }
        }
    }
    private boolean runTimePermission() {

        mErrorString = new SparseIntArray();
        int currentapiVersion = Build.VERSION.SDK_INT;
        // if current version is M or greater than M
        if (currentapiVersion >= Build.VERSION_CODES.M) {
            String[] array = {
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA,
            };

            requestAppPermissions(array, R.string.permission, REQUEST_PERMISSIONS);
        } else {
            onPermissionsGranted(REQUEST_PERMISSIONS);
        }

        return false;
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
                        ActivityCompat.requestPermissions(MultiPagePrintingActivity.this, requestedPermissions, requestCode);
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

    @Override
    public void successResponseFromPresenter(MultiPagePrintingResponseModel multiPagePrintingResponseModel) {
        dialog.dismiss();
        if (multiPagePrintingResponseModel != null && multiPagePrintingResponseModel.getData().getMultiPage().size() > 0) {
            layoutMultipage.setVisibility(View.VISIBLE);
            Log.d("response+++", "response+++" + multiPagePrintingResponseModel.getData().getMultiPage().size());
            for (int i = 0; i < multiPagePrintingResponseModel.getData().getMultiPage().size(); i++) {
                mselectjobList = multiPagePrintingResponseModel.getData().getMultiPage().get(i).getJob();
                mselectColorList = multiPagePrintingResponseModel.getData().getMultiPage().get(i).getColorCode();
                mnumberofColorList = multiPagePrintingResponseModel.getData().getMultiPage().get(i).getNumerOfColors();
                msizeList = multiPagePrintingResponseModel.getData().getMultiPage().get(i).getSize();
                morientationList = multiPagePrintingResponseModel.getData().getMultiPage().get(i).getOrientation();
                mslideList = multiPagePrintingResponseModel.getData().getMultiPage().get(i).getSides();
                mbindingList = multiPagePrintingResponseModel.getData().getMultiPage().get(i).getBinding();
                mCoverPageTypeList = multiPagePrintingResponseModel.getData().getMultiPage().get(i).getCoverPageType();
                mCoverPageGramList = multiPagePrintingResponseModel.getData().getMultiPage().get(i).getCoverPageGram();
                mpaperTypeList = multiPagePrintingResponseModel.getData().getMultiPage().get(i).getInsidePageType();
                mpaperGramList = multiPagePrintingResponseModel.getData().getMultiPage().get(i).getInsidePageGram();
                categoryModelArrayList = new ArrayList<>();
                for (int j = 0; j < multiPagePrintingResponseModel.getData().getMultiPage().get(i).getCoverAdOns().size(); j++) {
                    Model imageModel = new Model();
                    imageModel.setName(multiPagePrintingResponseModel.getData().getMultiPage().get(i).getCoverAdOns().get(j));
                    categoryModelArrayList.add(imageModel);
                }
            }


        } else {

        }
    }

    @Override
    public void errorResponseFromPresenter(String message) {
        dialog.dismiss();
        layoutMultipage.setVisibility(View.GONE);
        String error_message = message;
        Toast.makeText(context, "" + error_message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void successMultipageSubmitResponseFromPresenter(MultiPageFormSubmitResponseModel multiPageFormSubmitResponseModel) {
        dialog.dismiss();
        String message = multiPageFormSubmitResponseModel.getData().getMultipage();
        Toast.makeText(context, "" + message, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void errorMultipageSubmitFromPresenter(String message) {
        dialog.dismiss();
        String error_message = message;
        Toast.makeText(context, "" + error_message, Toast.LENGTH_SHORT).show();
    }

    private void mSelectJobDialog() {
        mseletJodialog = new Dialog(MultiPagePrintingActivity.this);
        mseletJodialog.setContentView(R.layout.dialog_mselect_job);
        mseletJodialog.setCancelable(true);
        RecyclerView recyclermSelectJob = mseletJodialog.findViewById(R.id.recyclermSelectJob);
        MSelectAJobAdapter mSelectAJobAdapter = new MSelectAJobAdapter(context, mselectjobList);
        recyclermSelectJob.setLayoutManager(new LinearLayoutManager(this));
        recyclermSelectJob.setAdapter(mSelectAJobAdapter);
        DividerItemDecoration divider = new DividerItemDecoration(recyclermSelectJob.getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.custom_divider));
        recyclermSelectJob.addItemDecoration(divider);
        mseletJodialog.show();
    }

    private void mSelectJobTypeDialog() {
        mseletJoTypedialog = new Dialog(MultiPagePrintingActivity.this);
        mseletJoTypedialog.setContentView(R.layout.dialog_mselect_job_type);
        mseletJoTypedialog.setCancelable(true);
        RecyclerView recyclermSelectJobType = mseletJoTypedialog.findViewById(R.id.recyclermSelectJobType);
        MSelectAJobTypeAdapter mSelectAJobTypeAdapter = new MSelectAJobTypeAdapter(context, mselectjobtypeList);
        recyclermSelectJobType.setLayoutManager(new LinearLayoutManager(this));
        recyclermSelectJobType.setAdapter(mSelectAJobTypeAdapter);
        DividerItemDecoration divider = new DividerItemDecoration(recyclermSelectJobType.getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.custom_divider));
        recyclermSelectJobType.addItemDecoration(divider);
        mseletJoTypedialog.show();
    }

    private void mColorCodeDialog() {
        mseletColordialog = new Dialog(MultiPagePrintingActivity.this);
        mseletColordialog.setContentView(R.layout.dialog_mselect_color);
        mseletColordialog.setCancelable(true);
        RecyclerView recyclerMColor = mseletColordialog.findViewById(R.id.recyclerMColor);
        MSelectColorAdapter mSelectColorAdapter = new MSelectColorAdapter(context, mselectColorList);
        recyclerMColor.setLayoutManager(new LinearLayoutManager(this));
        recyclerMColor.setAdapter(mSelectColorAdapter);
        DividerItemDecoration divider = new DividerItemDecoration(recyclerMColor.getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.custom_divider));
        recyclerMColor.addItemDecoration(divider);
        mseletColordialog.show();
    }

    private void mNumberColorCodeDialog() {

        mnumberofColordialog = new Dialog(MultiPagePrintingActivity.this);
        mnumberofColordialog.setContentView(R.layout.dialog_mnumber_of_color);
        mnumberofColordialog.setCancelable(true);
        RecyclerView recyclermNumberOfColor = mnumberofColordialog.findViewById(R.id.recyclermNumberOfColor);
        MNumberOfColorAdapter mSelectColorAdapter = new MNumberOfColorAdapter(context, mnumberofColorList);
        recyclermNumberOfColor.setLayoutManager(new LinearLayoutManager(this));
        recyclermNumberOfColor.setAdapter(mSelectColorAdapter);
        DividerItemDecoration divider = new DividerItemDecoration(recyclermNumberOfColor.getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.custom_divider));
        recyclermNumberOfColor.addItemDecoration(divider);
        mnumberofColordialog.show();
    }

    private void mSizeDialog() {

        msizeDialog = new Dialog(MultiPagePrintingActivity.this);
        msizeDialog.setContentView(R.layout.dialog_msize);
        msizeDialog.setCancelable(true);
        RecyclerView recyclerMSize = msizeDialog.findViewById(R.id.recyclerMSize);
        MSizeAdapter mSizeAdapter = new MSizeAdapter(context, msizeList);
        recyclerMSize.setLayoutManager(new LinearLayoutManager(this));
        recyclerMSize.setAdapter(mSizeAdapter);
        DividerItemDecoration divider = new DividerItemDecoration(recyclerMSize.getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.custom_divider));
        recyclerMSize.addItemDecoration(divider);
        msizeDialog.show();
    }

    private void mOtherDialog() {
        motherDialog = new Dialog(MultiPagePrintingActivity.this);
        motherDialog.setContentView(R.layout.dialog_m_other);
        motherDialog.setCancelable(true);
        RecyclerView recyclerMOthers = motherDialog.findViewById(R.id.recyclerMOthers);
        MOtherAdapter mOtherAdapter = new MOtherAdapter(context, motherList);
        recyclerMOthers.setLayoutManager(new LinearLayoutManager(this));
        recyclerMOthers.setAdapter(mOtherAdapter);
        DividerItemDecoration divider = new DividerItemDecoration(recyclerMOthers.getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.custom_divider));
        recyclerMOthers.addItemDecoration(divider);
        motherDialog.show();
    }

    private void mStandredSizeDialog() {
        mstandardDialog = new Dialog(MultiPagePrintingActivity.this);
        mstandardDialog.setContentView(R.layout.dialog_mstandard_size);
        mstandardDialog.setCancelable(true);
        RecyclerView recyclermStandardSize = mstandardDialog.findViewById(R.id.recyclermStandardSize);
        MStandardSizeAdapter mStandardSizeAdapter = new MStandardSizeAdapter(context, mstandardSizeList);
        recyclermStandardSize.setLayoutManager(new LinearLayoutManager(this));
        recyclermStandardSize.setAdapter(mStandardSizeAdapter);
        DividerItemDecoration divider = new DividerItemDecoration(recyclermStandardSize.getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.custom_divider));
        recyclermStandardSize.addItemDecoration(divider);
        mstandardDialog.show();
    }

    private void mOrientationDialog() {

        morientationDialog = new Dialog(MultiPagePrintingActivity.this);
        morientationDialog.setContentView(R.layout.dialog_morientation);
        morientationDialog.setCancelable(true);
        RecyclerView recyclermStandardSize = morientationDialog.findViewById(R.id.recyclermOrientations);
        MOrientationAdapter mOrientationAdapter = new MOrientationAdapter(context, morientationList);
        recyclermStandardSize.setLayoutManager(new LinearLayoutManager(this));
        recyclermStandardSize.setAdapter(mOrientationAdapter);
        DividerItemDecoration divider = new DividerItemDecoration(recyclermStandardSize.getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.custom_divider));
        recyclermStandardSize.addItemDecoration(divider);
        morientationDialog.show();
    }

    private void mSlideDialog() {
        mslidesDialog = new Dialog(MultiPagePrintingActivity.this);
        mslidesDialog.setContentView(R.layout.dialog_mslides);
        mslidesDialog.setCancelable(true);
        RecyclerView recyclermSlides = mslidesDialog.findViewById(R.id.recyclermSlides);
        MSlidesAdapter mSlidesAdapter = new MSlidesAdapter(context, mslideList);
        recyclermSlides.setLayoutManager(new LinearLayoutManager(this));
        recyclermSlides.setAdapter(mSlidesAdapter);
        DividerItemDecoration divider = new DividerItemDecoration(recyclermSlides.getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.custom_divider));
        recyclermSlides.addItemDecoration(divider);
        mslidesDialog.show();
    }

    private void mBindingDialog() {
        mBinding = new Dialog(MultiPagePrintingActivity.this);
        //mBinding.setContentView(R.layout.dialog_mBinding);
        mBinding.setContentView(R.layout.dialog_m_bindings);
        mBinding.setCancelable(true);
        RecyclerView recyclerMBind = mBinding.findViewById(R.id.recyclerMBind);
        MBindingAdapter mBindingAdapter = new MBindingAdapter(context, mbindingList);
        recyclerMBind.setLayoutManager(new LinearLayoutManager(this));
        recyclerMBind.setAdapter(mBindingAdapter);
        DividerItemDecoration divider = new DividerItemDecoration(recyclerMBind.getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.custom_divider));
        recyclerMBind.addItemDecoration(divider);
        mBinding.show();
    }

    private void mNumberofPageDialog() {
        mNumberofPage = new Dialog(MultiPagePrintingActivity.this);
        mNumberofPage.setContentView(R.layout.dialog_m_number_of_page);
        mNumberofPage.setCancelable(true);
        RecyclerView recyclerNumberofPage = mNumberofPage.findViewById(R.id.recyclerNumberofPage);
        MNumberOfPageAdapter mBindingAdapter = new MNumberOfPageAdapter(context, mNumberOfPageList);
        recyclerNumberofPage.setLayoutManager(new LinearLayoutManager(this));
        recyclerNumberofPage.setAdapter(mBindingAdapter);
        DividerItemDecoration divider = new DividerItemDecoration(recyclerNumberofPage.getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.custom_divider));
        recyclerNumberofPage.addItemDecoration(divider);
        mNumberofPage.show();
    }

    private void mCoverofPageDialog() {
        mCoverPageType = new Dialog(MultiPagePrintingActivity.this);
        mCoverPageType.setContentView(R.layout.dialog_cover_page_type);
        mCoverPageType.setCancelable(true);
        RecyclerView recyclerCoverPageType = mCoverPageType.findViewById(R.id.recyclerCoverPageType);
        MCoverPageAdapter mCoverPageAdapter = new MCoverPageAdapter(context, mCoverPageTypeList);
        recyclerCoverPageType.setLayoutManager(new LinearLayoutManager(this));
        recyclerCoverPageType.setAdapter(mCoverPageAdapter);
        DividerItemDecoration divider = new DividerItemDecoration(recyclerCoverPageType.getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.custom_divider));
        recyclerCoverPageType.addItemDecoration(divider);
        mCoverPageType.show();
    }

    private void mCoverofPageGramDialog() {

        mCoverPageGram = new Dialog(MultiPagePrintingActivity.this);
        mCoverPageGram.setContentView(R.layout.dialog_cover_page_gram);
        mCoverPageGram.setCancelable(true);
        RecyclerView recyclerCoverPageGram = mCoverPageGram.findViewById(R.id.recyclerCoverPageGram);
        MCoverPageGramAdapter mCoverPageGramAdapter = new MCoverPageGramAdapter(context, mCoverPageGramList);
        recyclerCoverPageGram.setLayoutManager(new LinearLayoutManager(this));
        recyclerCoverPageGram.setAdapter(mCoverPageGramAdapter);
        DividerItemDecoration divider = new DividerItemDecoration(recyclerCoverPageGram.getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.custom_divider));
        recyclerCoverPageGram.addItemDecoration(divider);
        mCoverPageGram.show();
    }

    private void mPaperTypeGramDialog() {

        mpaperTypeDialog = new Dialog(MultiPagePrintingActivity.this);
        mpaperTypeDialog.setContentView(R.layout.dialog_paper_type);
        mpaperTypeDialog.setCancelable(true);
        RecyclerView recyclerPaperType = mpaperTypeDialog.findViewById(R.id.recyclerPaperType);
        MPapaerTypeAdapter mPapaerTypeAdapter = new MPapaerTypeAdapter(context, mpaperTypeList);
        recyclerPaperType.setLayoutManager(new LinearLayoutManager(this));
        recyclerPaperType.setAdapter(mPapaerTypeAdapter);
        DividerItemDecoration divider = new DividerItemDecoration(recyclerPaperType.getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.custom_divider));
        recyclerPaperType.addItemDecoration(divider);
        mpaperTypeDialog.show();

    }

    private void mPaperTypeDialog() {

        mpaperGrmDialog = new Dialog(MultiPagePrintingActivity.this);
        mpaperGrmDialog.setContentView(R.layout.dialog_paper_gram);
        mpaperGrmDialog.setCancelable(true);
        RecyclerView recyclerPaperGram = mpaperGrmDialog.findViewById(R.id.recyclerPaperGram);
        MPaperGramAdapter mPaperGramAdapter = new MPaperGramAdapter(context, mpaperGramList);
        recyclerPaperGram.setLayoutManager(new LinearLayoutManager(this));
        recyclerPaperGram.setAdapter(mPaperGramAdapter);
        DividerItemDecoration divider = new DividerItemDecoration(recyclerPaperGram.getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.custom_divider));
        recyclerPaperGram.addItemDecoration(divider);
        mpaperGrmDialog.show();


    }


    private void mCoverAdOnDialog() {

        mCoverDialog = new Dialog(MultiPagePrintingActivity.this);
        mCoverDialog.setContentView(R.layout.dialog_cover_ad_one);
        mCoverDialog.setCancelable(true);

        RecyclerView recyclerCoverAdOne = mCoverDialog.findViewById(R.id.recyclerCoverAdOne);
        tvOk = mCoverDialog.findViewById(R.id.tvOk);
        TextView tvCancel = mCoverDialog.findViewById(R.id.tvCancel);
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCoverDialog.dismiss();
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCoverDialog.dismiss();
            }
        });
        MCoverlAdOneAdapter coverlAdOneAdapter = new MCoverlAdOneAdapter(context, categoryModelArrayList, MultiPagePrintingActivity.this);
        recyclerCoverAdOne.setLayoutManager(new LinearLayoutManager(this));
        recyclerCoverAdOne.setAdapter(coverlAdOneAdapter);
        DividerItemDecoration divider = new DividerItemDecoration(recyclerCoverAdOne.getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.custom_divider));
        recyclerCoverAdOne.addItemDecoration(divider);
        mCoverDialog.show();
    }

    public void OnOptionClick(List<String> list) {
        edtMFinishing.setText(Arrays.toString(new List[]{list}).replaceAll("\\[|\\]", ""));

    }

    // upload file option
    private void openMediaDialog() {
        Dialog mediaDialog = new Dialog(MultiPagePrintingActivity.this);
        mediaDialog.setContentView(R.layout.dialog_media);
        mediaDialog.setCancelable(true);

        TextView tvChoose = (TextView) mediaDialog.findViewById(R.id.tvChoose);
        TextView tvMedia = (TextView) mediaDialog.findViewById(R.id.tvMedia);
        TextView tvUploadFile = (TextView) mediaDialog.findViewById(R.id.tvUploadFile);
        TextView tvCancel = (TextView) mediaDialog.findViewById(R.id.tvCancel);

        tvChoose.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));
        tvCancel.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));

        tvMedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickImageDialog.build(new PickSetup().setButtonOrientation(LinearLayout.HORIZONTAL)).show(MultiPagePrintingActivity.this);
                mediaDialog.dismiss();
            }
        });

        tvUploadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MultiPagePrintingActivity.this, NormalFilePickActivity.class);
                intent.putExtra(Constant.MAX_NUMBER, 1);
                intent.putExtra(NormalFilePickActivity.SUFFIX, new String[]{"doc", "docx", "pdf"});
                startActivityForResult(intent, Constant.REQUEST_CODE_PICK_FILE);
                mediaDialog.dismiss();

            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mediaDialog.dismiss();
            }
        });
        mediaDialog.show();
    }


    public void getAddress(Context context, double LATITUDE, double LONGITUDE) {
        //Set Address
        try {
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);

            if (addresses != null && addresses.size() > 0) {
                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                current_Address = address;

                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }

    @Override
    public void onPickResult(PickResult result) {
        if (result.getError() == null) {
            String[] parts = result.getPath().split("/");
            final String fileName = parts[parts.length - 1];
            edtUplaodFile.setText(fileName);
            Bitmap bitmap = result.getBitmap();
           imageToUpload =sendImageFileToserver(bitmap);
        }
    }

    private MultipartBody.Part sendImageFileToserver(Bitmap bitmap) {
        File filesDir = context.getFilesDir();
        File file = new File(filesDir, "image" + System.currentTimeMillis() + ".png");

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bos);
        byte[] bitmapdata = bos.toByteArray();

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fos.write(bitmapdata);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        RequestBody reqFile = null;
        StringBuilder builder = new StringBuilder();
        String fileName = null;
        String path = file.getPath();
        builder.append(path + "\n");
        String[] parts = file.getPath().split("/");
        fileName = parts[parts.length - 1];

        try {
            reqFile = RequestBody.create(
                    MediaType.parse(Files.probeContentType(file.toPath())), file);
        } catch (IOException e) {
            e.printStackTrace();
            reqFile = RequestBody.create(MediaType.parse("*//*"), file);
        }

        MultipartBody.Part imageMultipart = MultipartBody.Part.createFormData("file_name", fileName, reqFile);
        file_nameRequest = RequestBody.create(MediaType.parse("text/plain"), "file_name");
        Log.d("multipart++", "multipart++" + imageToUpload);
        return imageMultipart;
    }

    private MultipartBody.Part sendSignatureToserver(Bitmap bitmap) {
        File filesDir = context.getFilesDir();
        File file = new File(filesDir, "image" + System.currentTimeMillis() + ".png");

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bos);
        byte[] bitmapdata = bos.toByteArray();

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fos.write(bitmapdata);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder builder = new StringBuilder();
        String fileName = null;
        String path = file.getPath();
        builder.append(path + "\n");
        String[] parts = file.getPath().split("/");
        fileName = parts[parts.length - 1];

        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
        signatureToUpload = MultipartBody.Part.createFormData("signature_file", fileName, reqFile);
        Log.d("multipart++", "multipart++" + signatureToUpload);
        return signatureToUpload;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.REQUEST_CODE_PICK_FILE && resultCode == RESULT_OK) {

            ArrayList<NormalFile> list = data.getParcelableArrayListExtra(Constant.RESULT_PICK_FILE);
            StringBuilder builder = new StringBuilder();
            String fileName = null;
            for (NormalFile file : list) {
                String path = file.getPath();
                builder.append(path + "\n");
                String[] parts = file.getPath().split("/");
                fileName = parts[parts.length - 1];

                File files = new File(path);
                RequestBody requestFiles = null;

                try {
                    requestFiles = RequestBody.create(
                            MediaType.parse(Files.probeContentType(files.toPath())), files);
                } catch (IOException e) {
                    e.printStackTrace();
                    requestFiles = RequestBody.create(MediaType.parse("*//*"), files);
                }

                imageToUpload = MultipartBody.Part.createFormData("file_name", fileName, requestFiles);
                file_nameRequest = RequestBody.create(MediaType.parse("text/plain"), "file_name");

                Log.d("file++", "file++" + imageToUpload);
            }
            edtUplaodFile.setText(fileName);
        } else if (requestCode == AppConstants.GPS_REQUEST && requestCode == RESULT_OK) {
            isGPS = true; // flag maintain before get location

        }
    }
}