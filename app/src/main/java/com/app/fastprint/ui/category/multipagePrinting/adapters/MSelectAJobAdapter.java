package com.app.fastprint.ui.category.multipagePrinting.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fastprint.R;
import com.app.fastprint.ui.category.multipagePrinting.MultiPagePrintingActivity;
import com.app.fastprint.utills.UtilsFontFamily;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MSelectAJobAdapter extends RecyclerView.Adapter<MSelectAJobAdapter.ViewHolder> {

    Context context;
    List<String> mselectjobList;
    private int mCheckedPostion = -1;// no selection by default

    public MSelectAJobAdapter(Context context, List<String> mselectjobList) {
        this.context = context;
        this.mselectjobList = mselectjobList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_m_select_job, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.chMSeletJob.setText(mselectjobList.get(position));
        holder.chMSeletJob.setChecked(position == mCheckedPostion);

        holder.chMSeletJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position == mCheckedPostion) {
                    holder.chMSeletJob.setChecked(false);
                    mCheckedPostion = -1;
                } else {
                    mCheckedPostion = position;
                    notifyDataSetChanged();

                    MultiPagePrintingActivity.edtSelectJob.setText(mselectjobList.get(position));
                    MultiPagePrintingActivity.mseletJodialog.dismiss();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mselectjobList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.chMSeletJob)
        RadioButton chMSeletJob;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            chMSeletJob.setTypeface(UtilsFontFamily.typeFaceForRobotoRegular(context));
        }
    }
}
