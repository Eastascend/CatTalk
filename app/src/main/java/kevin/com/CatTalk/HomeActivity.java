package kevin.com.CatTalk;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
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

import kevin.com.CatTalk.Ui.MDStatusBarCompat;
import kevin.com.CatTalk.Ui.MyGridView;

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
        mToolbar.setTitle("CatTalk");
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





}
