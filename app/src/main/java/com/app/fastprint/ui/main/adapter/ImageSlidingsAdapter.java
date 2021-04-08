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

import com.app.fastprint.R;
import com.app.fastprint.ui.productDetails.ImageModel;

import java.util.ArrayList;

public class ImageSlidingsAdapter extends PagerAdapter {


    private ArrayList<ImageModel> imageModelArrayList;
    private LayoutInflater inflater;
    private Context context;


    public ImageSlidingsAdapter(Context context, ArrayList<ImageModel> imageModelArrayList) {
        this.context = context;
        this.imageModelArrayList = imageModelArrayList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return imageModelArrayList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, final int position) {
        View imageLayout = inflater.inflate(R.layout.layout_image_slider, view, false);

        assert imageLayout != null;
        final ImageView imageView = (ImageView) imageLayout
                .findViewById(R.id.image);


        imageView.setImageResource(imageModelArrayList.get(position).getImage_drawable());


        view.addView(imageLayout, 0);

        return imageLayout;
    }

    private void showImage(int image_drawable) {

        Dialog dialogAlert = new Dialog(context,R.style.MyCustomTheme);
        dialogAlert.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        dialogAlert.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogAlert.setContentView(R.layout.layout_zoom);
        ImageView img_selected_item = (ImageView) dialogAlert.findViewById(R.id.img_selected_item);
        img_selected_item.setImageResource(image_drawable);
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