package com.example.chyngyz.auapp.ui.transactions;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public interface TransactionManager {

    void replaceFragment(Fragment fragment, int container, boolean addToStack);

    void replaceFragmentWithArguments(Fragment fragment, int container, Bundle bundle, boolean addToStack);

    void addFragment(Fragment fragment, int container);

    void addFragmentWithArguments(Fragment fragment, int container, Bundle bundle);

    void removeFragment(Fragment fragment);

}
