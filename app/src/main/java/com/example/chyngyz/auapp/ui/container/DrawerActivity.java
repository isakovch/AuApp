package com.example.chyngyz.auapp.ui.container;

import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.chyngyz.auapp.BuildConfig;
import com.example.chyngyz.auapp.R;
import com.example.chyngyz.auapp.ui.BaseActivity;
import com.example.chyngyz.auapp.ui.favourite.FavouriteFragment;
import com.example.chyngyz.auapp.ui.main.MainFragment;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class DrawerActivity extends BaseActivity implements
        Drawer.OnDrawerItemClickListener,
        FragmentManager.OnBackStackChangedListener,
        View.OnClickListener {

    private Drawer mDrawer;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private Unbinder mUnbinder;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        mUnbinder = ButterKnife.bind(this);
        init();
    }

    private void init() {
        initToolbar();
        initDrawer();
        getSupportFragmentManager().addOnBackStackChangedListener(this);
    }

    private void initDrawer() {
        AccountHeader header = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.color.colorPrimaryViolet)
                .addProfiles(
                        getProfileDrawerItem(
                                R.drawable.logo,
                                R.string.title_all_kg_vacancies,
                                BuildConfig.VERSION_NAME)
                )
                .build();


        mDrawer = new DrawerBuilder()
                .withAccountHeader(header)
                .withActivity(this)
                .withSelectedItem(-1)
                .withToolbar(mToolbar)
                .withOnDrawerItemClickListener(this)
                .withActionBarDrawerToggle(true)
                .withActionBarDrawerToggleAnimated(true)
                .addDrawerItems(
                        getDrawerItem(R.drawable.ic_new_releases_black_24dp, R.string.title_new_vacancies, 1),
                        getDrawerItem(R.drawable.ic_star_black_24dp, R.string.title_favourite_vacancies, 2),
                        getDrawerItem(R.drawable.ic_exit_to_app_black_24dp, R.string.title_exit, 3)
                )
                .build();
    }

    @Override
    public void onClick(View v) {
        boolean isRoot = getSupportFragmentManager().getBackStackEntryCount() == 0;
        if (!isRoot)
            onBackPressed();
        else {
            mDrawer.openDrawer();
        }
    }

    @Override
    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
        switch ((int) drawerItem.getIdentifier()) {
            case 1:
                getTransactionManager().replaceFragment(new MainFragment(), R.id.fragment_container, false);
                break;
            case 2:
                getTransactionManager().replaceFragment(new FavouriteFragment(), R.id.fragment_container, false);
                break;
            case 3:
                finish();
                break;
        }

        return false;
    }

    private void initToolbar() {
        mToolbar.setNavigationOnClickListener(this);

        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }
    }

    private PrimaryDrawerItem getDrawerItem(int icon, int drawerTitle, int identifier) {
        return new PrimaryDrawerItem()
                .withIcon(icon)
                .withName(drawerTitle)
                .withIdentifier(identifier);
    }

    private ProfileDrawerItem getProfileDrawerItem(int icon, int accountHeaderTitle, String appVersion) {
        return new ProfileDrawerItem()
                .withIcon(icon)
                .withName(accountHeaderTitle)
                .withEmail(appVersion);
    }

    @Override
    public void onBackStackChanged() {
        updateDrawerToggle();
    }

    private void updateDrawerToggle() {
        if (mDrawer.getActionBarDrawerToggle() == null) {
            return;
        }
        boolean isRoot = getSupportFragmentManager().getBackStackEntryCount() == 0;

        mDrawer.getActionBarDrawerToggle().setDrawerIndicatorEnabled(isRoot);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(!isRoot);
            getSupportActionBar().setDisplayHomeAsUpEnabled(!isRoot);
            getSupportActionBar().setHomeButtonEnabled(!isRoot);
        }

        if (isRoot) {
            mDrawer.getActionBarDrawerToggle().syncState();
        } else {
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }
    }

    @Override
    public void onBackPressed() {
        if (mDrawer != null && mDrawer.isDrawerOpen()) {
            mDrawer.closeDrawer();
            return;
        }

        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setTitle(R.string.app_name);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) mUnbinder.unbind();
    }
}
