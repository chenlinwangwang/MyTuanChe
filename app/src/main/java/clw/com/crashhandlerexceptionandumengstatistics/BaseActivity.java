package clw.com.crashhandlerexceptionandumengstatistics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.umeng.analytics.MobclickAgent;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(MyApplication.getAppContext());
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(MyApplication.getAppContext());
    }
}
