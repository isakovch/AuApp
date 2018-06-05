package com.example.chyngyz.auapp.ui.details;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.chyngyz.auapp.ui.BaseActivity;
import com.example.chyngyz.auapp.utils.AndroidUtils;

public class DetailsActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String header = getIntent().getStringExtra("header");
        AndroidUtils.showToast(this, "DETAILS OPENED " + header);
    }
}
