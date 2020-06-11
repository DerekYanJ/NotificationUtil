package com.yqy.test.notificationutil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_no_exit_channel.setOnClickListener {
            NotificationManagerCompat.from(this).getNotificationChannel("111")
        }

    }
}
