package com.hitherejoe.animate.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;

import androidx.appcompat.app.ActionBar;
import androidx.interpolator.view.animation.FastOutLinearInInterpolator;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hitherejoe.animate.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewPropertyAnimatorActivity extends BaseActivity {

    @BindView(R.id.check_animate_alpha)
    CheckBox mAnimateAlphaCheck;

    @BindView(R.id.check_animate_scale)
    CheckBox mAnimateScaleCheck;

    @BindView(R.id.check_animate_z)
    CheckBox mAnimateZCheck;

    @BindView(R.id.check_animation_duration)
    CheckBox mAnimationDurationCheck;

    @BindView(R.id.check_animation_delay)
    CheckBox mAnimationDelayCheck;

    @BindView(R.id.fab_smile)
    FloatingActionButton mSmileButton;

    @BindView(R.id.spinner_interpolators)
    Spinner mAnimationInterpolatorSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_properties);
        ButterKnife.bind(this);
        setupSpinnerAdapter();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void setupSpinnerAdapter() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, R.layout.item_spinner, getResources().getStringArray(R.array.interpolators));
        adapter.setDropDownViewResource(R.layout.item_spinner_dropdown);
        mAnimationInterpolatorSpinner.setAdapter(adapter);
    }

    @OnClick(R.id.text_start_animation)
    public void onAnimateTextClicked() {
        buildAndStartAnimation(mSmileButton);
    }

    @OnClick(R.id.text_reset_animations)
    public void onResetAnimationsClicked() {
        mSmileButton.setAlpha(1f);
        mSmileButton.setScaleY(1f);
        mSmileButton.setScaleX(1f);
        mSmileButton.setTranslationZ(1f);
    }

    private void buildAndStartAnimation(View view) {
        ViewPropertyAnimator animator = view.animate();

        if (mAnimateAlphaCheck.isChecked() || view.getAlpha() == 0f) {
            float animationValue = view.getAlpha() == 0f ? 1f : 0f;
            animator.alpha(animationValue);
        }
        if (mAnimateScaleCheck.isChecked()) {
            float animationValue = view.getScaleY() == 0f ? 1f : 0f;
            animator.scaleX(animationValue).scaleY(animationValue);
        }
        if (mAnimateZCheck.isChecked()) {
            float animationValue = view.getTranslationZ() != 25f ? 25f : 2f;
            animator.translationZ(animationValue);
        }
        if (mAnimationDurationCheck.isChecked()) {
            animator.setDuration(500l);
        }
        if (mAnimationDelayCheck.isChecked()) {
            animator.setStartDelay(500l);
        }
        animator.setInterpolator(getSelectedInterpolator());
        animator.start();
    }

    private Interpolator getSelectedInterpolator() {
        switch (mAnimationInterpolatorSpinner.getSelectedItemPosition()) {
            case 1:
                return new FastOutLinearInInterpolator();
            case 2:
                return new FastOutSlowInInterpolator();
            case 3:
                return new LinearOutSlowInInterpolator();
            case 4:
                return new AccelerateDecelerateInterpolator();
            case 5:
                return new AccelerateInterpolator();
            case 6:
                return new DecelerateInterpolator();
            case 7:
                return new AnticipateInterpolator();
            case 8:
                return new AnticipateOvershootInterpolator();
            case 9:
                return new BounceInterpolator();
            case 10:
                return new LinearInterpolator();
            case 11:
                return new OvershootInterpolator();
            default:
                return null;
        }
    }

}
