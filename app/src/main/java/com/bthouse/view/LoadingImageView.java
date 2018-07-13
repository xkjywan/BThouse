package com.bthouse.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.bthouse.R;

public class LoadingImageView extends ImageView {

    public LoadingImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        Animation operatingAnim = AnimationUtils.loadAnimation(getContext(),
                R.anim.loading_image_view);
        LinearInterpolator lin = new LinearInterpolator();
        operatingAnim.setInterpolator(lin);
        startAnimation(operatingAnim);
    }

}
