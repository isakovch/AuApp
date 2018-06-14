package com.example.chyngyz.auapp.ui.transactions;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public final class FragmentTransactions implements TransactionManager {

    private FragmentManager mManager;

    public FragmentTransactions(FragmentManager manager) {
        this.mManager = manager;
    }

    @Override
    public void replaceFragment(Fragment fragment, int container, boolean addToStack) {
        FragmentTransaction transaction = mManager.beginTransaction();
        if (addToStack) transaction.addToBackStack(null);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.replace(container, fragment);
        transaction.commit();
    }

    @Override
    public void replaceFragmentWithArguments(Fragment fragment, int container, Bundle bundle, boolean addToStack) {
        fragment.setArguments(bundle);

        FragmentTransaction transaction = mManager.beginTransaction();
        if (addToStack) transaction.addToBackStack(null);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.replace(container, fragment);
        transaction.commit();
    }

    @Override
    public void addFragment(Fragment fragment, int container) {
        mManager.beginTransaction()
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .add(container, fragment)
                .commit();
    }

    @Override
    public void addFragmentWithArguments(Fragment fragment, int container, Bundle bundle) {
        fragment.setArguments(bundle);

        mManager.beginTransaction()
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .add(container, fragment)
                .commit();
    }

    @Override
    public void removeFragment(Fragment fragment) {
        mManager.beginTransaction()
                .remove(fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                .commit();
    }
}
