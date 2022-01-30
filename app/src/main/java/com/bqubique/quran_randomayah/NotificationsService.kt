package com.bqubique.quran_randomayah

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

const val TAG = "NotificationsService"

class NotificationsService : FirebaseMessagingService() {
    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        Log.d(TAG, "onMessageReceived: ${p0.data}")
    }

    override fun onDeletedMessages() {
        super.onDeletedMessages()
        Log.d(TAG, "onDeletedMessages: MESSAGE DELETED")
    }
}