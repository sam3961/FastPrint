package com.app.fastprint.ui.category.multipagePrinting.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fastprint.R;
import com.app.fastprint.ui.category.multipagePrinting.MultiPagePrintingActivity;
import com.app.fastprint.utills.UtilsFontFamily;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MNumberOfColorAdapter extends RecyclerView.Adapter<MNumberOfColorAdapter.ViewHolder> {

    Context context;
    List<String> mnumberofColorList;
    private int mCheckedPostion = -1;// no selection by default

    public MNumberOfColorAdapter(Context context, List<String> mnumberofColorList) {
        this.context = context;
        this.mnumberofColorList = mnumberofColorList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_m_number_of_color, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.chNumberOfColor.setChecked(position == mCheckedPostion);
        holder.chNumberOfColor.setText(mnumberofColorList.get(position));
        holder.chNumberOfColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position == mCheckedPostion) {
                    holder.chNumberOfColor.setChecked(false);
                    mCheckedPostion = -1;
                } else {
                    mCheckedPostion = position;
                    notifyDataSetChanged();
                    MultiPagePrintingActivity.edtNumberOfColor.setText(mnumberofColorList.get(position));
                    MultiPagePrintingActivity.mnumberofColordialog.dismiss();
                }
            }



        });
    }

    @Override
    public int getItemCount() {
        return mnumberofColorList.size();
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
