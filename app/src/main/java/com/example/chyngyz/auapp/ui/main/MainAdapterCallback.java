package com.example.chyngyz.auapp.ui.main;

import com.example.chyngyz.auapp.data.entity.Vacancy;

public interface MainAdapterCallback {
    void checkClicked(boolean isChecked, Vacancy vacancy);
}
