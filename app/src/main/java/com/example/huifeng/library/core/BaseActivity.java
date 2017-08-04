package com.example.huifeng.library.core;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.example.huifeng.library.utils.SharedPreferencesHelper;
import com.example.huifeng.library.utils.StatusBarTools;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * BaseActivity
 * Created by ShineF on 2016/12/5 0005.
 */

@SuppressLint("Registered")
public class BaseActivity extends FragmentActivity {

    public Context mContext;
    public SharedPreferencesHelper sp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeActivityCreate();
        setContentView(setContentLayout());
        mContext = this;
        sp = MobileApplication.sp;
        ButterKnife.bind(this);
        StatusBarTools.setTransparencyBar(this);
        StatusBarTools.setStatusBarLightMode(this);
        init();
    }

    public int setContentLayout() {
        return 0;
    }

    public void init() {

    }


    public void beforeActivityCreate() {

    }


    public void startActivity(Class<? extends Activity> target, String passDataKey, boolean value, boolean finish) {
        if (Common.getInstance().isNotFastClick()) {
            Intent intent = new Intent(this, target);
            if (passDataKey != null) {
                intent.putExtra(passDataKey, value);
            }
            startActivity(intent);
            if (finish) {
                finish();
            }
        }
    }

    public void startActivity(Class<? extends Activity> target, String passDataKey, ArrayList<String> list, boolean finish) {
        if (Common.getInstance().isNotFastClick()) {
            Intent intent = new Intent(this, target);
            if (passDataKey != null) {
                intent.putStringArrayListExtra(passDataKey, list);
            }
            startActivity(intent);
            if (finish) {
                finish();
            }
        }
    }

    public void startActivityForResult(Class<? extends Activity> target, int requestCode, Bundle bundle) {
        if (Common.getInstance().isNotFastClick()) {
            Intent intent = new Intent(this, target);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            startActivityForResult(intent, requestCode);
        }
    }


    public void startActivity(Class<? extends Activity> target, Bundle bundle, boolean finish) {
        if (Common.getInstance().isNotFastClick()) {
            Intent intent = new Intent(this, target);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            startActivity(intent);
            if (finish) {
                finish();
            }
        }
    }

    public void startActivity(Class<? extends Activity> target, String passDataKey, String value, boolean finish) {
        if (Common.getInstance().isNotFastClick()) {
            Intent intent = new Intent(this, target);
            if (passDataKey != null && value != null) {
                intent.putExtra(passDataKey, value);
            }
            startActivity(intent);
            if (finish) {
                finish();
            }
        }
    }


    /**
     * 不可取消的dialog
     */
    public void notCancelDialog(String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage(text);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        Dialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();
    }

}
