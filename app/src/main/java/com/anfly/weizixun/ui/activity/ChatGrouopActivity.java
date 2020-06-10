package com.anfly.weizixun.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anfly.weizixun.R;
import com.anfly.weizixun.adapter.EMMessageAdapter;
import com.anfly.weizixun.common.Constants;
import com.anfly.weizixun.utils.SharedPreferencesUtils;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMGroup;
import com.hyphenate.chat.EMGroupManager;
import com.hyphenate.chat.EMGroupOptions;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.exceptions.HyphenateException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatGrouopActivity extends AppCompatActivity {

    @BindView(R.id.btn_create_group)
    Button btnCreateGroup;
    @BindView(R.id.et_group_id)
    EditText etGroupId;
    @BindView(R.id.btn_join_group)
    Button btnJoinGroup;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.et_group_conent)
    EditText etGroupConent;
    @BindView(R.id.btn_send_conent)
    Button btnSendConent;
    private String groupId;
    private ArrayList<EMMessage> list;
    private String curName;
    private EMMessageAdapter adapter;
    private String mGroupId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_grouop);
        ButterKnife.bind(this);
        initView();
        intReceiver();
    }

    private void intReceiver() {

        EMMessageListener mMsgListener = new EMMessageListener() {
            @Override
            public void onMessageReceived(final List<EMMessage> messages) {
                //收到消息,任何消息都可以接受到的,需要过滤,只处理这个群的消息
                if (messages != null && messages.size() > 0) {
                    final ArrayList<EMMessage> arrayList = new ArrayList<>();
                    for (int i = 0; i < messages.size(); i++) {
                        EMMessage emMessage = messages.get(i);
                        Log.d("TAG", "run: " + emMessage.toString());
                        String to = emMessage.getTo();
                        //区分是否是这个群的消息
//                        if (!TextUtils.isEmpty(mGroupId) && mGroupId.equals(to)) {
                            arrayList.add(emMessage);
//                        }
                    }

                    //子线程
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            list.addAll(arrayList);
                            adapter.notifyDataSetChanged();
                        }
                    });

                }
            }

            @Override
            public void onCmdMessageReceived(List<EMMessage> messages) {
                //收到透传消息
            }

            @Override
            public void onMessageRead(List<EMMessage> messages) {
                //收到已读回执
            }

            @Override
            public void onMessageDelivered(List<EMMessage> message) {
                //收到已送达回执
            }

            @Override
            public void onMessageRecalled(List<EMMessage> messages) {
                //消息被撤回
            }

            @Override
            public void onMessageChanged(EMMessage message, Object change) {
                //消息状态变动
            }
        };
        EMClient.getInstance().chatManager().addMessageListener(mMsgListener);
    }


    private void initView() {
        curName = (String) SharedPreferencesUtils.getParam(this, Constants.NAME, "a");
        list = new ArrayList<>();
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EMMessageAdapter(list, this, "", curName);
        rv.setAdapter(adapter);
        adapter.setOnItemClick(new EMMessageAdapter.OnItemClick() {
            @Override
            public void onItemClick(String localUrl) {
//                playAudio(localUrl);
            }
        });
    }

    @OnClick({R.id.btn_create_group, R.id.btn_join_group, R.id.btn_send_conent})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_create_group:
                createGroup();
                break;
            case R.id.btn_join_group:
                joinGroup();
                break;
            case R.id.btn_send_conent:
                send();
                break;
        }
    }

    private void send() {

        String content = etGroupConent.getText().toString();
        if (content.isEmpty()) {
            Toast.makeText(ChatGrouopActivity.this, "内容不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        mGroupId = etGroupId.getText().toString();
        if (mGroupId.isEmpty()) {
            Toast.makeText(ChatGrouopActivity.this, "群号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                //创建一条文本消息，content为消息文字内容，toChatUsername为对方用户或者群聊的id，后文皆是如此
                EMMessage message = EMMessage.createTxtSendMessage(content, mGroupId);
                //如果是群聊，设置chattype，默认是单聊
                message.setChatType(EMMessage.ChatType.GroupChat);
                //发送消息
                EMClient.getInstance().chatManager().sendMessage(message);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        list.add(message);
                        adapter.notifyDataSetChanged();
                        etGroupConent.setText("");
                    }
                });
            }
        }).start();
    }

    private void joinGroup() {

        String groupId = etGroupId.getText().toString();
        if (groupId.isEmpty()) {
            Toast.makeText(ChatGrouopActivity.this, "群号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    EMClient.getInstance().groupManager().joinGroup(groupId);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(ChatGrouopActivity.this, "加入成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (HyphenateException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(ChatGrouopActivity.this, "加入失败" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void createGroup() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                EMGroupOptions option = new EMGroupOptions();
                option.maxUsers = 200;
                option.style = EMGroupManager.EMGroupStyle.EMGroupStylePublicOpenJoin;

                try {
                    EMGroup group = EMClient.getInstance().groupManager().createGroup("飞聊群", "没事扯会儿", new String[]{"a", "b"}, "", option);

                    groupId = group.getGroupId();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            etGroupId.setText(groupId);
                            Toast.makeText(ChatGrouopActivity.this, "创建成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (HyphenateException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(ChatGrouopActivity.this, "创建失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
