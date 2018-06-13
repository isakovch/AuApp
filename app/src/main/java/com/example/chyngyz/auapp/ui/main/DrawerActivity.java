package com.example.chyngyz.auapp.ui.main;

import android.support.v7.widget.Toolbar;

import com.example.chyngyz.auapp.BuildConfig;
import com.example.chyngyz.auapp.R;
import com.example.chyngyz.auapp.ui.BaseActivity;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class DrawerActivity extends BaseActivity {

    private Drawer mDrawer;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private Unbinder mUnbinder;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        mUnbinder = ButterKnife.bind(this);

        initDrawer();
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
                .withToolbar(mToolbar)
                .withActionBarDrawerToggle(true)
                .withActionBarDrawerToggleAnimated(true)
                .addDrawerItems(
                        getDrawerItem(R.drawable.ic_star_black_24dp, R.string.title_favourite_vacancies, 1),
                        getDrawerItem(R.drawable.ic_exit_to_app_black_24dp, R.string.title_exit, 2)
                )
                .build();
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
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) mUnbinder.unbind();
    }
}
