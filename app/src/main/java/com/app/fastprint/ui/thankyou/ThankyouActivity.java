package com.app.fastprint.ui.thankyou;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.app.fastprint.R;
import com.app.fastprint.baseClass.BaseClass;
import com.app.fastprint.ui.main.MainActivity;
import com.app.fastprint.utills.AppConstants;
import com.app.fastprint.utills.AppControler;
import com.app.fastprint.utills.UtilsFontFamily;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.review.testing.FakeReviewManager;
import com.google.android.play.core.tasks.Task;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ThankyouActivity extends BaseClass {

    @BindView(R.id.tvThankyou)
    TextView tvThankyou;
    @BindView(R.id.tvsendEmail)
    TextView tvsendEmail;
    @BindView(R.id.tvOrderId)
    TextView tvOrderId;
    @BindView(R.id.tvBackToShop)
    TextView tvBackToShop;
    String orderId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thankyou);
        ButterKnife.bind(this);
        orderId = getIntent().getStringExtra("orderId");
        tvThankyou.setTypeface(UtilsFontFamily.typeFaceForRobotoBold(this));
        tvOrderId.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));
        tvBackToShop.setTypeface(UtilsFontFamily.typeFaceForRobotoBold(this));
        AppControler.getInstance(ThankyouActivity.this).addProductToCart(new ArrayList<>(), AppConstants.KEY_CART);

        tvOrderId.setText(orderId);

        if (!AppControler.getInstance(ThankyouActivity.this).getBoolean(AppConstants.KEY_RATING))
            inAppRating();

    }


    @OnClick(R.id.tvBackToShop)
    public void onViewClicked() {
        Intent intent = new Intent(ThankyouActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ThankyouActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void inAppRating() {
        ReviewManager manager = ReviewManagerFactory.create(this);
        Task<ReviewInfo> request = manager.requestReviewFlow();
        request.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // We can get the ReviewInfo object
                ReviewInfo reviewInfo = task.getResult();
                Task<Void> flow = manager.launchReviewFlow(this, reviewInfo);
                flow.addOnCompleteListener(tasks -> {
                    AppControler.getInstance(ThankyouActivity.this).put(AppConstants.KEY_RATING, true);
                    // The flow has finished. The API does not indicate whether the user
                    // reviewed or not, or even whether the review dialog was shown. Thus, no
                    // matter the result, we continue our app flow.
                });
            } else {
                // There was some problem, continue regardless of the result.
            }
        });


    }

}
