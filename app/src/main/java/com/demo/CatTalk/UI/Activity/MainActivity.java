package com.demo.CatTalk.UI.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.demo.CatTalk.Friend;
import com.demo.CatTalk.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.ipc.PushMessageReceiver;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;
import io.rong.notification.PushNotificationMessage;

public class MainActivity extends Activity implements View.OnClickListener, RongIM.UserInfoProvider {

    private static final String token1 = "hVAMGA1LGAGHOnj4MyZkUdwvd8vGViBg1AapQLIDyvTxPQMyDf4p+bizea4pXC/2UCA4xY8M+D2SUKgm8Cp2Qg==";
    private static final String token2 = "YrwjdogQi45WMgBoPdMLLirD4LRJ/eEpnIkH0D19tj4M/Zh1CypmksLDGHzUK5v0f1ajGEUFyyqMGgad05GGuw==";

    private List<Friend> userIdList;
    private static final String TAG = "MainActivity";

    private Button mUser1, mUser2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUser1 = (Button) findViewById(R.id.connect_10010);
        mUser2 = (Button) findViewById(R.id.connect_10086);
        mUser1.setOnClickListener(this);
        mUser2.setOnClickListener(this);


        initUserInfo();

    }

    private void connectRongServer(String token) {

        RongIM.connect(token, new RongIMClient.ConnectCallback() {


            @Override
            public void onSuccess(String userId) {

                if (userId.equals("10010")) {
                    mUser1.setText("用户1连接服务器成功");
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                    Toast.makeText(MainActivity.this, "connect server success 10010", Toast.LENGTH_SHORT).show();

                } else {

                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                    Toast.makeText(MainActivity.this, "connect server success 10086", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                // Log.e("onError", "onError userid:" + errorCode.getValue());//获取错误的错误码
                Log.e(TAG, "connect failure errorCode is : " + errorCode.getValue());
            }


            @Override
            public void onTokenIncorrect() {
                Log.e(TAG, "token is error ,please check token and appkey");
            }
        });

    }


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.connect_10010) {
            connectRongServer(token1);
        } else if (v.getId() == R.id.connect_10086) {
            connectRongServer(token2);
        }
    }


    private void initUserInfo() {
        userIdList = new ArrayList<Friend>();
        userIdList.add(new Friend("10010", "联通", "http://www.51zxw.net/bbs/UploadFile/2013-4/201341122335711220.jpg"));//联通图标
        userIdList.add(new Friend("10086", "移动", "http://img02.tooopen.com/Download/2010/5/22/20100522103223994012.jpg"));//移动图标
        userIdList.add(new Friend("KEFU149406927435582","在线客服","http://img02.tooopen.com/Download/2010/5/22/20100522103223994012.jpg"));
        RongIM.setUserInfoProvider(this, true);
    }

    @Override
    public UserInfo getUserInfo(String s) {

        for (Friend i : userIdList) {
            if (i.getUserId().equals(s)) {
                Log.e(TAG, i.getPortraitUri());
                return new UserInfo(i.getUserId(), i.getName(), Uri.parse(i.getPortraitUri()));
            }
        }


        Log.e("MainActivity", "UserId is : " + s);
        return null;
    }


    /**
     * <p>启动会话界面。</p>
     * <p>使用时，可以传入多种会话类型 {@link io.rong.imlib.model.Conversation.ConversationType} 对应不同的会话类型，开启不同的会话界面。
     * 如果传入的是 {@link io.rong.imlib.model.Conversation.ConversationType#CHATROOM}，sdk 会默认调用
     * {@link RongIMClient#joinChatRoom(String, int, RongIMClient.OperationCallback)} 加入聊天室。
     * 如果你的逻辑是，只允许加入已存在的聊天室，请使用接口 {@link #startChatRoomChat(Context, String, boolean)} 并且第三个参数为 true</p>
     *
     * @param context          应用上下文。
     * @param conversationType 会话类型。
     * @param targetId         根据不同的 conversationType，可能是用户 Id、讨论组 Id、群组 Id 或聊天室 Id。
     * @param title            聊天的标题，开发者可以在聊天界面通过 intent.getData().getQueryParameter("title") 获取该值, 再手动设置为标题。
     */
    public void startConversation(Context context, Conversation.ConversationType conversationType, String targetId, String title){

    }


    /**
     * 启动会话列表界面。
     *
     * @param context               应用上下文。
     * @param supportedConversation 定义会话列表支持显示的会话类型，及对应的会话类型是否聚合显示。
     *                              例如：supportedConversation.put(Conversation.ConversationType.PRIVATE.getName(), false) 非聚合式显示 private 类型的会话。
     */
    public void startConversationList(Context context, Map<String, Boolean> supportedConversation){

    }

    /**
     * 启动聚合后的某类型的会话列表。<br> 例如：如果设置了单聊会话为聚合，则通过该方法可以打开包含所有的单聊会话的列表。
     *
     * @param context          应用上下文。
     * @param conversationType 会话类型。
     */
    public void startSubConversationList(Context context, Conversation.ConversationType conversationType){

    }



/**
 自定义广播接收器
 * */

    public class SealNotificationReceiver extends PushMessageReceiver {

        public boolean onNotificationMessageArrived(Context context, PushNotificationMessage message) {
            return false; // 返回 false, 会弹出融云 SDK 默认通知; 返回 true, 融云 SDK 不会弹通知, 通知需要由您自定义。
        }


        public boolean onNotificationMessageClicked(Context context, PushNotificationMessage message) {
            return false; // 返回 false, 会走融云 SDK 默认处理逻辑, 即点击该通知会打开会话列表或会话界面; 返回 true, 则由您自定义处理逻辑。
        }

    }

    /**断开连接
     * <p>断开与融云服务器的连接。当调用此接口断开连接后，仍然可以接收 Push 消息。</p>
     * <p>若想断开连接后不接受 Push 消息，可以调用{@link #logout()}</p>
     */
    public void disconnect(){

    }
   // 如果断开连接后，有新消息时，不想收到任何推送通知，调用 logout() 方法。

    /**
     * <p>断开与融云服务器的连接，并且不再接收 Push 消息。</p>
     * <p>若想断开连接后仍然接受 Push 消息，可以调用 {@link #disconnect()}</p>
     */
    public void logout(){

    }






}
