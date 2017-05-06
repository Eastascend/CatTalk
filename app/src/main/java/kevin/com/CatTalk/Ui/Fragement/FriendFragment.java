package kevin.com.CatTalk.Ui.Fragement;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Kevin Wong on 2017/5/6 0006.
 */

public class FriendFragment extends Fragment {

    public static FriendFragment instance =null;

    public static FriendFragment getInstance(){
        if (instance == null ){
            instance = new FriendFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        TextView tv = new TextView(getActivity());
        tv.setText("第三页");
        return tv;

    }

}
