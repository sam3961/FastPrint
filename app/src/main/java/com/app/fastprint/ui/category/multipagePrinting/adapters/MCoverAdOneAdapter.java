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
import com.app.fastprint.ui.category.commericalPrinting.CommercialPrintingActivity;
import com.app.fastprint.ui.category.commericalPrinting.viewModel.Model;
import com.app.fastprint.utills.UtilsFontFamily;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MCoverAdOneAdapter extends RecyclerView.Adapter<MCoverAdOneAdapter.ViewHolder> {

    Context context;
    CommercialPrintingActivity commercialPrintingActivity;
    ArrayList<Model> categoryModelArrayList;
    List<String> list = new ArrayList<String>();

    public MCoverAdOneAdapter(Context context, ArrayList<Model> categoryModelArrayList, CommercialPrintingActivity commercialPrintingActivity) {

        this.context = context;
        this.categoryModelArrayList = categoryModelArrayList;
        this.commercialPrintingActivity = commercialPrintingActivity;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_special_ad_one, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.chSpecialAdOne.setText(categoryModelArrayList.get(position).getName());
        holder.chSpecialAdOne.setChecked(categoryModelArrayList.get(position).isSelected());
        holder.chSpecialAdOne.setTag(position);

        holder.chSpecialAdOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer pos = (Integer) holder.chSpecialAdOne.getTag();
                Toast.makeText(context, categoryModelArrayList.get(pos).getName() + " clicked!", Toast.LENGTH_SHORT).show();
                list.add(categoryModelArrayList.get(pos).getName()); //this adds an element to the list.

                if (categoryModelArrayList.get(pos).isSelected()) {
                    categoryModelArrayList.get(pos).setSelected(false);
                } else {
                    categoryModelArrayList.get(pos).setSelected(true);
                }

                ((CommercialPrintingActivity) commercialPrintingActivity).OnOptionClick(list);


            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.chSpecialAdOne)
        CheckBox chSpecialAdOne;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            chSpecialAdOne.setTypeface(UtilsFontFamily.typeFaceForRobotoRegular(context));
        }
    }
}
