package com.app.fastprint.ui.settings.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fastprint.R;
import com.app.fastprint.ui.AccountInformations.AccountInfoActivity;
import com.app.fastprint.ui.changepassword.ChangePasswordActivity;
import com.app.fastprint.ui.main.MainActivity;
import com.app.fastprint.ui.myorders.MyOrdersActivity;
import com.app.fastprint.ui.notification.NotificationActivity;
import com.app.fastprint.ui.others.OthersActivity;
import com.app.fastprint.ui.settings.SettingsModel;
import com.app.fastprint.utills.UtilsFontFamily;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsAdapter extends RecyclerView.Adapter<SettingsAdapter.ViewHolder> {

    Context context;
    SettingsModel[] settingsModels;

    public SettingsAdapter(Context context, SettingsModel[] settingsModels) {
        this.context = context;
        this.settingsModels = settingsModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_settings, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvName.setText(settingsModels[position].getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            Intent intent = null;

            @Override
            public void onClick(View v) {

                if (settingsModels[position].getName().equalsIgnoreCase("Account info")) {
                    intent = new Intent(context, AccountInfoActivity.class);
                    context.startActivity(intent);


                } else if (settingsModels[position].getName().equalsIgnoreCase("Notifications")) {
                    intent = new Intent(context, NotificationActivity.class);
                    context.startActivity(intent);


                } else if (settingsModels[position].getName().equalsIgnoreCase("My orders")) {
                    intent = new Intent(context, MyOrdersActivity.class);
                    context.startActivity(intent);

                } else if (settingsModels[position].getName().equalsIgnoreCase("Change password")) {
                    intent = new Intent(context, ChangePasswordActivity.class);
                    context.startActivity(intent);


                } else if (settingsModels[position].getName().equalsIgnoreCase("Privacy policy")) {
                    intent = new Intent(context, OthersActivity.class);
                    intent.putExtra("intentType", "privacy_policy");
                    context.startActivity(intent);


                } else if (settingsModels[position].getName().equalsIgnoreCase("Refund policy")) {
                    intent = new Intent(context, OthersActivity.class);
                    intent.putExtra("intentType", "refund_policy");
                    context.startActivity(intent);

                } else if (settingsModels[position].getName().equalsIgnoreCase("Terms and conditions")) {
                    intent = new Intent(context, OthersActivity.class);
                    intent.putExtra("intentType", "terms_and_conditions");
                    context.startActivity(intent);

                } else if (settingsModels[position].getName().equalsIgnoreCase("Logout")) {
                    logoutAccount();
                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return settingsModels.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvName)
        TextView tvName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            tvName.setTypeface(UtilsFontFamily.typeFaceForRobotoRegular(context));
        }
    }

    private void logoutAccount() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.DialogeTheme);
        builder.setCancelable(true);
        builder.setMessage("Are you sure you want to logout?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(context, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
