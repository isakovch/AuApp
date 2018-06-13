package com.example.chyngyz.auapp.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;

import com.example.chyngyz.auapp.R;

public abstract class BaseActivity extends AppCompatActivity {

//    private ProgressDialog mDialog;
    private ProgressBar mProgressBar;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        mProgressBar = findViewById(R.id.progressBar);
        mProgressBar.setIndeterminate(true);
    }

    protected void showProgressBar(String text) {
        /*if (mDialog == null) {
            mDialog = new ProgressDialog(this);
            mDialog.setMessage(text == null ? getString(R.string.action_please_wait) : text);
            mDialog.show();
        }*/
        mProgressBar.setVisibility(View.VISIBLE);

    }

    protected void dismissProgressBar() {
        /*if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
        mDialog = null;*/
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
