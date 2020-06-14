package com.yqy.test.notificationutil

import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationManagerCompat
import com.yqy.test.notificationutil.notification.NotificationUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    private val channel1 = "101"
    private val channel2 = "102"
    private val channel3 = "103"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_open_setting.setOnClickListener {
            NotificationUtils.openSetting(this)
        }

        btn_check_notification.setOnClickListener {
            val tip = if (NotificationUtils.areNotificationsEnabled(this)) {
                "已有权限"
            } else "没有权限"

            Toast.makeText(this, tip, Toast.LENGTH_SHORT).show()
        }

        // 测试获取不存在得渠道是否异常
        btn_no_exit_channel.setOnClickListener {
            Log.e(TAG, "mNotificationChannel =11")
            Toast.makeText(this, "sksds", Toast.LENGTH_SHORT).show()
            val mNotificationChannel =
                NotificationManagerCompat.from(this).getNotificationChannel("111")
            Log.e(TAG, "mNotificationChannel = $mNotificationChannel")
        }

        btn_create_channel.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationUtils.createChannel(
                    this,
                    "101",
                    "渠道1", NotificationManagerCompat.IMPORTANCE_HIGH,
                    "g_101")
            }
        }

        btn_create_channels.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel1 = NotificationUtils.buildChannel(
                    "102",
                    "渠道2",
                    NotificationManagerCompat.IMPORTANCE_HIGH,
                    "g_102")
                val channel2 = NotificationUtils.buildChannel(
                    "103",
                    "渠道3",
                    NotificationManagerCompat.IMPORTANCE_HIGH,
                    "g_103")
                NotificationUtils.createChannels(this, arrayListOf(channel1, channel2))
            }
        }

        btn_create_channel_group.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationUtils.createChannelGroup(
                    this,
                    NotificationUtils.buildChannelGroup("g_101", "渠道组1"))
            }
        }

        btn_create_channel_groups.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channelGroup1 =
                    NotificationUtils.buildChannelGroup("g_102", "渠道组2")
                val channelGroup2 =
                    NotificationUtils.buildChannelGroup("g_103", "渠道组3")
                NotificationUtils.createChannelGroups(
                    this,
                    arrayListOf(channelGroup1, channelGroup2))
            }
        }

        btn_create_notification.setOnClickListener {
            val notification = NotificationUtils.create(
                this,
                channel1,
                whenTime = System.currentTimeMillis(),
                smallIconResId = R.mipmap.ic_launcher,
                contentTitle = "通知标题0",
                contentText = "内容"
            )
            NotificationUtils.show(this, 100, notification)
        }

        btn_create_notifications.setOnClickListener {
            val notification = NotificationUtils.create(
                this,
                channel1,
                whenTime = System.currentTimeMillis(),
                smallIconResId = R.mipmap.ic_launcher,
                contentTitle = "通知标题1",
                contentText = "内容"
            )
            NotificationUtils.show(this, 1, notification)


            NotificationUtils.createAndShow(
                this,
                channel1,
                notificationId = 2,
                whenTime = System.currentTimeMillis(),
                smallIconResId = R.mipmap.ic_launcher,
                contentTitle = "通知标题2",
                contentText = "内容"
            )
            NotificationUtils.createAndShow(
                this,
                channel1,
                notificationId = 3,
                whenTime = System.currentTimeMillis(),
                smallIconResId = R.mipmap.ic_launcher,
                contentTitle = "通知标题3",
                contentText = "内容"
            )
            NotificationUtils.createAndShow(
                this,
                channel1,
                notificationId = 4,
                whenTime = System.currentTimeMillis(),
                smallIconResId = R.mipmap.ic_launcher,
                contentTitle = "通知标题4",
                contentText = "内容"
            )
            NotificationUtils.createAndShow(
                this,
                channel1,
                notificationId = 5,
                whenTime = System.currentTimeMillis(),
                smallIconResId = R.mipmap.ic_launcher,
                contentTitle = "通知标题5",
                contentText = "内容"
            )
        }

        btn_create_big_notification.setOnClickListener {
            val bitmap =
                BitmapFactory.decodeResource(resources, R.mipmap.ic_audio, null);
            val notification = NotificationUtils.create(
                this,
                channel1,
                whenTime = System.currentTimeMillis(),
                smallIconResId = R.mipmap.ic_launcher,
                contentTitle = "通知标题0",
                contentText = "内容",
                largeIconBitmap = bitmap
            )
            NotificationUtils.show(this, 200, notification)
        }

        btn_create_big_notifications.setOnClickListener {
            val bitmap =
                BitmapFactory.decodeResource(resources, R.mipmap.ic_audio, null);
            val notification = NotificationUtils.create(
                this,
                channel1,
                whenTime = System.currentTimeMillis(),
                smallIconResId = R.mipmap.ic_launcher,
                contentTitle = "通知标题21",
                contentText = "内容",
                largeIconBitmap = bitmap
            )
            NotificationUtils.show(this, 21, notification)


            NotificationUtils.createAndShow(
                this,
                channel1,
                notificationId = 22,
                whenTime = System.currentTimeMillis(),
                smallIconResId = R.mipmap.ic_launcher,
                contentTitle = "通知标题22",
                contentText = "内容",
                largeIconBitmap = bitmap
            )
            NotificationUtils.createAndShow(
                this,
                channel1,
                notificationId = 23,
                whenTime = System.currentTimeMillis(),
                smallIconResId = R.mipmap.ic_launcher,
                contentTitle = "通知标题23",
                contentText = "内容",
                largeIconBitmap = bitmap
            )
            NotificationUtils.createAndShow(
                this,
                channel1,
                notificationId = 24,
                whenTime = System.currentTimeMillis(),
                smallIconResId = R.mipmap.ic_launcher,
                contentTitle = "通知标题24",
                contentText = "内容",
                largeIconBitmap = bitmap
            )
            NotificationUtils.createAndShow(
                this,
                channel1,
                notificationId = 25,
                whenTime = System.currentTimeMillis(),
                smallIconResId = R.mipmap.ic_launcher,
                contentTitle = "通知标题25",
                contentText = "内容",
                largeIconBitmap = bitmap
            )
        }

        btn_cancel_notification.setOnClickListener {
            NotificationUtils.cancel(this, 100)
            NotificationUtils.cancel(this, 200)
        }

        btn_cancel_notifications.setOnClickListener {
            NotificationUtils.cancelAll(this)
        }


    }
}
