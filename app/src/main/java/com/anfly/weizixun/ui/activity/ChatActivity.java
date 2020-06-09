package com.anfly.weizixun.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.btN_send)
    Button btNSend;
    @BindView(R.id.btn_record)
    Button btnRecord;
    @BindView(R.id.btn_send_audio)
    Button btnSendAudio;
    private String toName;
    private String curName;
    private ArrayList<EMMessage> list;
    private EMMessageAdapter adapter;
    private EMMessageListener msgListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        initData();
        initView();

        initReceiver();

        initHistory();
    }

    private void initHistory() {
        EMConversation conversation = EMClient.getInstance().chatManager().getConversation(toName);
        //获取此会话的所有消息
        List<EMMessage> messages = conversation.getAllMessages();
        list.addAll(messages);
        adapter.notifyDataSetChanged();
    }

    private void initReceiver() {
        msgListener = new EMMessageListener() {

            @Override
            public void onMessageReceived(List<EMMessage> messages) {

                list.addAll(messages);
                adapter.notifyDataSetChanged();
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

        EMClient.getInstance().chatManager().addMessageListener(msgListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EMClient.getInstance().chatManager().removeMessageListener(msgListener);
    }

    private void initData() {
        toName = getIntent().getStringExtra(Constants.NAME);
        curName = (String) SharedPreferencesUtils.getParam(this, Constants.NAME, "a");

        tvTitle.setText(curName + "正在和" + toName + "聊天中...");
    }

    private void initView() {
        list = new ArrayList<>();
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EMMessageAdapter(list, this, toName, curName);
        rv.setAdapter(adapter);

    }

    @OnClick({R.id.btN_send, R.id.btn_record, R.id.btn_send_audio})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btN_send:
                send();
                break;
            case R.id.btn_record:
                break;
            case R.id.btn_send_audio:
                break;
        }
    }

    private void send() {

        String content = etContent.getText().toString();

        if (content.isEmpty()) {
            Toast.makeText(ChatActivity.this, "请输入消息", Toast.LENGTH_SHORT).show();
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                //创建一条文本消息，content为消息文字内容，toChatUsername为对方用户或者群聊的id，后文皆是如此
                EMMessage message = EMMessage.createTxtSendMessage(content, toName);
                //发送消息
                EMClient.getInstance().chatManager().sendMessage(message);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        list.add(message);
                        adapter.notifyDataSetChanged();
                        etContent.setText("");
                    }
                });
            }
        }).start();

    }
}
