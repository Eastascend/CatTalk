package kevin.com.CatTalk.Ui.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import kevin.com.CatTalk.R;

public class ConversationActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversation);
    }
}
