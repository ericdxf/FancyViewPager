package com.example.dxf.fancyapplication;

import android.app.Application;
import android.content.Context;

/**
 * 应用程序入口
 * Created by mdw on 2016/4/19.
 */
public class App extends Application {


    private static Context sContext;

    public static App sApp;


    @Override
    public void onCreate() {
        super.onCreate();

        sContext = this;
        sApp = this;
        //toast 初始化
    }

    /**
     * 获取上下文对象
     * @return
     */
    public static Context getContext(){
        return sContext;
    }


}
