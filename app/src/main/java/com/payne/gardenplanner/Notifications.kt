package com.payne.gardenplanner
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import java.util.*

class Notifications(private val context: Context) {
    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    private val notificationChannelId = "GardenCareNotificationChannel"
    private val notificationChannelName = "Garden Care Notification Channel"
    private val notificationChannelDescription = "Get reminders to care for your garden"

    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    private val pendingIntent = PendingIntent.getBroadcast(
        context,
        0,
        Intent(context, GardenCareNotificationReceiver::class.java),
        PendingIntent.FLAG_UPDATE_CURRENT
    )

    fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(
                    notificationChannelId,
                    notificationChannelName,
                    NotificationManager.IMPORTANCE_DEFAULT
                )
            channel.description = notificationChannelDescription
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun setNotificationTime(hour: Int, minute: Int) {
        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
            if (timeInMillis <= System.currentTimeMillis()) {
                add(Calendar.DAY_OF_MONTH, 1)
            }
        }
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
    }

    fun cancelNotifications() {
        alarmManager.cancel(pendingIntent)
        notificationManager.cancelAll()
    }

    inner class GardenCareNotificationReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val notificationBuilder = NotificationCompat.Builder(context, notificationChannelId)
                .setContentTitle("Garden Care Reminder")
                .setContentText("Don't forget to care for your plants today!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            notificationManager.notify(0, notificationBuilder.build())
        }
    }
}