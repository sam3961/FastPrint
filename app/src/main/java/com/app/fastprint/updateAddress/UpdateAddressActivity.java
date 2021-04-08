package com.app.fastprint.updateAddress;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.fastprint.R;
import com.app.fastprint.baseClass.BaseClass;
import com.app.fastprint.networks.NetworkUtils;
import com.app.fastprint.ui.address.responseModel.BillingAddressResponseModel;
import com.app.fastprint.updateAddress.interfaces.IPUpdateAddress;
import com.app.fastprint.updateAddress.interfaces.IUpdateAddress;
import com.app.fastprint.updateAddress.presenter.PUpdateAddress;
import com.app.fastprint.utills.AppControler;
import com.app.fastprint.utills.UtilsAlertDialog;
import com.app.fastprint.utills.UtilsFontFamily;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateAddressActivity extends BaseClass implements IUpdateAddress {

    @BindView(R.id.tvTittle)
    TextView tvTittle;

    @BindView(R.id.imgBack)
    ImageView imgBack;

    @BindView(R.id.editFirstName)
    EditText editFirstName;

    @BindView(R.id.editLastName)
    EditText editLastName;

    @BindView(R.id.editAddressLine1)
    EditText editAddressLine1;

    @BindView(R.id.editAddressLine2)
    EditText editAddressLine2;

    @BindView(R.id.editCountry)
    EditText editCountry;

    @BindView(R.id.editState)
    EditText editState;

    @BindView(R.id.editCity)
    EditText editCity;

    @BindView(R.id.editZip)
    EditText editZip;

    @BindView(R.id.tvUpdate)
    TextView tvUpdate;

    Context context;
    String user_id = "";
    String first_name = "";
    String last_name = "";
    String address_1 = "";
    String address_2 = "";
    String post_code = "";
    String city_name = "";
    String state_name = "";
    String country_name = "";
    Dialog dialog;
    IPUpdateAddress ipUpdateAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_address);
        ButterKnife.bind(this);
        context = UpdateAddressActivity.this;
        ipUpdateAddress=new PUpdateAddress(this);
        user_id = AppControler.getInstance(context).getString(AppControler.Key.USER_ID);
        first_name=getIntent().getStringExtra("first_name");
        last_name=getIntent().getStringExtra("last_name");
        address_1=getIntent().getStringExtra("address_1");
        address_2=getIntent().getStringExtra("address_2");
        post_code=getIntent().getStringExtra("post_code");
        city_name=getIntent().getStringExtra("city_name");
        state_name=getIntent().getStringExtra("state_name");
        country_name=getIntent().getStringExtra("country_name");
        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoBold(this));
        tvUpdate.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));

        editFirstName.setText(first_name);
        editLastName.setText(last_name);
        editAddressLine1.setText(address_1);
        editAddressLine2.setText(address_2);
        editCountry.setText(country_name);
        editState.setText(state_name);
        editCity.setText(city_name);
        editZip.setText(post_code);
    }

    @OnClick({R.id.imgBack, R.id.tvSave,})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.tvSave:
                validationOnAddress();
                break;
        }
    }

    private void validationOnAddress() {

        if (editFirstName.getText().toString().trim().isEmpty()) {
            editFirstName.setError("Enter your name");
        } else if (editFirstName.length() < 3 || editFirstName.length() > 15) {
            editFirstName.setError("name should be between 3 to 15 characters");
        } else if (editLastName.getText().toString().trim().isEmpty()) {
            editLastName.setError("Enter your name");
        } else if (editLastName.length() < 3 || editLastName.length() > 15) {
            editLastName.setError("last name should be between 3 to 15 characters");
        } else if (editAddressLine1.getText().toString().trim().isEmpty()) {
            editAddressLine1.setError("Enter address ");
        } else if (editCountry.getText().toString().trim().isEmpty()) {
            editCountry.setError("Enter country name");
        } else if (editState.getText().toString().trim().isEmpty()) {
            editState.setError("Enter state name");
        } else if (editCity.getText().toString().trim().isEmpty()) {
            editCity.setError("Enter city name");
        } else if (editZip.getText().toString().trim().isEmpty()) {
            editZip.setError("Enter zip code");
        } else {
            if (NetworkUtils.isNetworkConnectionAvailable(context)) {
                dialog = UtilsAlertDialog.ShowDialog(context);
                ipUpdateAddress.addBillingAddress(user_id, editFirstName.getText().toString().trim(), editLastName.getText().toString().trim(),
                        editAddressLine1.getText().toString().trim(),
                        editAddressLine2.getText().toString().trim(), editCity.getText().toString().trim(), editZip.getText().toString().trim(),
                        editCity.getText().toString().trim(), editState.getText().toString().trim());
            }
        }
    }

    @Override
    public void successResponseFromPresenter(BillingAddressResponseModel billingAddressResponseModel) {
        dialog.dismiss();
        finish();
        Toast.makeText(this, "" + billingAddressResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void errorResponseFromPresenter(String message) {
        Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();
    }
}