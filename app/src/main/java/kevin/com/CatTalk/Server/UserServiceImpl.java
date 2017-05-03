package kevin.com.CatTalk.Server;

import android.util.Log;

/**
 * Created by Kevin Wong on 2017/5/1 0001.
 */

public class UserServiceImpl implements UserService{

    private static final String TAG = "UserServiceImpl";

    @Override
    public void userLogin(String userName, String userPwd) throws Exception {

        Log.d(TAG,userName);
        Log.d(TAG,userPwd);

    }
}
