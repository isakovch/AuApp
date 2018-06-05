package com.example.chyngyz.auapp.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.chyngyz.auapp.AuApp;
import com.example.chyngyz.auapp.R;
import com.example.chyngyz.auapp.data.entity.Vacancy;
import com.example.chyngyz.auapp.ui.details.DetailsActivity;
import com.example.chyngyz.auapp.utils.AndroidUtils;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends DrawerActivity implements MainContract.View, AdapterView.OnItemClickListener {

    @BindView(R.id.list_view)
    ListView mListView;

    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void init() {
        mPresenter = new MainPresenter(AuApp.get(this).getService());
        mPresenter.bind(this);
        mPresenter.getAllVacancies();
    }

    @Override
    public void showAllVacancies(ArrayList<Vacancy> vacancyList) {
        mListView.setAdapter(new MainAdapter(this, vacancyList, new ArrayList<String>(), new ArrayList<String>()));
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void showGetVacanciesError(String msg) {
        AndroidUtils.showToast(this, msg);
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
}
