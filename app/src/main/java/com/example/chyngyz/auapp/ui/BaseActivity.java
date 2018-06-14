package com.example.chyngyz.auapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.chyngyz.auapp.ui.transactions.TransactionManager;
import com.example.chyngyz.auapp.ui.transactions.FragmentTransactions;

public abstract class BaseActivity extends AppCompatActivity {

    private TransactionManager mTransactionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTransactionManager = new FragmentTransactions(getSupportFragmentManager());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected TransactionManager getTransactionManager() {
        return mTransactionManager;
    }

    protected void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
