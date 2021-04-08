package com.app.fastprint.ui.category.commericalPrinting.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fastprint.R;
import com.app.fastprint.ui.category.commericalPrinting.CommercialPrintingActivity;
import com.app.fastprint.utills.UtilsFontFamily;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NumberOfColorAdapter extends RecyclerView.Adapter<NumberOfColorAdapter.ViewHolder> {

    Context context;
    List<String> numberofColorList;
    private int mCheckedPostion = -1;// no selection by default



    public NumberOfColorAdapter(Context context, List<String> numberofColorList) {
        this.context = context;
        this.numberofColorList = numberofColorList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_number_of_color, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.chNumberOfColor.setChecked(position == mCheckedPostion);
        holder.chNumberOfColor.setText(numberofColorList.get(position));
        holder.chNumberOfColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position == mCheckedPostion) {
                    holder.chNumberOfColor.setChecked(false);
                    mCheckedPostion = -1;
                } else {
                    mCheckedPostion = position;
                    notifyDataSetChanged();
                    CommercialPrintingActivity.edtNumberOfColor.setText(numberofColorList.get(position));
                    CommercialPrintingActivity.numberofColordialog.dismiss();
                }
            }



        });
    }

    @Override
    public int getItemCount() {
        return numberofColorList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.chNumberOfColor)
        RadioButton chNumberOfColor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            chNumberOfColor.setTypeface(UtilsFontFamily.typeFaceForRobotoRegular(context));
        }
    }
}
