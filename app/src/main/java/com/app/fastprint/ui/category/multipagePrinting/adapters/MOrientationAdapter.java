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

public class MOrientationAdapter extends RecyclerView.Adapter<MOrientationAdapter.ViewHolder> {

    Context context;
    List<String> morientationList;
    private int mCheckedPostion = -1;// no selection by default


    public MOrientationAdapter(Context context, List<String> morientationList) {
        this.context = context;
        this.morientationList = morientationList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_m_orientation, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.chmOrientations.setChecked(position == mCheckedPostion);
        holder.chmOrientations.setText(morientationList.get(position));
        holder.chmOrientations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position == mCheckedPostion) {
                    holder.chmOrientations.setChecked(false);
                    mCheckedPostion = -1;
                } else {
                    mCheckedPostion = position;
                    notifyDataSetChanged();
                    MultiPagePrintingActivity.edtOrientation.setText(morientationList.get(position));
                    MultiPagePrintingActivity.morientationDialog.dismiss();

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return morientationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.chmOrientations)
        RadioButton chmOrientations;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            chmOrientations.setTypeface(UtilsFontFamily.typeFaceForRobotoRegular(context));
        }
    }
}
