package am.highapps.mvptest.ui.main;

import android.os.Bundle;

import am.highapps.mvptest.R;
import am.highapps.mvptest.base.BaseActivity;
import am.highapps.mvptest.util.ActivityUtil;

public class MainActivity extends BaseActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            ActivityUtil.addFragmentToActivity(
                    getSupportFragmentManager(),
                    MainFragmentActions.newInstance(),
                    R.id.fl_main_container);
        }

    }

}
