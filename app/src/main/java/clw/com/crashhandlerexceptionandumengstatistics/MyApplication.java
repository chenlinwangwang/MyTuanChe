package clw.com.crashhandlerexceptionandumengstatistics;

import android.app.Application;
import android.content.Context;

import com.umeng.analytics.MobclickAgent;

/**
 * Created by admin on 2016/9/5.
 */
public class MyApplication extends Application {
    public static MyApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInStance().init();
        myApplication = this;
        initUmeng();
    }

    public void initUmeng() {
        MobclickAgent.setDebugMode(true);//开启调试模式（如果不开启调试模式不会上传Umeng统计）
        MobclickAgent.setCheckDevice(false);//不采集手机mac地址
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);//普通场景统计
    }

    public static MyApplication getMyApplication() {
        return myApplication;
    }

    public static Context getAppContext() {
        return myApplication.getMyApplication();
    }
}
