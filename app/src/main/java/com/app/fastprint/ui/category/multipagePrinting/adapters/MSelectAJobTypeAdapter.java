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

public class MSelectAJobTypeAdapter extends RecyclerView.Adapter<MSelectAJobTypeAdapter.ViewHolder> {

    Context context;
    List<String> mselectjobtypeList;
    private int mCheckedPostion = -1;// no selection by default

    public MSelectAJobTypeAdapter(Context context, List<String> mselectjobtypeList) {
        this.context = context;
        this.mselectjobtypeList = mselectjobtypeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_m_select_job_type, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.chMSeletJobType.setChecked(position == mCheckedPostion);
        holder.chMSeletJobType.setText(mselectjobtypeList.get(position));
        holder.chMSeletJobType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position == mCheckedPostion) {
                    holder.chMSeletJobType.setChecked(false);
                    mCheckedPostion = -1;
                } else {
                    mCheckedPostion = position;
                    notifyDataSetChanged();
                //    MultiPagePrintingActivity.edtSelectJobType.setText(mselectjobtypeList.get(position));
                    MultiPagePrintingActivity.mseletJoTypedialog.dismiss();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mselectjobtypeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.chMSeletJobType)
        RadioButton chMSeletJobType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            chMSeletJobType.setTypeface(UtilsFontFamily.typeFaceForRobotoRegular(context));
        }
    }
}
