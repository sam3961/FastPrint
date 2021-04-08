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

public class MSizeAdapter extends RecyclerView.Adapter<MSizeAdapter.ViewHolder> {

    Context context;
    List<String> msizeList;
    private int mCheckedPostion = -1;// no selection by default

    public MSizeAdapter(Context context, List<String> msizeList) {
        this.context = context;
        this.msizeList = msizeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_m_size, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.chmSize.setChecked(position == mCheckedPostion);
        holder.chmSize.setText(msizeList.get(position));
        holder.chmSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (position == mCheckedPostion) {
                    holder.chmSize.setChecked(false);
                    mCheckedPostion = -1;
                } else {
                    mCheckedPostion = position;
                    notifyDataSetChanged();
                    MultiPagePrintingActivity.edtSize.setText(msizeList.get(position));
                    MultiPagePrintingActivity.msizeDialog.dismiss();

                    String size_name=msizeList.get(position);
                    Toast.makeText(context, ""+size_name, Toast.LENGTH_SHORT).show();

                   /* if (size_name.equalsIgnoreCase("Size")){

                        MultiPagePrintingActivity.edtOther.setVisibility(View.GONE);
                        MultiPagePrintingActivity.viewOther.setVisibility(View.GONE);

                        MultiPagePrintingActivity.edtStandredSize.setVisibility(View.GONE);
                        MultiPagePrintingActivity.viewedtStandredSize.setVisibility(View.GONE);

                    }else if (size_name.equalsIgnoreCase("Standard Size")){
                        MultiPagePrintingActivity.edtStandredSize.setVisibility(View.VISIBLE);
                        MultiPagePrintingActivity.viewedtStandredSize.setVisibility(View.VISIBLE);

                        MultiPagePrintingActivity.edtOther.setVisibility(View.GONE);
                        MultiPagePrintingActivity.viewOther.setVisibility(View.GONE);

                    }else if (size_name.equalsIgnoreCase("Custom Size")){

                        MultiPagePrintingActivity.edtOther.setVisibility(View.VISIBLE);
                        MultiPagePrintingActivity.viewOther.setVisibility(View.VISIBLE);

                        MultiPagePrintingActivity.edtStandredSize.setVisibility(View.GONE);
                        MultiPagePrintingActivity.viewedtStandredSize.setVisibility(View.GONE);
                    }*/
                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return msizeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.chmSize)
        RadioButton chmSize;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            chmSize.setTypeface(UtilsFontFamily.typeFaceForRobotoRegular(context));
        }
    }
}
