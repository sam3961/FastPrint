package com.app.fastprint.ui.uploadFileForm;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import com.app.fastprint.baseClass.BaseClass;
import com.app.fastprint.networks.NetworkUtils;
import com.app.fastprint.ui.address.AddressActivity;
import com.app.fastprint.ui.login.LoginActivity;
import com.app.fastprint.ui.uploadFileForm.interfaces.IFormFileUpload;
import com.app.fastprint.ui.uploadFileForm.interfaces.IPFormFileUpload;
import com.app.fastprint.ui.uploadFileForm.presenter.PFormFileUpload;
import com.app.fastprint.ui.uploadFileForm.responseModel.UploadFileSubmitResponseModel;
import com.app.fastprint.utills.AppControler;
import com.app.fastprint.utills.CommonMethods;
import com.app.fastprint.utills.UtilsAlertDialog;
import com.app.fastprint.utills.UtilsFontFamily;
import com.google.android.material.snackbar.Snackbar;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;
import com.vincent.filepicker.Constant;
import com.vincent.filepicker.activity.NormalFilePickActivity;
import com.vincent.filepicker.filter.entity.NormalFile;
import com.williamww.silkysignature.views.SignaturePad;
import com.app.fastprint.R;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class FormFileUploadActivity extends BaseClass implements IPickResult, IFormFileUpload {
    String intent_From = "";
    Context context;
    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.imgClear)
    ImageView imgClear;
    @BindView(R.id.tvTittle)
    TextView tvTittle;
    @BindView(R.id.tvSubmit)
    TextView tvSubmit;
    @BindView(R.id.edtUplaodFile)
    EditText edtUplaodFile;

    @BindView(R.id.edtName)
    EditText edtName;
    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edtPhone)
    EditText edtPhone;

    @BindView(R.id.layoutUploadFile)
    RelativeLayout layoutUploadFile;

    @BindView(R.id.layoutName)
    RelativeLayout layoutName;

    @BindView(R.id.layoutEmail)
    RelativeLayout layoutEmail;

    @BindView(R.id.layoutPhone)
    RelativeLayout layoutPhone;

    @BindView(R.id.layoutSignature)
    RelativeLayout layoutSignature;

    @BindView(R.id.signaturePad)
    SignaturePad signaturePad;
    @BindView(R.id.tvSignatureHere)
    TextView tvSignatureHere;
    MultipartBody.Part signatureToUpload;
    public static MultipartBody.Part imageToUpload;
    private SparseIntArray mErrorString;
    private static final int REQUEST_PERMISSIONS = 20;
    private static final int REQUEST_CODE_PICK_FILE = 30;
    private static final int FILE_REQUEST_CODE = 40;
    IPFormFileUpload ipFormFileUpload;
    Dialog dialog;
    String auth_token = "";
    String address = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_file_upload);
        context = FormFileUploadActivity.this;
        ButterKnife.bind(this);
        auth_token = AppControler.getInstance(context).getString(AppControler.Key.AUTH_TOKEN);
        address = AppControler.getInstance(context).getString(AppControler.Key.ADDRESS);

        ipFormFileUpload = new PFormFileUpload(this);
        intent_From = getIntent().getStringExtra("intent_From");
        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));

        if (intent_From.equalsIgnoreCase("extras")) {
            layoutUploadFile.setVisibility(View.VISIBLE);
            layoutName.setVisibility(View.VISIBLE);
            layoutEmail.setVisibility(View.VISIBLE);
            layoutPhone.setVisibility(View.VISIBLE);
            layoutSignature.setVisibility(View.VISIBLE);


        } else {
            layoutUploadFile.setVisibility(View.VISIBLE);
            layoutName.setVisibility(View.GONE);
            layoutEmail.setVisibility(View.GONE);
            layoutPhone.setVisibility(View.GONE);
            layoutSignature.setVisibility(View.GONE);


        }

        initSignatuer();
    }

    private void initSignatuer() {
        signaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {
                tvSignatureHere.setText("");
                imgClear.setVisibility(View.GONE);
                Toast.makeText(FormFileUploadActivity.this, "OnStartSigning", Toast.LENGTH_SHORT).show();
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

    @OnClick({R.id.imgBack, R.id.imgClear, R.id.tvSubmit, R.id.edtUplaodFile})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.imgClear:
                signaturePad.clear();
                break;
            case R.id.tvSubmit:
                validationOnUploadFile();
                break;
            case R.id.edtUplaodFile:
                if (runTimePermission()) {
                } else {
                    openMediaDialog(); // upload file option
                }
                break;
        }
    }

    private void validationOnUploadFile() {


        if (intent_From.equalsIgnoreCase("extras")) {

            if (edtUplaodFile.getText().toString().trim().isEmpty()) {
                Toast.makeText(context, "Please upload file", Toast.LENGTH_SHORT).show();
            } else if (edtName.getText().toString().trim().isEmpty()) {
                edtName.setError("Enter your name");
            } else if (edtEmail.getText().toString().trim().isEmpty()) {
                edtEmail.setError("Enter your email");
            } else if (!CommonMethods.isValidEmail(edtEmail.getText().toString())) {
                edtEmail.setError("Enter valid email");
            } else if (edtPhone.getText().toString().trim().isEmpty()) {
                edtPhone.setError("Enter phone number");
            } else if (!CommonMethods.isValidMobile(edtPhone.getText().toString())) {
                edtPhone.setError("Enter phone number");
            } else {
                if (NetworkUtils.isNetworkConnectionAvailable(context)) {
                    dialog = UtilsAlertDialog.ShowDialog(context);
                    ipFormFileUpload.uploadFile(edtName.getText().toString().trim(),
                            edtEmail.getText().toString().trim(),
                            "",
                            edtPhone.getText().toString().trim(),
                            "",
                            imageToUpload,
                            signatureToUpload);
                }
                //file name kaha hai?

            }

        } else {
            if (edtUplaodFile.getText().toString().trim().isEmpty()) {
                Toast.makeText(context, "Please upload file", Toast.LENGTH_SHORT).show();
            }
/*
            else {
                if (NetworkUtils.isNetworkConnectionAvailable(context)) {
                    dialog = UtilsAlertDialog.ShowDialog(context);
                    ipFormFileUpload.uploadFile(edtName.getText().toString().trim(),
                            "",
                            "",
                            "",
                            "",
                            imageToUpload,
                            signatureToUpload);
                }
            }
*/

            Intent intent = new Intent(FormFileUploadActivity.this, AddressActivity.class);
            intent.putExtra("intent_type", "from_file_upload");
            startActivity(intent);

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
                        ActivityCompat.requestPermissions(FormFileUploadActivity.this, requestedPermissions, requestCode);
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

    // upload file option
    private void openMediaDialog() {
        Dialog mediaDialog = new Dialog(FormFileUploadActivity.this);
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
                PickImageDialog.build(new PickSetup().setButtonOrientation(LinearLayout.HORIZONTAL)).show(FormFileUploadActivity.this);
                mediaDialog.dismiss();
            }
        });

        tvUploadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FormFileUploadActivity.this, NormalFilePickActivity.class);
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

    @Override
    public void onPickResult(PickResult result) {
        if (result.getError() == null) {
            String[] parts = result.getPath().split("/");
            final String fileName = parts[parts.length - 1];
            edtUplaodFile.setText(fileName);
            Bitmap bitmap = result.getBitmap();
            sendImageFileToserver(bitmap);
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

        try {
            reqFile = RequestBody.create(
                    MediaType.parse(Files.probeContentType(file.toPath())), file);
        } catch (IOException e) {
            e.printStackTrace();
            reqFile = RequestBody.create(MediaType.parse("*//*"), file);
        }
        StringBuilder builder = new StringBuilder();
        String path = file.getPath();
        builder.append(path + "\n");
        String[] parts = file.getPath().split("/");
        String fileName = parts[parts.length - 1];

        //Extras    upload_file & upload_signature
        if (layoutSignature.getVisibility() == View.VISIBLE)
            imageToUpload = MultipartBody.Part.createFormData("upload_file", fileName, reqFile);
        else
            imageToUpload = MultipartBody.Part.createFormData("file", fileName, reqFile);
        Log.d("multipart++", "multipart++" + imageToUpload);
        return imageToUpload;
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
            reqFile = RequestBody.create(MediaType.parse("image/*"), file);
        }


        signatureToUpload = MultipartBody.Part.createFormData("upload_signature", fileName, reqFile);
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
                String extension = path.substring(path.lastIndexOf(".") + 1);

                // RequestBody requestFiles = RequestBody.create(MediaType.parse("*//*"), path);
                RequestBody requestFiles = null;
                try {
                    requestFiles = RequestBody.create(
                            MediaType.parse(Files.probeContentType(files.toPath())), files);
                } catch (IOException e) {
                    e.printStackTrace();
                    requestFiles = RequestBody.create(MediaType.parse("*//*"), files);
                }

                //requestFiles = RequestBody.create(MediaType.parse("image/*"), files);

                if (layoutSignature.getVisibility() == View.VISIBLE)
                    imageToUpload = MultipartBody.Part.createFormData("upload_file", fileName, requestFiles);
                else
                    imageToUpload = MultipartBody.Part.createFormData("file", fileName, requestFiles);

                Log.d("file++", "file++" + imageToUpload);
            }
            edtUplaodFile.setText(fileName);

        }
    }


    @Override
    public void uploadFileSuccessResponseFromPresenter(UploadFileSubmitResponseModel uploadFileSubmitResponseModel) {
        dialog.dismiss();
        String message = uploadFileSubmitResponseModel.getData().getUpload();
        Toast.makeText(context, "" + message, Toast.LENGTH_SHORT).show();

        if (intent_From.equalsIgnoreCase("extras")) {
            finish();
        } else {
            if (auth_token != null && !auth_token.isEmpty()) {
//                if (address!=null && !address.equals("")){
//                    Intent intent = new Intent(FormFileUploadActivity.this, PaymentActivity.class);
//                    startActivity(intent);
//                }else {
                Intent intent = new Intent(FormFileUploadActivity.this, AddressActivity.class);
                intent.putExtra("intent_type", "from_file_upload");
                startActivity(intent);
                //}

            } else {
                Intent intent = new Intent(FormFileUploadActivity.this, LoginActivity.class);
                intent.putExtra("intent_type", "from_file_upload");
                startActivity(intent);
            }
        }


    }

    @Override
    public void uploadFileErrorResponseFromPresenter(String message) {
        dialog.dismiss();
        String error_message = message;
        Toast.makeText(context, "" + error_message, Toast.LENGTH_SHORT).show();
    }
}