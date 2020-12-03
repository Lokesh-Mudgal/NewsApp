package com.newsapp.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

/**
 * Created by Lokesh Mudgal on 1/12/20.
 */
public abstract class BaseActivity extends AppCompatActivity {

    public abstract void inject();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        inject();
    }

    void addFragment( Fragment fragment, int layoutId, boolean addToBackStack){
        fragmentTransaction(fragment, FragmentTransactionType.ADD, layoutId, addToBackStack);
    }

    void replaceFragment(Fragment fragment, int layoutId, boolean addToBackStack){
        fragmentTransaction(fragment, FragmentTransactionType.REPLACE, layoutId, addToBackStack);
    }

    void fragmentTransaction(Fragment fragment, FragmentTransactionType fragmentTransactionType, int containerId, Boolean addToBackStack){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (fragmentTransactionType){
            case ADD:
                fragmentTransaction.add(containerId, fragment);
                break;
            case REPLACE:
                fragmentTransaction.replace(containerId, fragment);
                break;
        }

        if (addToBackStack)
            fragmentTransaction.addToBackStack(fragment.getTag());

        fragmentTransaction.commitAllowingStateLoss();
    }

    public enum FragmentTransactionType{
        ADD, REPLACE
    }
}
