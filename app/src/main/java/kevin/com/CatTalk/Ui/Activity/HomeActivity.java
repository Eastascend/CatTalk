package kevin.com.CatTalk.Ui.Activity;

import android.app.DownloadManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import kevin.com.CatTalk.R;
import kevin.com.CatTalk.Utils.StreamUtil;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private CoordinatorLayout mCoordinatorLayout;
    private AppBarLayout mAppBarLayout;
    private ImageView mImageView;
    private Toolbar mToolbar;
    private GridView gv_home_gridview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        gv_home_gridview = (MyGridView) findViewById(R.id.grid_view);
        gv_home_gridview.setAdapter(new Myadapter());

        tv_splash_versionname = (TextView) findViewById(R.id.tv_splash_versionname);
        tv_spalsh_plan = (TextView) findViewById(R.id.tv_spalsh_plan);
        /*tv_splash_versionname.setText("Version "+getVersionName());*/



        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        View headerLayout = navView.inflateHeaderView(R.layout.nav_header_home);
        ImageView iv = (ImageView) headerLayout.findViewById(R.id.iv_nav_header_touxiang);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
            }
        });

/*
* 滚动状态栏图片消失实现方法  Collapsing Toolbar实现
* */
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        mImageView = (ImageView) findViewById(R.id.backdrop);
/*        mToolbar.setTitle("CatTalk");*/
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MDStatusBarCompat.setCollapsingToolbar(this, mCoordinatorLayout, mAppBarLayout, mImageView, mToolbar);


