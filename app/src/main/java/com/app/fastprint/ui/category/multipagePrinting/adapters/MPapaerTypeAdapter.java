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

public class MPapaerTypeAdapter extends RecyclerView.Adapter<MPapaerTypeAdapter.ViewHolder> {

    Context context;
    List<String> mpaperTypeList;
    private int mCheckedPostion = -1;// no selection by default


    public MPapaerTypeAdapter(Context context, List<String> mpaperTypeList) {
        this.context = context;
        this.mpaperTypeList = mpaperTypeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_m_papaer_typer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.chmPaperType.setChecked(position == mCheckedPostion);

        holder.chmPaperType.setText(mpaperTypeList.get(position));
        holder.chmPaperType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position == mCheckedPostion) {
                    holder.chmPaperType.setChecked(false);
                    mCheckedPostion = -1;
                } else {
                    mCheckedPostion = position;
                    notifyDataSetChanged();
                    MultiPagePrintingActivity.edtCoverPaperType.setText(mpaperTypeList.get(position));
                    MultiPagePrintingActivity.mpaperTypeDialog.dismiss();


                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mpaperTypeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.chmPaperType)
        RadioButton chmPaperType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            chmPaperType.setTypeface(UtilsFontFamily.typeFaceForRobotoRegular(context));
        }
    }
}
