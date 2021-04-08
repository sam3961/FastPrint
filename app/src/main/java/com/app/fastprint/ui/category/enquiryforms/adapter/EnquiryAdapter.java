package com.app.fastprint.ui.category.enquiryforms.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.app.fastprint.R;
import com.app.fastprint.ui.category.commericalPrintingList.CommercialPrintingCategoriesActivity;
import com.app.fastprint.ui.category.enquiryforms.responseModel.FormsResponseModel;
import com.app.fastprint.ui.category.multipagePrinting.MultiPagePrintingActivity;
import com.app.fastprint.utills.UtilsFontFamily;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EnquiryAdapter extends RecyclerView.Adapter<EnquiryAdapter.ViewHolder> {

    Context context;
    List<FormsResponseModel.Data.Enquiry> enquiry;

    public EnquiryAdapter(Context context, List<FormsResponseModel.Data.Enquiry> enquiry) {
        this.context = context;
        this.enquiry = enquiry;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_enquiry_form, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

         holder.tvEnquiryType.setText(enquiry.get(position).getName());
        if (enquiry.get(position).getImage() != null) {
            Glide.with(context).load(enquiry.get(position).getImage())
                    .placeholder(R.drawable.ic_refresh)
                    .into(holder.imgEnquiry);
        } else {
            Glide.with(context).load(enquiry.get(position).getImage())
                    .placeholder(R.drawable.ic_refresh)
                    .into(holder.imgEnquiry);
        }



        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (enquiry.get(position).getName().equalsIgnoreCase("Commercial Printing")) {

                    Intent intent_commercial = new Intent(context, CommercialPrintingCategoriesActivity.class);
                    intent_commercial.putExtra("printing_id",enquiry.get(position).getId());
                    intent_commercial.putExtra("printing_name",enquiry.get(position).getName());
                    context.startActivity(intent_commercial);

                } else if ((enquiry.get(position).getName().equalsIgnoreCase("Multi Page Printing"))) {

                    Intent intent_multipage = new Intent(context, MultiPagePrintingActivity.class);
                    intent_multipage.putExtra("printing_id",enquiry.get(position).getId());
                    intent_multipage.putExtra("printing_name",enquiry.get(position).getName());
                    context.startActivity(intent_multipage);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return enquiry.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imgEnquiry)
        ImageView imgEnquiry;

        @BindView(R.id.tvEnquiryType)
        TextView tvEnquiryType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            tvEnquiryType.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));

        }
    }
}
