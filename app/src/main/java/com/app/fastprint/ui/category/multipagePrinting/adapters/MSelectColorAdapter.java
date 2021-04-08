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

public class MSelectColorAdapter extends RecyclerView.Adapter<MSelectColorAdapter.ViewHolder> {

    Context context;
    List<String> mselectColorList;
    private int mCheckedPostion = -1;// no selection by default


    public MSelectColorAdapter(Context context, List<String> mselectColorList) {
        this.context = context;
        this.mselectColorList = mselectColorList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_m_color_code, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.chMColorCode.setChecked(position == mCheckedPostion);
        holder.chMColorCode.setText(mselectColorList.get(position));
        holder.chMColorCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position == mCheckedPostion) {
                    holder.chMColorCode.setChecked(false);
                    mCheckedPostion = -1;
                } else {
                    mCheckedPostion = position;
                    notifyDataSetChanged();
                    MultiPagePrintingActivity.edtColorCode.setText(mselectColorList.get(position));
                    MultiPagePrintingActivity.mseletColordialog.dismiss();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return mselectColorList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.chMColorCode)
        RadioButton chMColorCode;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            chMColorCode.setTypeface(UtilsFontFamily.typeFaceForRobotoRegular(context));
        }
    }
}
