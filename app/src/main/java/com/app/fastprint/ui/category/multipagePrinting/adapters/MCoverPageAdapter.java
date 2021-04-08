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

public class MCoverPageAdapter extends RecyclerView.Adapter<MCoverPageAdapter.ViewHolder> {

    Context context;
    List<String> mCoverPageTypeList;
    private int mCheckedPostion = -1;// no selection by default


    public MCoverPageAdapter(Context context, List<String> mCoverPageTypeList) {
        this.context = context;
        this.mCoverPageTypeList = mCoverPageTypeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_m_cover_page_type, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.chMCoverPageType.setChecked(position == mCheckedPostion);
        holder.chMCoverPageType.setText(mCoverPageTypeList.get(position));
        holder.chMCoverPageType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position == mCheckedPostion) {
                    holder.chMCoverPageType.setChecked(false);
                    mCheckedPostion = -1;
                } else {
                    mCheckedPostion = position;
                    notifyDataSetChanged();
                    MultiPagePrintingActivity.edtPaperType.setText(mCoverPageTypeList.get(position));
                    MultiPagePrintingActivity.mCoverPageType.dismiss();

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCoverPageTypeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.chMCoverPageType)
        RadioButton chMCoverPageType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            chMCoverPageType.setTypeface(UtilsFontFamily.typeFaceForRobotoRegular(context));
        }
    }
}
