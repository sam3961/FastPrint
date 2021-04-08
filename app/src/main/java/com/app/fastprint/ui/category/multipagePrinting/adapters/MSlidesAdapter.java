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

public class MSlidesAdapter extends RecyclerView.Adapter<MSlidesAdapter.ViewHolder> {

    Context context;
    List<String> mslideList;
    private int mCheckedPostion = -1;// no selection by default


    public MSlidesAdapter(Context context, List<String> mslideList) {
        this.context = context;
        this.mslideList = mslideList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_m_slide, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.chMSlide.setChecked(position == mCheckedPostion);
        holder.chMSlide.setText(mslideList.get(position));
        holder.chMSlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (position == mCheckedPostion) {
                    holder.chMSlide.setChecked(false);
                    mCheckedPostion = -1;
                } else {
                    mCheckedPostion = position;
                    notifyDataSetChanged();
                    MultiPagePrintingActivity.edtSlide.setText(mslideList.get(position));
                    MultiPagePrintingActivity.mslidesDialog.dismiss();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mslideList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.chMSlide)
        RadioButton chMSlide;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            chMSlide.setTypeface(UtilsFontFamily.typeFaceForRobotoRegular(context));
        }
    }
}
