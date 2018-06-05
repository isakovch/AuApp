package com.example.chyngyz.auapp.ui;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

import com.example.chyngyz.auapp.R;
import com.example.chyngyz.auapp.ui.main.DrawerActivity;

public abstract class BaseActivity extends AppCompatActivity {

    private ProgressDialog mDialog;

    protected void showProgressBar(String text) {
        if (mDialog == null) {
            mDialog = new ProgressDialog(this);
            mDialog.setMessage(text == null ? getString(R.string.please_wait) : text);
            mDialog.show();
        }
    }

    protected void dismissProgressBar() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
        mDialog = null;
    }
}
