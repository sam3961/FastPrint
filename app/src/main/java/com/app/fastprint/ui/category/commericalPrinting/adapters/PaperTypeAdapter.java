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

public class PaperTypeAdapter extends RecyclerView.Adapter<PaperTypeAdapter.ViewHolder> {

    Context context;
    List<String> paperTypeList;
    private int mCheckedPostion = -1;// no selection by default


    public PaperTypeAdapter(Context context, List<String> paperTypeList) {
        this.context = context;
        this.paperTypeList = paperTypeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_paper_type, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.chPaperType.setChecked(position == mCheckedPostion);

        holder.chPaperType.setText(paperTypeList.get(position));
        holder.chPaperType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position == mCheckedPostion) {
                    holder.chPaperType.setChecked(false);
                    mCheckedPostion = -1;
                } else {
                    mCheckedPostion = position;
                    notifyDataSetChanged();
                    CommercialPrintingActivity.edtPaperType.setText(paperTypeList.get(position));
                    CommercialPrintingActivity.paperTypeDialog.dismiss();

                }
                      }
        });
    }

    @Override
    public int getItemCount() {
        return paperTypeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.chPaperType)
        RadioButton chPaperType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            chPaperType.setTypeface(UtilsFontFamily.typeFaceForRobotoRegular(context));
        }
    }
}