/*
        //沉浸状态栏代码实现
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
*/


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        update();


    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    public void onBackPressed() {                                                         //后退键
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {           //创建菜单      点击MENU按钮的时候，调用该方法
        // Inflate the menu; this adds items to the action bar if it is present.  膨胀菜单; 如果存在，则会将项目添加到操作栏。
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {   //点击菜单当中的某一个选项时，会调用该方法
        // Handle action bar item clicks here. The action bar will     处理动作栏项目点击此处。 只要您在AndroidManifest.xml中指定了父活动，
        // automatically handle clicks on the Home/Up button, so long          操作栏就会自动处理Home / Up按钮上的点击。
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection Simplifiable If Statement                                     //简化if语句
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.    处理导航视图项目点击此处。
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class Myadapter extends BaseAdapter{
        int[] imageId = { R.drawable.safe, R.drawable.callmsgsafe, R.drawable.app,
                R.drawable.taskmanager, R.drawable.netmanager, R.drawable.trojan,
                R.drawable.sysoptimize, R.drawable.atools, R.drawable.settings };
        String[] names = { "手机防盗", "通讯卫士", "软件管理", "进程管理", "流量统计", "手机杀毒", "缓存清理",
                "高级工具", "设置中心" };

        //设置条目个数
        @Override
        public int getCount() {
            return 9;
        }

        //设置条目样式
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
/*            TextView textView = new TextView(getApplicationContext());
            textView.setText("第"+position+"个条目");//position代表条目位置 从0开始 0-8
            return textView;*/
            View view = View.inflate(getApplicationContext(), R.layout.item_home, null);
            //每个条目的样式都不一样,初始化控件,去设置控件的值
            //view.findViewById是从item_home布局文件中找控件,findViewById是从activity_home中找控件
            ImageView iv_itemhome_icon = (ImageView)view.findViewById(R.id.iv_itemhome_icon);
            TextView tv_itemhome_text = (TextView) view.findViewById(R.id.tv_itemhome_text);
            //设置控件的值
            iv_itemhome_icon.setImageResource(imageId[position]);//给imageview设置图片,根据条目的位置从图片数组中获取相应的图片
            tv_itemhome_text.setText(names[position]);
            return view;
        }


        //获取条目对应数据
        @Override
        public Object getItem(int position) {
            return null;
        }
        //获取条目ID

        @Override
        public long getItemId(int position) {
            return 0;
        }
    }


    protected static final int MSG_UPDATE_DIALOG = 1;
    protected static final int MSG_ENTER_HOME = 2;
    protected static final int MSG_SERVER_ERROR = 3;
    protected static final int MSG_URL_ERROR = 4;
    protected static final int MSG_IO_ERROR = 5;
    protected static final int MSG_JSON_ERROR = 6;
    private TextView tv_splash_versionname;
    private TextView tv_spalsh_plan;
    private String code;
    private String apkurl;
    private String des;
    private Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case MSG_UPDATE_DIALOG:
                    //弹出对话框
                    showdialog();
                    break;
                case MSG_ENTER_HOME:

                    break;
                case MSG_SERVER_ERROR:
                    //连接失败,服务器出现异常
                    Toast.makeText(getApplicationContext(), "服务器异常", Toast.LENGTH_SHORT).show();

                    break;
                case MSG_IO_ERROR:
                    Toast.makeText(getApplicationContext(), "亲,网络没有连接..", Toast.LENGTH_SHORT).show();

                    break;
                case MSG_URL_ERROR:
                    //方便我们后期定位异常
                    Toast.makeText(getApplicationContext(), "错误号:"+MSG_URL_ERROR, Toast.LENGTH_SHORT).show();

                    break;
                case MSG_JSON_ERROR:
                    Toast.makeText(getApplicationContext(), "错误号:"+MSG_JSON_ERROR, Toast.LENGTH_SHORT).show();

                    break;

            }
        }
    };






    /**
     * 弹出对话框
     */
    protected void showdialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //设置对话框不能消失
        builder.setCancelable(false);
        //设置对话框的标题
        builder.setTitle("新版本:"+code);
        //设置对话框的图标
        builder.setIcon(R.mipmap.ic_launcher);
        //设置对话框的描述信息
        builder.setMessage(des);
        //设置升级取消按钮
        builder.setPositiveButton("升级", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //3.下载最新版本
                download();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //1.隐藏对话框
                dialog.dismiss();
            }
        });
        //显示对话框
        //builder.create().show();//两种方式效果一样
        builder.show();
    }
    /**
     * 3.在最新版本
     */
    protected void download() {
        DownloadManager downloadManager = (DownloadManager)getSystemService(DOWNLOAD_SERVICE);

        String apkUrl = apkurl;

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(apkUrl));

        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"CatTalk.apk");
        downloadManager.enqueue(request);
        installAPK();



    }
    /**
     * 4.安装最新的版本
     */
    protected void installAPK() {
        /**
         *  <intent-filter>
         <action android:name="android.intent.action.VIEW" />
         <category android:name="android.intent.category.DEFAULT" />
         <data android:scheme="content" /> //content : 从内容提供者中获取数据  content://
         <data android:scheme="file" /> // file : 从文件中获取数据
         <data android:mimeType="application/vnd.android.package-archive" />
         </intent-filter>
         */
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        //单独设置会相互覆盖
		/*intent.setType("application/vnd.android.package-archive");
		intent.setData(Uri.fromFile(new File("/mnt/sdcard/mobliesafe75_2.apk")));*/
        intent.setDataAndType(Uri.fromFile(new File("/mnt/sdcard/mobliesafe_2.apk")), "application/vnd.android.package-archive");
        //在当前activity退出的时候,会调用之前的activity的onActivityResult方法
        //requestCode : 请求码,用来标示是从哪个activity跳转过来
        //ABC  a -> c    b-> c  ,c区分intent是从哪个activity传递过来的,这时候就要用到请求码
        startActivityForResult(intent, 0);
    }

    /**
     * 1.提醒用户更新版本
     */
    private void update() {
        //1.连接服务器,查看是否有最新版本,  联网操作,耗时操作,4.0以后不允许在主线程中执行的,放到子线程中执行
        new Thread(){

            private int startTime;

            public void run() {
                Message message = Message.obtain();
                //在连接之前获取一个时间
                startTime = (int) System.currentTimeMillis();
                try {
                    //1.1连接服务器
                    //1.1.1设置连接路径
                    //spec:连接路径
                    URL url = new URL("http://120.24.247.209/updateinfo.html");
                    //1.1.2获取连接操作
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();     //http协议,httpClient
                    //1.1.3设置超时时间
                    conn.setConnectTimeout(5000);//设置连接超时时间
                    //conn.setReadTimeout(5000);//设置读取超时时间
                    //1.1.4设置请求方式
                    conn.setRequestMethod("GET");//post
                    //1.1.5获取服务器返回的状态码,200,404,500
                    int responseCode = conn.getResponseCode();
                    if (responseCode == 200) {
                        //连接成功,获取服务器返回的数据,code : 新版本的版本号     apkurl:新版本的下载路径      des:描述信息,告诉用户增加了哪些功能,修改那些bug
                        //获取数据之前,服务器是如何封装数据xml  json
                        System.out.println("连接成功.....");
                        //获取服务器返回的流信息
                        InputStream in = conn.getInputStream();
                        //将获取到的流信息转化成字符串
                        String json = StreamUtil.parserStreamUtil(in);
                        //解析json数据
                        JSONObject jsonObject = new JSONObject(json);
                        //获取数据
                        code = jsonObject.getString("code");
                        apkurl = jsonObject.getString("apkurl");
                        des = jsonObject.getString("des");
                        System.out.println("code:"+code+"   apkurl:"+apkurl+"   des:"+des);
                        //1.2查看是否有最新版本
                        //判断服务器返回的新版本版本号和当前应用程序的版本号是否一致,一致表示没有最新版本,不一致表示有最新版本
                        if (code.equals(getVersionName())) {
                            //没有最新版本
                            message.what = MSG_ENTER_HOME;
                        }else{
                            //有最新版本
                            //2.弹出对话框,提醒用户更新版本
                            message.what = MSG_UPDATE_DIALOG;
                        }
                    }else{
                        //连接失败
                        System.out.println("连接失败.....");
                        message.what=MSG_SERVER_ERROR;
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    message.what = MSG_URL_ERROR;
                } catch (IOException e) {
                    e.printStackTrace();
                    message.what = MSG_IO_ERROR;
                } catch (JSONException e) {
                    e.printStackTrace();
                    message.what = MSG_JSON_ERROR;
                }finally{//不管有没有异常都会执行
                    //处理连接外网连接时间的问题
                    //在连接成功之后在去获取一个时间
                    int endTime = (int) System.currentTimeMillis();
                    //比较两个时间的时间差,如果小于两秒,睡两秒,大于两秒,不睡
                    int dTime = endTime-startTime;
                    if (dTime<2000) {
                        //睡两秒钟
                        SystemClock.sleep(2000-dTime);//始终都是睡两秒钟的时间
                    }
                    handler.sendMessage(message);
                }
            }
        }.start();
    }
    /**
     * 0.获取当前应用程序的版本号
     * @return
     */
    private String getVersionName(){
        //包的管理者,获取清单文件中的所有信息
        PackageManager pm = getPackageManager();
        try {
            //根据包名获取清单文件中的信息,其实就是返回一个保存有清单文件信息的javabean
            //packageName :应用程序的包名
            //flags : 指定信息的标签,0:获取基础的信息,比如包名,版本号,要想获取权限等等信息,必须通过标签来指定,才能去获取
            //GET_PERMISSIONS : 标签的含义:处理获取基础信息之外,还会额外获取权限的信息
            //getPackageName() : 获取当前应用程序的包名
            PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), 0);
            //获取版本号名称
            String versionName = packageInfo.versionName;
            return versionName;
        } catch (PackageManager.NameNotFoundException e) {
            //包名找不到的异常
            e.printStackTrace();
        }
        return null;
    }





}
