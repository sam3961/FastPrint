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
import com.app.fastprint.utills.UtilsFontFamily;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MOtherAdapter extends RecyclerView.Adapter<MOtherAdapter.ViewHolder> {

    Context context;
    List<String> motherList;
    private int mCheckedPostion = -1;// no selection by default

    public MOtherAdapter(Context context, List<String> motherList) {
        this.context = context;
        this.motherList = motherList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_m_other, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.chmOther.setChecked(position == mCheckedPostion);
        holder.chmOther.setText(motherList.get(position));

        holder.chmOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position == mCheckedPostion) {
                    holder.chmOther.setChecked(false);
                    mCheckedPostion = -1;
                } else {
                    mCheckedPostion = position;
                    notifyDataSetChanged();
                   /* MultiPagePrintingActivity.edtOther.setText(motherList.get(position));
                    MultiPagePrintingActivity.motherDialog.dismiss();
*/
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return motherList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.chmOther)
        RadioButton chmOther;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            chmOther.setTypeface(UtilsFontFamily.typeFaceForRobotoRegular(context));
        }
    }
}
