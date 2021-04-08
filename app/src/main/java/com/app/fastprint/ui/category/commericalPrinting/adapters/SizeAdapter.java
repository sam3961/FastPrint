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

public class SizeAdapter extends RecyclerView.Adapter<SizeAdapter.ViewHolder> {

    Context context;
    List<String> sizeList;
    private int mCheckedPostion = -1;// no selection by default

    public SizeAdapter(Context context, List<String> sizeList) {
        this.context = context;
        this.sizeList = sizeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_size, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.chSize.setChecked(position == mCheckedPostion);
        holder.chSize.setText(sizeList.get(position));
        holder.chSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (position == mCheckedPostion) {
                    holder.chSize.setChecked(false);
                    mCheckedPostion = -1;
                } else {
                    mCheckedPostion = position;
                    notifyDataSetChanged();
                    CommercialPrintingActivity.edtSize.setText(sizeList.get(position));
                    CommercialPrintingActivity.sizeDialog.dismiss();

                    String size_name=sizeList.get(position);
                    Toast.makeText(context, ""+size_name, Toast.LENGTH_SHORT).show();

                  /*  if (size_name.equalsIgnoreCase("Size")){

                        CommercialPrintingActivity.edtOther.setVisibility(View.GONE);
                        CommercialPrintingActivity.viewOther.setVisibility(View.GONE);

                        CommercialPrintingActivity.edtStandredSize.setVisibility(View.GONE);
                        CommercialPrintingActivity.viewedtStandredSize.setVisibility(View.GONE);

                    }else if (size_name.equalsIgnoreCase("Standard Size")){
                        CommercialPrintingActivity.edtStandredSize.setVisibility(View.VISIBLE);
                        CommercialPrintingActivity.viewedtStandredSize.setVisibility(View.VISIBLE);

                        CommercialPrintingActivity.edtOther.setVisibility(View.GONE);
                        CommercialPrintingActivity.viewOther.setVisibility(View.GONE);

                    }else if (size_name.equalsIgnoreCase("Custom Size")){

                        CommercialPrintingActivity.edtOther.setVisibility(View.VISIBLE);
                        CommercialPrintingActivity.viewOther.setVisibility(View.VISIBLE);

                        CommercialPrintingActivity.edtStandredSize.setVisibility(View.GONE);
                        CommercialPrintingActivity.viewedtStandredSize.setVisibility(View.GONE);
                    }*/
                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return sizeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.chSize)
        RadioButton chSize;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            chSize.setTypeface(UtilsFontFamily.typeFaceForRobotoRegular(context));
        }
    }
}
