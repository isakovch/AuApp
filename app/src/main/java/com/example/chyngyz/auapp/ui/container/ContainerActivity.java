package com.example.chyngyz.auapp.ui.container;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.chyngyz.auapp.R;
import com.example.chyngyz.auapp.ui.main.MainFragment;

public class ContainerActivity extends DrawerActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        if (savedInstanceState == null) {
            getTransactionManager().replaceFragment(
                    new MainFragment(),
                    R.id.fragment_container,
                    false);
        }
    }
}
