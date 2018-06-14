package com.example.chyngyz.auapp.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.chyngyz.auapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    private Unbinder mUnbinder;

    private FragmentActivity mFragmentActivity;

    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;


    protected abstract int getViewLayout();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() != null) mFragmentActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getViewLayout(), container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mProgressBar.setIndeterminate(true);
    }

    protected FragmentActivity getCheckedActivity() {
        return mFragmentActivity;
    }

    protected void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);

    }

    protected void dismissProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
