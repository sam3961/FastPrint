package com.app.fastprint.ui.uploadfile;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fastprint.R;
import com.app.fastprint.baseClass.BaseClass;
import com.app.fastprint.networks.NetworkUtils;
import com.app.fastprint.ui.login.LoginActivity;
import com.app.fastprint.ui.uploadfile.adapters.UploadAdapter;
import com.app.fastprint.ui.uploadfile.interfaces.IPUploadFile;
import com.app.fastprint.ui.uploadfile.interfaces.IUploadFile;
import com.app.fastprint.ui.uploadfile.presenter.PUploadFile;
import com.app.fastprint.ui.uploadfile.responnseModel.UploadFileResponseModel;
import com.app.fastprint.utills.UtilsAlertDialog;
import com.app.fastprint.utills.UtilsFontFamily;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UploadFileActivity extends BaseClass implements IUploadFile {

    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.tvTittle)
    TextView tvTittle;
    @BindView(R.id.searchView)
    SearchView searchView;
    @BindView(R.id.recyclerUploadFile)
    RecyclerView recyclerUploadFile;
    @BindView(R.id.tvUploadFile)
    TextView tvUploadFile;
    UploadAdapter uploadAdapter;
    Context context;
    Dialog dialog;
    IPUploadFile ipUploadFile;
    String intent_From = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_file);
        context = UploadFileActivity.this;
        ButterKnife.bind(this);
        intent_From = getIntent().getStringExtra("intent_From");
        ipUploadFile = new PUploadFile(this);
        tvUploadFile.setTypeface(UtilsFontFamily.typeFaceForRobotoBold(context));
        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));

        if (intent_From.equalsIgnoreCase("extras")){
            tvUploadFile.setVisibility(View.GONE);
            recyclerUploadFile.setVisibility(View.GONE);

         }else {
            tvUploadFile.setVisibility(View.GONE);
            recyclerUploadFile.setVisibility(View.GONE);
        }

        if (NetworkUtils.isNetworkConnectionAvailable(context)) {
            dialog = UtilsAlertDialog.ShowDialog(context);
            ipUploadFile.uploadFileList();
        }

    }

    @OnClick({R.id.imgBack, R.id.tvUploadFile})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.tvUploadFile:
                Intent intent = new Intent(UploadFileActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void successResponseFromPresenter(UploadFileResponseModel uploadFileResponseModel) {
        dialog.dismiss();
        if (intent_From.equalsIgnoreCase("extras")){
            tvUploadFile.setVisibility(View.GONE);
            recyclerUploadFile.setVisibility(View.VISIBLE);

        }else {
            tvUploadFile.setVisibility(View.VISIBLE);
            recyclerUploadFile.setVisibility(View.VISIBLE);

        }

        if (uploadFileResponseModel != null) {
            updateListUI(uploadFileResponseModel);
        }

    }

    private void updateListUI(UploadFileResponseModel uploadFileResponseModel) {

        uploadAdapter = new UploadAdapter(this, uploadFileResponseModel.getData().getUploadFile());
        recyclerUploadFile.setLayoutManager(new LinearLayoutManager(this));
        recyclerUploadFile.setAdapter(uploadAdapter);
    }

    @Override
    public void errorResponseFromPresenter(String message) {
        dialog.dismiss();
        if (intent_From.equalsIgnoreCase("extras")){
            tvUploadFile.setVisibility(View.GONE);
            recyclerUploadFile.setVisibility(View.GONE);

        }else {
            tvUploadFile.setVisibility(View.GONE);
            recyclerUploadFile.setVisibility(View.GONE);

        }
        String error_message = message;
        Toast.makeText(context, "" + error_message, Toast.LENGTH_SHORT).show();
    }
}
