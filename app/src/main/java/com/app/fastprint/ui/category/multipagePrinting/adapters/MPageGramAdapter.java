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
import com.app.fastprint.ui.category.commericalPrinting.CommercialPrintingActivity;
import com.app.fastprint.utills.UtilsFontFamily;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MPageGramAdapter extends RecyclerView.Adapter<MPageGramAdapter.ViewHolder> {

    Context context;
    List<String> paperGramList;
    private int mCheckedPostion = -1;// no selection by default


    public MPageGramAdapter(Context context, List<String> paperGramList) {
        this.context = context;
        this.paperGramList = paperGramList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_paper_gram, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.chPaperGram.setChecked(position == mCheckedPostion);
        holder.chPaperGram.setText(paperGramList.get(position));
        holder.chPaperGram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position == mCheckedPostion) {
                    holder.chPaperGram.setChecked(false);
                    mCheckedPostion = -1;
                } else {
                    mCheckedPostion = position;
                    notifyDataSetChanged();
                    CommercialPrintingActivity.edtPaperGram.setText(paperGramList.get(position));
                    CommercialPrintingActivity.paperGrmDialog.dismiss();

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return paperGramList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.chPaperGram)
        RadioButton chPaperGram;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            chPaperGram.setTypeface(UtilsFontFamily.typeFaceForRobotoRegular(context));
        }
    }
}
