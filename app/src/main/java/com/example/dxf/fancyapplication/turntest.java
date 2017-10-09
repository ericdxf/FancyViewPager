package com.example.dxf.fancyapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class turntest extends Activity {
    /**
     * Called when the activity is first created.
     */
    private PageWidget pageWidget;
    private Handler timerHandler;
    int x = 206;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        pageWidget = new PageWidget(this);
        setContentView(pageWidget);

        timerHandler = new Handler();
        pageWidget.setPagePoint(x, 275);
        timerHandler.postDelayed(runable, 10);
    }

    Runnable runable = new Runnable() {
        @Override
        public void run() {
            pageWidget.pageTurn(x, 275);
            x -= 5;
            if (x > 0) {
                timerHandler.postDelayed(this, 10);
            }
        }
    };

}