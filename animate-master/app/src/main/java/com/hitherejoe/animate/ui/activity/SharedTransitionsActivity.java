package com.hitherejoe.animate.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;

import com.hitherejoe.animate.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SharedTransitionsActivity extends BaseActivity {

    @BindView(R.id.text_shared_toolbar)
    RelativeLayout mRelativeView;

    @BindView(R.id.text_toolbar)
    TextView mToolbarView;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_transitions);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @OnClick(R.id.text_shared_transition)
    public void startSharedTransition(View view) {
        Intent intent =
                new Intent(SharedTransitionsActivity.this, SharedTransitionInToolbarActivity.class);
        intent.putExtra(
                TransitionInActivity.EXTRA_TRANSITION, TransitionInActivity.TRANSITION_FADE_FAST);
        Pair participants = new Pair<>(view, ViewCompat.getTransitionName(view));
        ActivityOptionsCompat transitionActivityOptions =
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                        SharedTransitionsActivity.this, participants);
        ActivityCompat.startActivity(
                SharedTransitionsActivity.this, intent, transitionActivityOptions.toBundle());
    }

    @OnClick(R.id.text_shared_toolbar)
    public void startToolbarTransition() {
        Intent intent =
                new Intent(SharedTransitionsActivity.this, SharedTransitionToolbarActivity.class);
        Pair squareParticipant =
                new Pair<>(mRelativeView, ViewCompat.getTransitionName(mRelativeView));
        Pair toolbarParticipants =
                new Pair<>(mToolbarView, ViewCompat.getTransitionName(mToolbarView));
        ActivityOptionsCompat transitionActivityOptions =
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                        SharedTransitionsActivity.this, squareParticipant, toolbarParticipants);
        ActivityCompat.startActivity(
                SharedTransitionsActivity.this, intent, transitionActivityOptions.toBundle());
    }

}
