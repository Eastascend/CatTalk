package kevin.com.CatTalk.Ui.Activity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;
import kevin.com.CatTalk.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    protected static final String TAG = "MainActivity";
    private List<Friend> userIdList;
    private Button mUser1, mUser2;


    private static final int GO_HOME = 0;//去主页
    private static final int GO_LOGIN = 1;//去登录页

    private static final String Token1 = "M5GvTNA4L9DWoIAQ4yHIFtwvd8vGViBg1AapQLIDyvTxPQMyDf4p+Umxw4iNrkG5yFNvIQ4ISniQ77EmISOKNg==";
    private static final String Token2 = "BRvx0aB34b3Ux8hSc0CivCrD4LRJ/eEpnIkH0D19tj4M/Zh1CypmkqGc9U/u+Eusxv6ec5/rU7Pa9/qsGjOaCA==";

    /*"userId":"0001",   name1 ,  "token":"M5GvTNA4L9DWoIAQ4yHIFtwvd8vGViBg1AapQLIDyvTxPQMyDf4p+Umxw4iNrkG5yFNvIQ4ISniQ77EmISOKNg=="*/
    /* "userId":"0002",  name2 ,  "token":"BRvx0aB34b3Ux8hSc0CivCrD4LRJ/eEpnIkH0D19tj4M/Zh1CypmkqGc9U/u+Eusxv6ec5/rU7Pa9/qsGjOaCA=="*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);*/  //代码实现全屏显示
        setContentView(R.layout.activity_main);

        mUser1 = (Button) findViewById(R.id.bt_connect_0001);
        mUser2 = (Button) findViewById(R.id.bt_connect_0002);

        mUser1.setOnClickListener(this);
        mUser2.setOnClickListener(this);

        initUserInfo();

    }

    /**
    *连接融云服务器
    */
    private void connectRongServer(String token) {
        RongIM connect = RongIM.connect(Token1, new RongIMClient.ConnectCallback() {
            @Override
            public void onSuccess(String userId) {
                if (userId.equals("0001")){
                    mUser1.setText("用户1连接服务器成功");
                    startActivity(new Intent(MainActivity.this,HomeActivity.class));
                    Toast.makeText(MainActivity.this,"connect server success 0001",Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(MainActivity.this,HomeActivity.class));
                    Toast.makeText(MainActivity.this,"connect server success 0002",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.e("onSuccess", "userId:" + errorCode.getValue());

            }

            @Override
            public void onTokenIncorrect() {
                Log.e(TAG,"token is error , please check token and appkey");

            }
        });

    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.bt_connect_0001) {
            connectRongServer(Token1);
        }else if (v.getId() == R.id.bt_connect_0002){
            connectRongServer(Token2);
        }
    }


    private void initUserInfo() {
        userIdList = new ArrayList<Friend>();
        userIdList.add(new Friend("0001","用户1","http://www.rongcloud.cn/images/logo.png"));
        userIdList.add(new Friend("0002","用户2","http://www.rongcloud.cn/images/logo.png"));
        RongIM.setUserInfoProvider((RongIM.UserInfoProvider) this,true);

    }

    public UserInfo getUserInfo(String s) {
        for (Friend i : userIdList) {
            if (i.getUserId().equals(s)){
                Log.e(TAG,i.getPortraitUri());
                return new UserInfo(i.getUserId(),i.getUserName(), Uri.parse(i.getPortraitUri()));
            }
        }
        Log.e("MainActivity","UserId is : " + s );
        return null;


    }
}

