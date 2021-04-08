package com.app.fastprint.ui.category.multipagePrinting.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fastprint.R;
import com.app.fastprint.ui.category.commericalPrinting.viewModel.Model;
import com.app.fastprint.ui.category.multipagePrinting.MultiPagePrintingActivity;
import com.app.fastprint.utills.UtilsFontFamily;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MCoverlAdOneAdapter extends RecyclerView.Adapter<MCoverlAdOneAdapter.ViewHolder> {

    Context context;
    MultiPagePrintingActivity multiPagePrintingActivity;
    ArrayList<Model> categoryModelArrayList;
    List<String> list = new ArrayList<String>();


    public MCoverlAdOneAdapter(Context context, ArrayList<Model> categoryModelArrayList, MultiPagePrintingActivity multiPagePrintingActivity) {
        this.context = context;
        this.categoryModelArrayList = categoryModelArrayList;
        this.multiPagePrintingActivity = multiPagePrintingActivity;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_m_cover_ad_on, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.chCoverAdOne.setText(categoryModelArrayList.get(position).getName());
        holder.chCoverAdOne.setChecked(categoryModelArrayList.get(position).isSelected());
        holder.chCoverAdOne.setTag(position);

        holder.chCoverAdOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer pos = (Integer) holder.chCoverAdOne.getTag();
                Toast.makeText(context, categoryModelArrayList.get(pos).getName() + " clicked!", Toast.LENGTH_SHORT).show();
                list.add(categoryModelArrayList.get(pos).getName()); //this adds an element to the list.

                if (categoryModelArrayList.get(pos).isSelected()) {
                    categoryModelArrayList.get(pos).setSelected(false);
                } else {
                    categoryModelArrayList.get(pos).setSelected(true);
                }

                ((MultiPagePrintingActivity) multiPagePrintingActivity).OnOptionClick(list);


            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.chCoverAdOne)
        CheckBox chCoverAdOne;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            chCoverAdOne.setTypeface(UtilsFontFamily.typeFaceForRobotoRegular(context));
        }
    }
}
