package kevin.com.CatTalk.Ui.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import kevin.com.CatTalk.R;
import kevin.com.CatTalk.Server.UserService;
import kevin.com.CatTalk.Server.UserServiceImpl;

import static kevin.com.CatTalk.R.id.et_name;
import static kevin.com.CatTalk.R.id.et_password;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity  {


    private EditText name;
    private EditText password;
    private ImageView mImg_Background;
    private Button bt_login;

    private UserService userService = new UserServiceImpl();




    private void initView() {

        name = (EditText) findViewById(et_name);
        password = (EditText) findViewById(et_password);
        findViewById(R.id.bt_login).setOnClickListener(mOnClickListener);

        mImg_Background = (ImageView) findViewById(R.id.de_img_backgroud);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation animation = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.translate_anim);
                mImg_Background.startAnimation(animation);
            }
        }, 200);
    }


    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

    }


    private View.OnClickListener mOnClickListener = new View.OnClickListener() {


        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.bt_login://登录
                    final String userName = name.getText().toString();
                    final String userPwd = password.getText().toString();
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();

                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                userService.userLogin(userName,userPwd);

                            } catch (Exception e ){
                                e.printStackTrace();

                            }

                        }
                    });

                    thread.start();



                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);//跳转到主页
                    startActivity(intent);
                    finish();
                    break;
            }

        }
    };

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };








}

