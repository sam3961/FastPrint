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

public class OrientationAdapter extends RecyclerView.Adapter<OrientationAdapter.ViewHolder> {

    Context context;
    List<String> orientationList;
    private int mCheckedPostion = -1;// no selection by default


    public OrientationAdapter(Context context, List<String> orientationList) {
        this.context = context;
        this.orientationList = orientationList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_orientation, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.chOrientation.setChecked(position == mCheckedPostion);
        holder.chOrientation.setText(orientationList.get(position));
        holder.chOrientation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position == mCheckedPostion) {
                    holder.chOrientation.setChecked(false);
                    mCheckedPostion = -1;
                } else {
                    mCheckedPostion = position;
                    notifyDataSetChanged();
                    CommercialPrintingActivity.edtOrientation.setText(orientationList.get(position));
                    CommercialPrintingActivity.orientationDialog.dismiss();

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return orientationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.chOrientation)
        RadioButton chOrientation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            chOrientation.setTypeface(UtilsFontFamily.typeFaceForRobotoRegular(context));
        }
    }
}
