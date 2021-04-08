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
import com.app.fastprint.utills.UtilsFontFamily;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MStandardSizeAdapter extends RecyclerView.Adapter<MStandardSizeAdapter.ViewHolder> {

    Context context;
    List<String> mstandardSizeList;
    private int mCheckedPostion = -1;// no selection by default


    public MStandardSizeAdapter(Context context, List<String> mstandardSizeList) {
        this.context = context;
        this.mstandardSizeList = mstandardSizeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_m_standred_size, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.chmStandredSize.setChecked(position == mCheckedPostion);
        holder.chmStandredSize.setText(mstandardSizeList.get(position));
        holder.chmStandredSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position == mCheckedPostion) {
                    holder.chmStandredSize.setChecked(false);
                    mCheckedPostion = -1;
                } else {
                    mCheckedPostion = position;
                    notifyDataSetChanged();
                    /*MultiPagePrintingActivity.edtStandredSize.setText(mstandardSizeList.get(position));
                    MultiPagePrintingActivity.mstandardDialog.dismiss();*/
                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return mstandardSizeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.chmStandredSize)
        RadioButton chmStandredSize;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            chmStandredSize.setTypeface(UtilsFontFamily.typeFaceForRobotoRegular(context));
        }
    }
}
