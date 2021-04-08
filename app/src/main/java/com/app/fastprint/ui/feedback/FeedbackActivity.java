package com.app.fastprint.ui.feedback;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.app.fastprint.R;
import com.app.fastprint.ui.thankyou.ThankyouActivity;
import com.app.fastprint.utills.UtilsFontFamily;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FeedbackActivity extends AppCompatActivity {

    @BindView(R.id.tvTittle)
    TextView tvTittle;
    @BindView(R.id.tvThankyou)
    TextView tvThankyou;
    @BindView(R.id.tvOrderPlace)
    TextView tvOrderPlace;
    @BindView(R.id.tvExperience)
    TextView tvExperience;
    @BindView(R.id.productRating)
    RatingBar productRating;
    @BindView(R.id.edtReview)
    EditText edtReview;
    @BindView(R.id.tvSubmit)
    TextView tvSubmit;
    @BindView(R.id.tvSkipp)
    TextView tvSkipp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ButterKnife.bind(this);
        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));
        tvSubmit.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));
        tvSkipp.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(this));

    }

    @OnClick({R.id.tvSubmit, R.id.tvSkipp})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvSubmit:
                thankYouIntent();
                break;
            case R.id.tvSkipp:
                thankYouIntent();
                break;
        }
    }

    private void thankYouIntent() {
        Intent intent = new Intent(FeedbackActivity.this, ThankyouActivity.class);
        startActivity(intent);
    }
}