package com.twiceyuan.autoform.sample;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.twiceyuan.autoform.FormManager;

public class MainActivity extends AppCompatActivity {

    private static final int MENU_SUBMIT = 1001;

    private FrameLayout mForm;
    private FormManager mFormManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        mFormManager = FormManager.build(mForm, DemoForm.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, MENU_SUBMIT, 0, R.string.submit).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == MENU_SUBMIT) {

            if (!mFormManager.validate()) {
                return true;
            }

            new AlertDialog.Builder(this)
                    .setMessage(String.valueOf(mFormManager.getResult()))
                    .setPositiveButton("确定", null)
                    .show();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        mForm = (FrameLayout) findViewById(R.id.form);
    }
}