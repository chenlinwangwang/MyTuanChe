package clw.com.crashhandlerexceptionandumengstatistics;

import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Created by admin on 2016/9/5.
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {


    private final static String TAG = "crashHandle";
    private static CrashHandler crashHandler;
    private Thread.UncaughtExceptionHandler defaultHandler;

    private CrashHandler() {

    }

    public static CrashHandler getInStance() {
        if (crashHandler == null) {
            crashHandler = new CrashHandler();
        }
        return crashHandler;
    }

    /**
     * 初始化
     */
    public void init() {
        //获取一个系统的获取异常类
        defaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        //告诉系统自己也可以捕获异常
        Thread.setDefaultUncaughtExceptionHandler(crashHandler);
    }

    /**
     * 捕获异常方法
     *
     * @param thread
     * @param throwable
     */

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        if (!handlerException(throwable) && defaultHandler != null) {
            //表示交给系统处理
            defaultHandler.uncaughtException(thread, throwable);
        } else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Looper.prepare();
                    Toast.makeText(MyApplication.getAppContext(),"程序发生错误",Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }).start();
        }
    }

    /**
     * * 自定义错误处理,收集错误信息1q
     * * 发送错误报告等操作均在此完成.
     * * 开发者可以根据自己的情况来自定义异常处理逻辑
     * * @return
     * * true代表处理该异常，不再向上抛异常，
     * * false代表不处理该异常(可以将该log信息存储起来)然后交给上层(这里就到了系统的异常处理)去处理，
     * * 简单来说就是true不会弹出那个错误提示框，false就会弹出
     */
    public boolean handlerException(Throwable throwable) {

        if (throwable == null)
            return false;
        String err_msg = getStack(throwable);
        Log.e(TAG, "错误日志: " + err_msg);
        return true;
    }

    public String getStack(Throwable throwable) {
        String err_msg = "";
        ByteArrayOutputStream outputStream = null;
        PrintStream printStream = null;

        try {
            outputStream = new ByteArrayOutputStream();
            printStream = new PrintStream(outputStream);
            throwable.printStackTrace(printStream);
            outputStream.close();
            printStream.close();
            err_msg = outputStream.toString();
        } catch (Exception e) {
            e.toString();
        }
        return err_msg;
    }
}
