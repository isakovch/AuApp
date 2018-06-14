package com.example.chyngyz.auapp.ui.favourite;

import com.example.chyngyz.auapp.R;
import com.example.chyngyz.auapp.ui.BaseFragment;

public class FavouriteFragment extends BaseFragment {
    @Override
    protected int getViewLayout() {
        return R.layout.fragment_favourite;
    }

    @Override
    public void onResume() {
        super.onResume();
        getCheckedActivity().setTitle(R.string.title_favourite_vacancies);
    }
}
