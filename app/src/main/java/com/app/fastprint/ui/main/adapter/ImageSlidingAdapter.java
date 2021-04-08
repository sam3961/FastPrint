package com.app.fastprint.ui.main.adapter;

import android.app.Dialog;
import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.app.fastprint.R;
import com.app.fastprint.ui.main.responseModel.MenuListResponseModel;

import java.util.List;

public class ImageSlidingAdapter extends PagerAdapter {

    List<MenuListResponseModel.Data.Banner> banner;
    private LayoutInflater inflater;
    private Context context;
    ImageView imageView;

    public ImageSlidingAdapter(Context context, List<MenuListResponseModel.Data.Banner> banner) {
        this.context = context;
        this.banner = banner;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return banner.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, final int position) {
        View imageLayout = inflater.inflate(R.layout.layout_image_slider, view, false);
        assert imageLayout != null;
        imageView = (ImageView) imageLayout
                .findViewById(R.id.image);

        if (banner.size() >= 0) {
            Glide.with(context).load(banner.get(position).getImage()).into(imageView);
        } else {
        }

        view.addView(imageLayout, 0);

        return imageLayout;
    }

    private void showImage(String image_drawable) {

        Dialog dialogAlert = new Dialog(context, R.style.MyCustomTheme);
        dialogAlert.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        dialogAlert.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogAlert.setContentView(R.layout.layout_zoom);
        ImageView img_selected_item = (ImageView) dialogAlert.findViewById(R.id.img_selected_item);
        img_selected_item.setImageResource(Integer.parseInt(image_drawable));
        dialogAlert.show();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }


}