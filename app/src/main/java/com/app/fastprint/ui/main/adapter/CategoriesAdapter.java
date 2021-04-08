package com.app.fastprint.ui.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.app.fastprint.R;
import com.app.fastprint.ui.category.aboutus.AboutusActivity;
import com.app.fastprint.ui.category.contactus.ContactUsActivity;
import com.app.fastprint.ui.category.enquiryforms.EnquiryFormsActivity;
import com.app.fastprint.ui.category.extras.ExtrasActivity;
import com.app.fastprint.ui.category.gallery.GalleryActivity;
import com.app.fastprint.ui.category.logo.DefinationofLogoActivity;
import com.app.fastprint.ui.category.services.ServicesActivity;
import com.app.fastprint.ui.findus.FindUsActivity;
import com.app.fastprint.ui.main.MainActivity;
import com.app.fastprint.ui.main.responseModel.MenuListResponseModel;
import com.app.fastprint.ui.product.ProductListingActivity;
import com.app.fastprint.ui.socialmedia.SocialMedialActivity;
import com.app.fastprint.utills.UtilsFontFamily;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    Context context;
    List<MenuListResponseModel.Data.Menu>  menuList;
    List<MenuListResponseModel.Data.Menu> mFilterList;

    public CategoriesAdapter(MainActivity context, List<MenuListResponseModel.Data.Menu> menuList) {
        this.context = context;
        this.menuList = menuList;
        this.mFilterList = menuList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_categories, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvCatgegoriesName.setText(menuList.get(position).getTitle());
        if (menuList.get(position).getImage() != null) {
            Glide.with(context).load(menuList.get(position).getImage())
                    .placeholder(R.drawable.ic_refresh)
                    .into(holder.imgCatgegories);
        } else {
            Glide.with(context).load(menuList.get(position).getImage())
                    .placeholder(R.drawable.ic_refresh)
                    .into(holder.imgCatgegories);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            Intent intent = null;

            @Override
            public void onClick(View v) {
                if (menuList.get(position).getTitle().equalsIgnoreCase("About us")) {
                    intent = new Intent(context, AboutusActivity.class);
                    context.startActivity(intent);

                } else if (menuList.get(position).getTitle().equalsIgnoreCase("Services")) {
                    intent = new Intent(context, ServicesActivity.class);
                    context.startActivity(intent);


                } else if (menuList.get(position).getTitle().equalsIgnoreCase("Definition of Logo")) {
                    intent = new Intent(context, DefinationofLogoActivity.class);
                    context.startActivity(intent);

                } else if (menuList.get(position).getTitle().equalsIgnoreCase("Contact Us")) {

                    intent = new Intent(context, ContactUsActivity.class);
                    context.startActivity(intent);

                }else if (menuList.get(position).getTitle().equalsIgnoreCase("Find Us")) {

                    intent = new Intent(context, FindUsActivity.class);
                    context.startActivity(intent);

                } else if (menuList.get(position).getTitle().equalsIgnoreCase("Enquiry Form")) {
                    intent = new Intent(context, EnquiryFormsActivity.class);
                    context.startActivity(intent);

                } else if (menuList.get(position).getTitle().equalsIgnoreCase("Store")) {

                    intent = new Intent(context, ProductListingActivity.class);
                    context.startActivity(intent);

                } else if (menuList.get(position).getTitle().equalsIgnoreCase("Social Media")) {
                    intent = new Intent(context, SocialMedialActivity.class);
                    context.startActivity(intent);

                }  else if (menuList.get(position).getTitle().equalsIgnoreCase("Photo Gallery")) {

                    intent = new Intent(context, GalleryActivity.class);
                    context.startActivity(intent);

                } else if (menuList.get(position).getTitle().equalsIgnoreCase("Extras")) {

                    intent = new Intent(context, ExtrasActivity.class);
                    context.startActivity(intent);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imgCatgegories)
        ImageView imgCatgegories;

        @BindView(R.id.tvCatgegoriesName)
        TextView tvCatgegoriesName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            tvCatgegoriesName.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        }
    }
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {

                    mFilterList = menuList;
                }else {

                    ArrayList<MenuListResponseModel.Data.Menu> filteredList = new ArrayList<>();

                    for (MenuListResponseModel.Data.Menu datum : menuList) {

                        if (datum.getTitle().toLowerCase().contains(charString)||datum.getTitle().toUpperCase().contains(charString)) {

                            filteredList.add(datum);
                        }
                    }
                    mFilterList = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilterList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilterList = (ArrayList<MenuListResponseModel.Data.Menu>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
