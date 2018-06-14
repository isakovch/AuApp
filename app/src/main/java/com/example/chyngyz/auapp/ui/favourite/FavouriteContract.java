package com.example.chyngyz.auapp.ui.favourite;

import com.example.chyngyz.auapp.ui.IProgressBar;
import com.example.chyngyz.auapp.ui.Lifecycle;

public interface FavouriteContract {
    interface View extends IProgressBar {

    }

    interface Presenter extends Lifecycle<View> {

    }

}
