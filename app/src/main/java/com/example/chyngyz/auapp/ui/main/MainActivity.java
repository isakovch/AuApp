package com.example.chyngyz.auapp.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.chyngyz.auapp.AuApp;
import com.example.chyngyz.auapp.R;
import com.example.chyngyz.auapp.data.entity.Vacancy;
import com.example.chyngyz.auapp.di.main.MainModule;
import com.example.chyngyz.auapp.ui.details.DetailsActivity;
import com.example.chyngyz.auapp.utils.AndroidUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends DrawerActivity implements
        MainAdapter.MainAdapterCallback,
        MainContract.View,
        AdapterView.OnItemClickListener {

    @BindView(R.id.list_view)
    ListView mListView;

    @Inject
    MainContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDependency();
        init();
    }

    private void initDependency() {
        AuApp.getComponent()
                .include(new MainModule())
                .inject(this);
    }

    private void init() {
        mPresenter.bind(this);
        mPresenter.getAllVacancies(this);
    }

    @Override
    public void showAllVacancies(List<Vacancy> vacancyList) {
        mListView.setAdapter(new MainAdapter(this, vacancyList, new ArrayList<String>(), new ArrayList<String>(), this));
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void showGetVacanciesError(String msg) {
        AndroidUtils.showShortToast(this, msg);
    }

    @Override
    public void showActionMessage(String msg) {
        AndroidUtils.showShortToast(this, msg);
    }

    @Override
    public void showLoadingIndicator() {
        showProgressBar(null);
    }

    @Override
    public void hideLoadingIndicator() {
        dismissProgressBar();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unbind();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
        intent.putExtra("header", ((Vacancy) parent.getItemAtPosition(position)).getHeader());
        startActivity(intent);
    }

    @Override
    public void checkClicked(boolean isChecked, Vacancy vacancy) {
        mPresenter.saveOrDeleteVacancy(isChecked, vacancy);
    }
}
