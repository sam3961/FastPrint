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

public class SelectAJobAdapter extends RecyclerView.Adapter<SelectAJobAdapter.ViewHolder> {

    Context context;
    List<String> selectjobList;
    private int mCheckedPostion = -1;// no selection by default

    public SelectAJobAdapter(Context context, List<String> selectjobList) {
        this.context = context;
        this.selectjobList = selectjobList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_select_job, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.chSeletJob.setText(selectjobList.get(position));
        holder.chSeletJob.setChecked(mCheckedPostion == position);

        holder.chSeletJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCheckedPostion = holder.getAdapterPosition();
                notifyDataSetChanged();
                if(mCheckedPostion == position){
                    holder.chSeletJob.setChecked(true);
                    CommercialPrintingActivity.edtSelectJob.setText(selectjobList.get(position));
                    CommercialPrintingActivity.seletJodialog.dismiss();

                }else {
                    holder.chSeletJob.setChecked(false);
                }
            }
        });

    }
    @Override
    public int getItemCount() {
        return selectjobList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.chSeletJob)
        RadioButton chSeletJob;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            chSeletJob.setTypeface(UtilsFontFamily.typeFaceForRobotoRegular(context));
        }
    }
}
