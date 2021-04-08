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

public class SlidesAdapter extends RecyclerView.Adapter<SlidesAdapter.ViewHolder> {

    Context context;
    List<String> slideList;
    private int mCheckedPostion = -1;// no selection by default


    public SlidesAdapter(Context context, List<String> slideList) {
        this.context = context;
        this.slideList = slideList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_slides, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.chSlides.setChecked(position == mCheckedPostion);
        holder.chSlides.setText(slideList.get(position));
        holder.chSlides.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (position == mCheckedPostion) {
                    holder.chSlides.setChecked(false);
                    mCheckedPostion = -1;
                } else {
                    mCheckedPostion = position;
                    notifyDataSetChanged();
                    CommercialPrintingActivity.edtSlide.setText(slideList.get(position));
                    CommercialPrintingActivity.slidesDialog.dismiss();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return slideList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.chSlides)
        RadioButton chSlides;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            chSlides.setTypeface(UtilsFontFamily.typeFaceForRobotoRegular(context));
        }
    }
}
