package com.framgia.fbook

import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import android.support.v4.app.NotificationCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import com.crashlytics.android.Crashlytics
import com.framgia.fbook.data.source.remote.api.NetworkModule
import com.pusher.android.PusherAndroid
import com.pusher.android.PusherAndroidOptions
import com.pusher.android.notifications.PushNotificationRegistration
import com.pusher.android.notifications.interests.InterestSubscriptionChangeListener
import com.pusher.android.notifications.tokens.PushNotificationRegistrationListener
import io.fabric.sdk.android.Fabric


/**
 * Created by le.quang.dao on 10/03/2017.
 */

class MainApplication : MultiDexApplication() {

  lateinit var appComponent: AppComponent

  private val TAG = MainApplication::class.java.simpleName

  override fun onCreate() {
    super.onCreate()
    appComponent = DaggerAppComponent.builder()
        .applicationModule(ApplicationModule(applicationContext))
        .networkModule(NetworkModule(this))
        .build()
    Fabric.with(this, Crashlytics())
    MultiDex.install(this)
    configPusher()
  }

  private fun configPusher() {

    val pusherOptions = PusherAndroidOptions()
    pusherOptions.setCluster(BuildConfig.CLUSTER_KEY)

    val pusher = PusherAndroid(BuildConfig.APP_KEY, pusherOptions)
    val nativePusher: PushNotificationRegistration = pusher.nativePusher()

    registerPusher(nativePusher)
  }

  private fun registerPusher(nativePusher: PushNotificationRegistration?) {
    nativePusher?.registerFCM(this, object : PushNotificationRegistrationListener {
      override fun onSuccessfulRegistration() {
        Log.d(TAG, "Registration Successful !")
        subscribePusher(nativePusher)
      }

      override fun onFailedRegistration(statusCode: Int, response: String) {
        Log.d(TAG, "Registration failed with code $statusCode $response")
      }
    })
  }

  private fun subscribePusher(nativePusher: PushNotificationRegistration?) {
    nativePusher?.subscribe(BuildConfig.CHANNEL_KEY, object : InterestSubscriptionChangeListener {
      override fun onSubscriptionChangeSucceeded() {
        Log.d(TAG, "Subscribe Successful !")
        listenerPusher(nativePusher)
      }

      override fun onSubscriptionChangeFailed(statusCode: Int, response: String) {
        Log.d(TAG, "Subscribe failed with code $statusCode $response")
      }
    })
  }

  private fun listenerPusher(nativePusher: PushNotificationRegistration?) {
    nativePusher?.setFCMListener { remoteMessage ->
      remoteMessage?.let {
        //todo edit later
//        showNotification(remoteMessage.notification.title)
      }
    }
  }

  private fun showNotification(message: String) {
    val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
    val builder = NotificationCompat.Builder(this)
        .setSmallIcon(R.drawable.ic_books)
        .setColor(ContextCompat.getColor(this, R.color.colorPrimary))
        .setLargeIcon(BitmapFactory.decodeResource(resources,
            R.mipmap.ic_launcher_round))
        .setAutoCancel(true)
        .setSound(uri)
        .setDefaults(NotificationCompat.DEFAULT_ALL)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setContentTitle(message)
        .setContentText(message)
    val notificationManager: NotificationManager = getSystemService(
        Context.NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.notify(99, builder.build())

  }
}
