package com.hitherejoe.animate.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.hitherejoe.animate.R;
import com.hitherejoe.animate.ui.adapter.GridAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WindowTransitionsActivityExplode extends BaseActivity {

    @BindView(R.id.recycler_view_team)
    RecyclerView mTeamRecycler;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_transitions_explode);
        ButterKnife.bind(this);
        setupToolbar();
        setupRecyclerView();
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void setupRecyclerView() {
        mTeamRecycler.setHasFixedSize(true);
        String[] items = getResources().getStringArray(R.array.items);
        mTeamRecycler.setAdapter(new GridAdapter(items, onRecyclerItemClick));
    }

    private GridAdapter.OnRecyclerItemClick onRecyclerItemClick = new GridAdapter.OnRecyclerItemClick() {
        @Override
        public void onItemClick(View view) {
            Pair squareParticipant = new Pair<>(view, ViewCompat.getTransitionName(view));
            ActivityOptionsCompat transitionActivityOptions =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                            WindowTransitionsActivityExplode.this, squareParticipant);
            Intent intent = new Intent(
                    WindowTransitionsActivityExplode.this, SharedTransitionsInActionbarActivity.class);
            startActivity(intent, transitionActivityOptions.toBundle());
        }
    };

}
