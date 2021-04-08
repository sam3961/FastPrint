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

public class MBindingAdapter extends RecyclerView.Adapter<MBindingAdapter.ViewHolder> {

    Context context;
    List<String> mbindingList;
    private int mCheckedPostion = -1;// no selection by default
    public MBindingAdapter(Context context, List<String> mbindingList) {
        this.context = context;
        this.mbindingList = mbindingList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_m_binding, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.chMBinding.setChecked(position == mCheckedPostion);
        holder.chMBinding.setText(mbindingList.get(position));
        holder.chMBinding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position == mCheckedPostion) {
                    holder.chMBinding.setChecked(false);
                    mCheckedPostion = -1;
                } else {
                    mCheckedPostion = position;
                    notifyDataSetChanged();
                    MultiPagePrintingActivity.edtBinding.setText(mbindingList.get(position));
                    MultiPagePrintingActivity.mBinding.dismiss();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mbindingList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.chMBinding)
        RadioButton chMBinding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            chMBinding.setTypeface(UtilsFontFamily.typeFaceForRobotoRegular(context));
        }
    }
}
