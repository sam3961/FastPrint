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

public class MNumberOfPageAdapter extends RecyclerView.Adapter<MNumberOfPageAdapter.ViewHolder> {

    Context context;
    List<String> mNumberOfPageList;
    private int mCheckedPostion = -1;// no selection by default

   

    public MNumberOfPageAdapter(Context context, List<String> mNumberOfPageList) {
        this.context = context;
        this.mNumberOfPageList = mNumberOfPageList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_m_numberof_page, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.chMNumberOfPage.setChecked(position == mCheckedPostion);
        holder.chMNumberOfPage.setText(mNumberOfPageList.get(position));

        holder.chMNumberOfPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position == mCheckedPostion) {
                    holder.chMNumberOfPage.setChecked(false);
                    mCheckedPostion = -1;
                } else {
                    mCheckedPostion = position;
                    notifyDataSetChanged();
                    MultiPagePrintingActivity.edtNumberofPage.setText(mNumberOfPageList.get(position));
                    MultiPagePrintingActivity.mNumberofPage.dismiss();

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNumberOfPageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.chMNumberOfPage)
        RadioButton chMNumberOfPage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            chMNumberOfPage.setTypeface(UtilsFontFamily.typeFaceForRobotoRegular(context));
        }
    }
}
