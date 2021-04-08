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

public class SelectColorAdapter extends RecyclerView.Adapter<SelectColorAdapter.ViewHolder> {

    Context context;
    List<String> selectColorList;
    private int mCheckedPostion = -1;// no selection by default


    public SelectColorAdapter(Context context, List<String> selectColorList) {
        this.context = context;
        this.selectColorList = selectColorList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_select_color, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.chColor.setChecked(position == mCheckedPostion);
        holder.chColor.setText(selectColorList.get(position));
        holder.chColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == mCheckedPostion) {
                    holder.chColor.setChecked(false);
                    mCheckedPostion = -1;
                } else {
                    mCheckedPostion = position;
                    notifyDataSetChanged();
                    CommercialPrintingActivity.edtColorCode.setText(selectColorList.get(position));
                    CommercialPrintingActivity.seletColordialog.dismiss();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return selectColorList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.chColor)
        RadioButton chColor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            chColor.setTypeface(UtilsFontFamily.typeFaceForRobotoRegular(context));
        }
    }
}
