package com.leo2026.weightlifting.service

import android.app.*
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Binder
import android.os.Build
import android.os.CountDownTimer
import android.os.IBinder
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RestTimerService : Service() {

    private val binder = TimerBinder()
    private var countDownTimer: CountDownTimer? = null

    inner class TimerBinder : Binder() {
        fun getService(): RestTimerService = this@RestTimerService
    }

    override fun onBind(intent: Intent?): IBinder = binder

    override fun onCreate() {
        super.onCreate()
        instance = this
        createNotificationChannel()
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer?.cancel()
        _isRunning.value = false
        instance = null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val seconds = intent?.getLongExtra("SECONDS", 0L) ?: 0L
        if (seconds > 0) {
            startTimer(seconds)
        }
        return START_NOT_STICKY
    }

    private fun startTimer(seconds: Long) {
        countDownTimer?.cancel()
        _isRunning.value = true
        
        startForeground(NOTIFICATION_ID, createNotification(seconds))

        countDownTimer = object : CountDownTimer(seconds * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secs = millisUntilFinished / 1000
                _timeLeft.value = secs
                updateNotification(secs)
            }

            override fun onFinish() {
                _timeLeft.value = 0
                _isRunning.value = false
                updateNotification(0, "¡Descanso Terminado!")
                vibrate()
                playBeep()
                stopForeground(STOP_FOREGROUND_DETACH)
                stopSelf()
            }
        }.start()
    }

    private fun playBeep() {
        try {
            val toneG = ToneGenerator(AudioManager.STREAM_NOTIFICATION, 100)
            toneG.startTone(ToneGenerator.TONE_PROP_BEEP, 500)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun vibrate() {
        val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager = getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as android.os.VibratorManager
            vibratorManager.defaultVibrator
        } else {
            @Suppress("DEPRECATION")
            getSystemService(Context.VIBRATOR_SERVICE) as android.os.Vibrator
        }
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(android.os.VibrationEffect.createOneShot(500, android.os.VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            @Suppress("DEPRECATION")
            vibrator.vibrate(500)
        }
    }

    fun stopTimer() {
        countDownTimer?.cancel()
        _isRunning.value = false
        stopForeground(STOP_FOREGROUND_REMOVE)
        stopSelf()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Rest Timer Channel",
                NotificationManager.IMPORTANCE_LOW
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }

    private fun createNotification(seconds: Long, content: String = ""): Notification {
        val text = if (content.isEmpty()) "Tiempo restante: $seconds s" else content
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("LEO-2026 Rest Timer")
            .setContentText(text)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setOngoing(true)
            .build()
    }

    private fun updateNotification(seconds: Long, content: String = "") {
        val notification = createNotification(seconds, content)
        val manager = getSystemService(NotificationManager::class.java)
        manager.notify(NOTIFICATION_ID, notification)
    }

    companion object {
        const val CHANNEL_ID = "rest_timer_channel"
        const val NOTIFICATION_ID = 1001

        private val _timeLeft = MutableStateFlow(0L)
        val timeLeft: StateFlow<Long> = _timeLeft

        private val _isRunning = MutableStateFlow(false)
        val isRunning: StateFlow<Boolean> = _isRunning

        var instance: RestTimerService? = null
            private set
    }
}
