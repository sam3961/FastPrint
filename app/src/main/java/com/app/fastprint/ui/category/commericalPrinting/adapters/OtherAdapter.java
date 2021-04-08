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

public class OtherAdapter extends RecyclerView.Adapter<OtherAdapter.ViewHolder> {

    Context context;
    List<String> numberofSheetList;
    private int mCheckedPostion = -1;// no selection by default

    public OtherAdapter(Context context, List<String> numberofSheetList) {
        this.context = context;
        this.numberofSheetList = numberofSheetList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_other, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.chOthers.setChecked(position == mCheckedPostion);
        holder.chOthers.setText(numberofSheetList.get(position));

        holder.chOthers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position == mCheckedPostion) {
                    holder.chOthers.setChecked(false);
                    mCheckedPostion = -1;
                } else {
                    mCheckedPostion = position;
                    notifyDataSetChanged();
                    CommercialPrintingActivity.edtNumberofSheet.setText(numberofSheetList.get(position));
                    CommercialPrintingActivity.otherDialog.dismiss();

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return numberofSheetList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.chOthers)
        RadioButton chOthers;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            chOthers.setTypeface(UtilsFontFamily.typeFaceForRobotoRegular(context));
        }
    }
}
