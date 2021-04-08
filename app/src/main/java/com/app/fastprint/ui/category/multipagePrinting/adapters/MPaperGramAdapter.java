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

public class MPaperGramAdapter extends RecyclerView.Adapter<MPaperGramAdapter.ViewHolder> {

    Context context;
    List<String> mpaperGramList;
    private int mCheckedPostion = -1;// no selection by default


    public MPaperGramAdapter(Context context, List<String> mpaperGramList) {
        this.context = context;
        this.mpaperGramList = mpaperGramList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_m_paper_gram, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.chmmPaperGram.setChecked(position == mCheckedPostion);

        holder.chmmPaperGram.setText(mpaperGramList.get(position));
        holder.chmmPaperGram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position == mCheckedPostion) {
                    holder.chmmPaperGram.setChecked(false);
                    mCheckedPostion = -1;
                } else {
                    mCheckedPostion = position;
                    notifyDataSetChanged();
                    MultiPagePrintingActivity.edtCoverPaperGram.setText(mpaperGramList.get(position));
                    MultiPagePrintingActivity.mpaperGrmDialog.dismiss();



                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mpaperGramList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.chmmPaperGram)
        RadioButton chmmPaperGram;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            chmmPaperGram.setTypeface(UtilsFontFamily.typeFaceForRobotoRegular(context));
        }
    }
}
