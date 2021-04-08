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

public class MCoverPageGramAdapter extends RecyclerView.Adapter<MCoverPageGramAdapter.ViewHolder> {

    Context context;
    List<String> mCoverPageGramList;
    private int mCheckedPostion = -1;// no selection by default


    public MCoverPageGramAdapter(Context context, List<String> mCoverPageGramList) {
        this.context = context;
        this.mCoverPageGramList = mCoverPageGramList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_m_cover_page_gram, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.chMCoverPageGram.setChecked(position == mCheckedPostion);
        holder.chMCoverPageGram.setText(mCoverPageGramList.get(position));
        holder.chMCoverPageGram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position == mCheckedPostion) {
                    holder.chMCoverPageGram.setChecked(false);
                    mCheckedPostion = -1;
                } else {
                    mCheckedPostion = position;
                    notifyDataSetChanged();
                    MultiPagePrintingActivity.edtPaperGram.setText(mCoverPageGramList.get(position));
                    MultiPagePrintingActivity.mCoverPageGram.dismiss();

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCoverPageGramList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.chMCoverPageGram)
        RadioButton chMCoverPageGram;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            chMCoverPageGram.setTypeface(UtilsFontFamily.typeFaceForRobotoRegular(context));
        }
    }
}
