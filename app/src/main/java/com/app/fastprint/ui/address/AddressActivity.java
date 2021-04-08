package com.app.fastprint.ui.address;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.app.fastprint.R;
import com.app.fastprint.baseClass.BaseClass;
import com.app.fastprint.networks.NetworkUtils;
import com.app.fastprint.ui.address.interfaces.IAddress;
import com.app.fastprint.ui.address.interfaces.IPAddress;
import com.app.fastprint.ui.address.presenter.PAddress;
import com.app.fastprint.ui.address.responseModel.BillingAddressResponseModel;
import com.app.fastprint.ui.main.MainActivity;
import com.app.fastprint.ui.payment.PaymentActivity;
import com.app.fastprint.ui.payment.paymentResponseModel.GetAddressResponseModel;
import com.app.fastprint.utills.AppControler;
import com.app.fastprint.utills.UtilsAlertDialog;
import com.app.fastprint.utills.UtilsFontFamily;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddressActivity extends BaseClass implements IAddress {

    @BindView(R.id.tvTittle)
    TextView tvTittle;

    @BindView(R.id.editFirstName)
    EditText editFirstName;
    @BindView(R.id.editShipFirstName)
    EditText editShipFirstName;

    @BindView(R.id.editLastName)
    EditText editLastName;
    @BindView(R.id.editShipLastName)
    EditText editShipLastName;

    @BindView(R.id.editAddressLine1)
    EditText editAddressLine1;
    @BindView(R.id.editShipAddressLine1)
    EditText editShipAddressLine1;

    @BindView(R.id.editAddressLine2)
    EditText editAddressLine2;
    @BindView(R.id.editShipAddressLine2)
    EditText editShipAddressLine2;

    @BindView(R.id.editCountry)
    EditText editCountry;
    @BindView(R.id.editShipCountry)
    EditText editShipCountry;

    @BindView(R.id.editState)
    EditText editState;
    @BindView(R.id.editShipState)
    EditText editShipState;

    @BindView(R.id.editCity)
    EditText editCity;
    @BindView(R.id.editShipCity)
    EditText editShipCity;

    @BindView(R.id.editZip)
    EditText editZip;
    @BindView(R.id.editShipZip)
    EditText editShipZip;

    @BindView(R.id.tvSave)
    TextView tvSave;

    @BindView(R.id.relativeLayoutShipMain)
    RelativeLayout relativeLayoutShipMainView;

    @BindView(R.id.relativeLayoutShip)
    RelativeLayout relativeLayoutShip;

    @BindView(R.id.relativeLayoutShipTextFields)
    RelativeLayout relativeLayoutShipTextFields;

    @BindView(R.id.relativeLayoutBillingAddress)
    RelativeLayout relativeLayoutBillingAddress;

    @BindView(R.id.scrollView)
    ScrollView scrollView;

    @BindView(R.id.switchShip)
    Switch switchShip;

    IPAddress ipAddress;
    Context context;
    Dialog dialog;
    String user_id = "";
    String first_name = "";
    String last_name = "";
    String email = "";
    String phone_number = "";
    String intent_type = "";
    String address = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        ButterKnife.bind(this);
        context = AddressActivity.this;
        dialog = UtilsAlertDialog.progressDialog(this);

        ipAddress = new PAddress(this);
        intent_type = getIntent().getStringExtra("intent_type");
        address = AppControler.getInstance(context).getString(AppControler.Key.ADDRESS);
        user_id = AppControler.getInstance().getString(AppControler.Key.USER_ID);
        first_name = AppControler.getInstance().getString(AppControler.Key.USER_FIRST_NAME);
        last_name = AppControler.getInstance().getString(AppControler.Key.USER_LAST_NAME);
        email = AppControler.getInstance().getString(AppControler.Key.USER_EMAIL);
        phone_number = AppControler.getInstance().getString(AppControler.Key.USER_PHONE);
        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoBold(this));
        tvSave.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));
      //  editFirstName.setText(first_name);
      //  editLastName.setText(last_name);

