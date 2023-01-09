package com.hitherejoe.animate.ui.activity;

import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import com.hitherejoe.animate.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SharedTransitionToolbarActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Slide slide = new Slide(Gravity.BOTTOM);
        slide.addTarget(R.id.text_detail);
        slide.addTarget(R.id.text_close);
        slide.addTarget(R.id.view_separator);
        getWindow().setEnterTransition(slide);
        setContentView(R.layout.activity_shared_transition_in);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @OnClick(R.id.text_close)
    public void onCloseTextClicked() {
        finishAfterTransition();
    }

}
