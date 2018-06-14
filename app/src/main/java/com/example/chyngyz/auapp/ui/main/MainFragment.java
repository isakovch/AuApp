package com.example.chyngyz.auapp.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.chyngyz.auapp.AuApp;
import com.example.chyngyz.auapp.R;
import com.example.chyngyz.auapp.data.entity.Vacancy;
import com.example.chyngyz.auapp.di.main.MainModule;
import com.example.chyngyz.auapp.ui.BaseFragment;
import com.example.chyngyz.auapp.ui.container.DrawerActivity;
import com.example.chyngyz.auapp.ui.details.DetailsActivity;
import com.example.chyngyz.auapp.utils.AndroidUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class MainFragment extends BaseFragment implements
        MainAdapter.MainAdapterCallback,
        MainContract.View,
        AdapterView.OnItemClickListener {

    @BindView(R.id.list_view)
    ListView mListView;

    @Inject
    MainContract.Presenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDependency();
        init();

        setRetainInstance(true);
    }

    @Override
    protected int getViewLayout() {
        return R.layout.fragment_main;
    }

    private void initDependency() {
        AuApp.getComponent()
                .include(new MainModule())
                .inject(this);
    }

    private void init() {
        mPresenter.bind(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.getAllVacancies(getCheckedActivity());
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    public void showAllVacancies(List<Vacancy> vacancyList) {
        mListView.setAdapter(new MainAdapter(getCheckedActivity(), vacancyList, new ArrayList<String>(), new ArrayList<String>(), this));
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void showGetVacanciesError(String msg) {
        AndroidUtils.showShortToast(getCheckedActivity(), msg);
    }

    @Override
    public void showActionMessage(String msg) {
        AndroidUtils.showShortToast(getCheckedActivity(), msg);
    }

    @Override
    public void showLoadingIndicator() {
        showProgressBar();
    }

    @Override
    public void hideLoadingIndicator() {
        dismissProgressBar();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unbind();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getCheckedActivity(), DetailsActivity.class);
        startActivity(intent);
    }

    @Override
    public void checkClicked(boolean isChecked, Vacancy vacancy) {
        mPresenter.saveOrDeleteVacancy(isChecked, vacancy);
    }
}
