package com.shequn.liming.mobaidanche.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.shequn.liming.mobaidanche.R;

/**
 * Created by Liming on 2017/7/27.
 */

public class LoadingToastView extends FrameLayout {

    private ImageView loadImage;
    private TextView toastText;

    public LoadingToastView(@NonNull Context context) {
        this(context, null);
    }

    public LoadingToastView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initView();

    }


    private void initView() {
        View.inflate(getContext(), R.layout.loading_toast, this);

        loadImage = (ImageView) findViewById(R.id.loadImage);
        toastText = (TextView) findViewById(R.id.toastText);

        Animation anim =new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setRepeatMode(Animation.INFINITE);
        anim.setFillAfter(true);
        anim.setRepeatCount(-1);
        anim.setDuration(1000); // 设置动画时间
        anim.setInterpolator(new AccelerateInterpolator()); // 设置插入器
        loadImage.startAnimation(anim);

    }

    public void toast(String text) {
        toastText.setText(text);
    }
}
