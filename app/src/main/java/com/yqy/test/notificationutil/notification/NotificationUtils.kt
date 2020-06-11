package com.yqy.test.notificationutil.notification

import android.annotation.TargetApi
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationManagerCompat

/**
 * 通知辅助类
 *
 * @author derekyan
 * @date 2020/6/11
 */
object NotificationUtils {
    private const val TAG = "NotificationUtils"

    /**
     * 通知渠道信息
     */
    data class NotificationChannelBean (
        val channelId: String,
        val channelName: String,
        val importance: Int)

    /**
     * 通过 context 和 channelList 批量创建通知渠道
     *
     * 8.0 以上调用
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun createNotificationChannel(context: Context, channelList: List<NotificationChannelBean>) {
        if (channelList.isNotEmpty()) {
            channelList.forEach {
                createNotificationChannel(context, it.channelId, it.channelName, it.importance)
            }
        }
    }

    /**
     * 创建通知渠道
     *
     * 8.0 以上调用
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun createNotificationChannel(
        context: Context,
        channelId: String,
        channelName: String,
        importance: Int
    ) {
        val channel = NotificationChannel(channelId, channelName, importance)
        val notificationManager = context.getSystemService(
            Context.NOTIFICATION_SERVICE ) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    /**
     * 指定渠道是否打开
     *
     * 8.0 以上调用
     */
    @TargetApi(Build.VERSION_CODES.O)
    fun isChannelEnabled(
        context: Context,
        channelId: String?,
        isIntentSetting: Boolean
    ): Boolean {
        val manager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = manager.getNotificationChannel(channelId)
            if (channel != null && channel.importance == NotificationManager.IMPORTANCE_NONE) {
                if (isIntentSetting) {
                    val intent =
                        Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    intent.putExtra(
                        Settings.EXTRA_APP_PACKAGE,
                        context.packageName
                    )
                    intent.putExtra(Settings.EXTRA_CHANNEL_ID, channel.id)
                    context.startActivity(intent)
                }
                return false
            }
        }
        return true
    }

    /**
     * 通知权限是否打开
     */
    fun isEnableNotification(context: Context): Boolean {
        return NotificationManagerCompat.from(context).areNotificationsEnabled()
    }

    /**
     * 关闭指定 id 的通知
     */
    fun cancelNotificationById(context: Context, id: Int) {
        val manager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.cancel(id)
    }

    /**
     * 关闭所有通知
     */
    fun cancelAllNotification(context: Context) {
        val manager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.cancelAll()
    }



}