/*        if (address != null && !address.equals("")) {
            relativeLayoutShipMainView.setVisibility(View.VISIBLE);
            tvSave.setText("Proceed to payment");
//            editFirstName.setText(first_name);
//            editLastName.setText(last_name);
//            editAddressLine1.setText(address);
        } else {
            relativeLayoutShipMainView.setVisibility(View.GONE);
            tvSave.setText("Save & Continue");
        }*/

        switchListener();

        fetchBillingAddress();
    }

    private void fetchBillingAddress() {
        dialog.show();
        ipAddress.getBillingAddress(user_id);
    }

    private void switchListener() {
        switchShip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    relativeLayoutShipTextFields.setVisibility(View.VISIBLE);
                    editAddressLine1.setEnabled(false);
                    editAddressLine2.setEnabled(false);
                    editCity.setEnabled(false);
                    editCountry.setEnabled(false);
                    editState.setEnabled(false);
                    editFirstName.setEnabled(false);
                    editLastName.setEnabled(false);
                    editZip.setEnabled(false);
                    scrollView.postDelayed(() -> scrollView.smoothScrollTo(0, scrollView.getHeight()), 200);
                } else {
                    relativeLayoutShipTextFields.setVisibility(View.GONE);
                    editAddressLine1.setEnabled(true);
                    editAddressLine2.setEnabled(true);
                    editCity.setEnabled(true);
                    editCountry.setEnabled(true);
                    editState.setEnabled(true);
                    editFirstName.setEnabled(true);
                    editLastName.setEnabled(true);
                    editZip.setEnabled(true);
                }
            }
        });
    }

    @OnClick({R.id.tvSave, R.id.llSave})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.llSave:
            case R.id.tvSave:
                if (switchShip.isChecked())
                    validationShipOnAddress();
                else
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
                dialog.show();
                ipAddress.addBillingAddress(user_id, editFirstName.getText().toString().trim(), editLastName.getText().toString().trim(),
                        editAddressLine1.getText().toString().trim(),
                        editAddressLine2.getText().toString().trim(), editCity.getText().toString().trim(), editZip.getText().toString().trim(),
                        editCity.getText().toString().trim(), editState.getText().toString().trim());
            }
        }
    }

    private void validationShipOnAddress() {

        if (editShipFirstName.getText().toString().trim().isEmpty()) {
            editShipFirstName.setError("Enter your name");
        } else if (editShipFirstName.length() < 3 || editShipFirstName.length() > 15) {
            editShipFirstName.setError("name should be between 3 to 15 characters");
        } else if (editShipLastName.getText().toString().trim().isEmpty()) {
            editShipLastName.setError("Enter your name");
        } else if (editShipLastName.length() < 3 || editShipLastName.length() > 15) {
            editShipLastName.setError("last name should be between 3 to 15 characters");
        } else if (editShipAddressLine1.getText().toString().trim().isEmpty()) {
            editShipAddressLine1.setError("Enter address ");
        } else if (editShipCountry.getText().toString().trim().isEmpty()) {
            editShipCountry.setError("Enter country name");
        } else if (editShipState.getText().toString().trim().isEmpty()) {
            editShipState.setError("Enter state name");
        } else if (editShipCity.getText().toString().trim().isEmpty()) {
            editShipCity.setError("Enter city name");
        } else if (editShipZip.getText().toString().trim().isEmpty()) {
            editShipZip.setError("Enter zip code");
        } else {
            if (NetworkUtils.isNetworkConnectionAvailable(context)) {
                dialog.show();
                ipAddress.addBillingAddress(user_id,
                        editShipFirstName.getText().toString().trim(),
                        editShipLastName.getText().toString().trim(),
                        editShipAddressLine1.getText().toString().trim(),
                        editShipAddressLine2.getText().toString().trim(),
                        editShipCity.getText().toString().trim(),
                        editShipZip.getText().toString().trim(),
                        editShipCity.getText().toString().trim(),
                        editShipState.getText().toString().trim());
            }
        }
    }

    @Override
    public void successResponseFromPresenter(BillingAddressResponseModel billingAddressResponseModel) {
        dialog.dismiss();

        if (address == null || address.isEmpty())
            AppControler.getInstance(context).put(AppControler.Key.ADDRESS, editAddressLine1.getText().toString());

        if (intent_type.equalsIgnoreCase("from_file_upload")) {
            Intent intent = new Intent(AddressActivity.this, PaymentActivity.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(AddressActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        Toast.makeText(this, "" + billingAddressResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void errorResponseFromPresenter(String message) {
        dialog.dismiss();
        Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void errorAddressResponseFromPresenter(String message) {
        dialog.dismiss();
        Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void successAddressResponseFromPresenter(GetAddressResponseModel getAddressResponseModel) {
        dialog.dismiss();
        if (getAddressResponseModel.getData() != null && (getAddressResponseModel.getData().getAddress1() != null ||
                !getAddressResponseModel.getData().getAddress1().isEmpty())) {
            editFirstName.setText(getAddressResponseModel.getData().getFirstName());
            editLastName.setText(getAddressResponseModel.getData().getLastName());
            editZip.setText(getAddressResponseModel.getData().getPostCode());
            editAddressLine1.setText(getAddressResponseModel.getData().getAddress1());
            editAddressLine2.setText(getAddressResponseModel.getData().getAddress2());
            editCity.setText(getAddressResponseModel.getData().getCityName());
            editCountry.setText(getAddressResponseModel.getData().getCountryName());
            editState.setText(getAddressResponseModel.getData().getStateName());

            relativeLayoutShipMainView.setVisibility(View.VISIBLE);
            tvSave.setText("Proceed to payment");

        } else {
            relativeLayoutShipMainView.setVisibility(View.GONE);
            tvSave.setText("Save & Continue");
        }
    }
}
