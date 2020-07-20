package com.wentuo.weizixun.receiver;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.wentuo.weizixun.R;
import com.wentuo.weizixun.ui.activity.MainActivity;
import com.wentuo.weizixun.ui.activity.SmartActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import cn.jpush.android.api.JPushInterface;

public class WeizixunReceiver extends BroadcastReceiver {
    private static String TAG = "WeizixunReceiver";
    private Intent intent;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Log.d(TAG, "[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));

        String json = bundle.getString(JPushInterface.EXTRA_MESSAGE);
        if (json == null) {
            return;
        }
        sendNofication(context, json);
    }

    private void sendNofication(Context context, String json) {
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        try {
            JSONObject jsonObject = new JSONObject(json);
            String data = jsonObject.getString("data");
            String msg = jsonObject.getString("msg");
            String url = jsonObject.getString("url");

            if (data.equals("1")) {
                intent = new Intent(context, MainActivity.class);
            } else {
                intent = new Intent(context, SmartActivity.class);
                intent.putExtra("url", url);
            }

            PendingIntent pendingIntent = PendingIntent.getActivity(context, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            String channelId = "1";
            String channelName = "Weizixun";

            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
                NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
                nm.createNotificationChannel(notificationChannel);
            }
            Notification notification = new NotificationCompat.Builder(context, channelId)
                    .setSmallIcon(R.mipmap.ic_fab_calendar)
                    .setTicker("通知")
                    .setContentText("极光推送的消息")
                    .setContentIntent(pendingIntent)
                    .build();

            nm.notify(200, notification);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // 打印所有的 intent extra 数据
    private static String printBundle(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
            } else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
            } else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
                if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))) {
                    Log.i(TAG, "This message has no Extra data");
                    continue;
                }

                try {
                    JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                    Iterator<String> it = json.keys();
                    while (it.hasNext()) {
                        String myKey = it.next().toString();
                        sb.append("\nkey:" + key + ", value: [" +
                                myKey + " - " + json.optString(myKey) + "]");
                    }
                } catch (JSONException e) {
                    Log.e(TAG, "Get message extra JSON error!");
                }

            } else {
                sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
            }
        }
        return sb.toString();
    }
}
