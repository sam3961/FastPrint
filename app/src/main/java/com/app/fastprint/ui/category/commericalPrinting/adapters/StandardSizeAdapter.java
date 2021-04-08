package com.app.fastprint.ui.category.commericalPrinting.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fastprint.R;
import com.app.fastprint.ui.category.commericalPrinting.CommercialPrintingActivity;
import com.app.fastprint.utills.UtilsFontFamily;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StandardSizeAdapter extends RecyclerView.Adapter<StandardSizeAdapter.ViewHolder> {

    Context context;
    List<String> jobsList;
    private int mCheckedPostion = -1;// no selection by default


    public StandardSizeAdapter(Context context, List<String> jobsList) {
        this.context = context;
        this.jobsList = jobsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_standard_size, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.chstandardSize.setChecked(position == mCheckedPostion);
        holder.chstandardSize.setText(jobsList.get(position));
        holder.chstandardSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position == mCheckedPostion) {
                    holder.chstandardSize.setChecked(false);
                    mCheckedPostion = -1;
                } else {
                    mCheckedPostion = position;
                    notifyDataSetChanged();
                    CommercialPrintingActivity.edtJob.setText(jobsList.get(position));
                    CommercialPrintingActivity.standardDialog.dismiss();
                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return jobsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.chstandardSize)
        RadioButton chstandardSize;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            chstandardSize.setTypeface(UtilsFontFamily.typeFaceForRobotoRegular(context));
        }
    }
}
