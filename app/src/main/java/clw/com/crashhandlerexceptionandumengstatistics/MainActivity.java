package clw.com.crashhandlerexceptionandumengstatistics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        CrashHandler.getInStance().init();
//        String str=null;
//        str.equals("我");
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                HashMap<String,String> map = new HashMap<String,String>();
//                map.put("type","book");
//                map.put("quantity","3");
                MobclickAgent.onEvent(MainActivity.this,"120");
            }
        });
    }
}
