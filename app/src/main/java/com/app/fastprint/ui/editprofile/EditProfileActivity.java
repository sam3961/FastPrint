package com.app.fastprint.ui.editprofile;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
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

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.app.fastprint.R;
import com.app.fastprint.baseClass.BaseClass;
import com.app.fastprint.networks.NetworkUtils;
import com.app.fastprint.ui.editprofile.interfaces.IEditProfile;
import com.app.fastprint.ui.editprofile.interfaces.IPEditProfile;
import com.app.fastprint.ui.editprofile.presenter.PEditProfile;
import com.app.fastprint.ui.editprofile.responseModel.UpdateProfileResponseModel;
import com.app.fastprint.utills.AppControler;
import com.app.fastprint.utills.CommonMethods;
import com.app.fastprint.utills.UtilsAlertDialog;
import com.app.fastprint.utills.UtilsFontFamily;
import com.google.android.material.snackbar.Snackbar;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class EditProfileActivity extends BaseClass implements IPickResult, IEditProfile {

    @BindView(R.id.imgBack)
    ImageView imgBack;

    @BindView(R.id.tvTittle)
    TextView tvTittle;

    @BindView(R.id.imgUser)
    CircleImageView imgUser;

    @BindView(R.id.imgOpenMedia)
    ImageView imgOpenMedia;

    @BindView(R.id.editFirstName)
    EditText editFirstName;

    @BindView(R.id.editLastName)
    EditText editLastName;

    @BindView(R.id.editEmail)
    EditText editEmail;

    @BindView(R.id.editPhone)
    EditText editPhone;

    @BindView(R.id.tvupdate)
    TextView tvupdate;

    private SparseIntArray mErrorString;
    private static final int REQUEST_PERMISSIONS = 20;
    MultipartBody.Part imageToUpload;
    RequestBody imageRequest;
    Context context;
    String user_first_name="";
    String user_image="";
    String user_phone="";
    String user_email="";
    String user_last_name="";
    Dialog dialog;
    String auth_token="";
    IPEditProfile ipEditProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);
        context=EditProfileActivity.this;
        ipEditProfile=new PEditProfile(this);
        user_first_name= AppControler.getInstance(context).getString(AppControler.Key.USER_FIRST_NAME);
        user_last_name= AppControler.getInstance(context).getString(AppControler.Key.USER_LAST_NAME);
        user_image= AppControler.getInstance(context).getString(AppControler.Key.USER_IMAGE);
        user_phone= AppControler.getInstance(context).getString(AppControler.Key.USER_PHONE);
        user_email= AppControler.getInstance(context).getString(AppControler.Key.USER_EMAIL);
        auth_token= AppControler.getInstance(context).getString(AppControler.Key.AUTH_TOKEN);

        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));
        tvupdate.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));

        if (user_image != null) {
            Glide.with(context).load(user_image)
                    .placeholder(R.drawable.ic_refresh)
                    .into(imgUser);
        } else {
            Glide.with(context).load(user_image)
                    .placeholder(R.drawable.ic_refresh)
                    .into(imgUser);
        }

        editFirstName.setText(user_first_name);
        editLastName.setText(user_last_name);
        editPhone.setText(user_phone);
        editEmail.setText(user_email);
    }

    @OnClick({R.id.imgBack, R.id.tvupdate, R.id.imgOpenMedia})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.tvupdate:
                validationOnUpdateProfile();
                break;
            case R.id.imgOpenMedia:
                if (runTimePermission()) {
                } else {
                    PickImageDialog.build(new PickSetup().setButtonOrientation(LinearLayout.HORIZONTAL)).show(EditProfileActivity.this);

                }
                break;
        }
    }

    private void validationOnUpdateProfile() {

        if (editFirstName.getText().toString().trim().isEmpty()) {
            editFirstName.setError("Enter your name");
        } else if (editFirstName.length() < 3 || editFirstName.length() > 15) {
            editFirstName.setError("name should be between 3 to 15 characters");
        } else if (editLastName.getText().toString().trim().isEmpty()) {
            editLastName.setError("name should be between 3 to 15 characters");
        } else if (editLastName.length() < 3 || editLastName.length() > 15) {
            editLastName.setError("last name should be between 3 to 15 characters");
        } else if (editEmail.getText().toString().trim().isEmpty()) {
            editEmail.setError("Enter your email");
        } else if (!CommonMethods.isValidEmail(editEmail.getText().toString())) {
            editEmail.setError("Enter valid email");
        } else if (editPhone.getText().toString().trim().isEmpty()) {
            editPhone.setError("Enter phone number");
        } else if (!CommonMethods.isValidMobile(editPhone.getText().toString())) {
            editPhone.setError("Enter valid phone number");
        }else {
            if (NetworkUtils.isNetworkConnectionAvailable(context)) {
                dialog = UtilsAlertDialog.ShowDialog(context);
                ipEditProfile.updateProfile(auth_token,editFirstName.getText().toString().trim(),
                        editLastName.getText().toString().trim(),editEmail.getText().toString().trim(),
                        editPhone.getText().toString().trim(),imageRequest,imageToUpload);
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
                        ActivityCompat.requestPermissions(EditProfileActivity.this, requestedPermissions, requestCode);
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

        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part imageMultipart = MultipartBody.Part.createFormData("image", file.getName(), reqFile);
        imageRequest = RequestBody.create(MediaType.parse("text/plain"), "image");
        Log.d("multipart++", "multipart++" + imageToUpload);
        return imageMultipart;
    }

    @Override
    public void onPickResult(PickResult result) {
        if (result.getError() == null) {
            Bitmap bitmap = result.getBitmap();
            imgUser.setImageBitmap(bitmap);
            imageToUpload =sendImageFileToserver(bitmap);
        }
    }

    @Override
    public void successResponseFromPresenter(UpdateProfileResponseModel updateProfileResponseModel) {
        dialog.dismiss();
        Toast.makeText(context, ""+updateProfileResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
        
        if (updateProfileResponseModel.getData()!=null){
            if (updateProfileResponseModel.getData().getFirstName() != null) {
                String user_first_name = updateProfileResponseModel.getData().getFirstName();
                AppControler.getInstance().put(AppControler.Key.USER_FIRST_NAME, user_first_name);
            }
            if (updateProfileResponseModel.getData().getLastName() != null) {
                String user_last_name = updateProfileResponseModel.getData().getLastName();
                AppControler.getInstance().put(AppControler.Key.USER_LAST_NAME, user_last_name);
            }
            if (updateProfileResponseModel.getData().getEmail() != null) {
                String user_email = updateProfileResponseModel.getData().getEmail();
                AppControler.getInstance().put(AppControler.Key.USER_EMAIL, user_email);
            }
            if (updateProfileResponseModel.getData().getPhoneNumber() != null) {
                String user_phone = updateProfileResponseModel.getData().getPhoneNumber();
                AppControler.getInstance().put(AppControler.Key.USER_PHONE, user_phone);
            }

            if (updateProfileResponseModel.getData().getUserImage() != null) {
                String user_image = updateProfileResponseModel.getData().getUserImage();
                AppControler.getInstance().put(AppControler.Key.USER_IMAGE, user_image);
            }
            finish();
        }

    }

    @Override
    public void errorResponseFromPresenter(String message) {
        dialog.dismiss();
        Toast.makeText(context, ""+message, Toast.LENGTH_SHORT).show();
    }
}