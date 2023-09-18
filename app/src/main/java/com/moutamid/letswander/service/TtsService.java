package com.moutamid.letswander.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.Locale;

public class TtsService extends Service implements TextToSpeech.OnInitListener {
    private TextToSpeech textToSpeech;
    String TAG = "GeofenceBroadcastReceiv";
    String desc = "";

    @Override
    public void onCreate() {
        super.onCreate();
        textToSpeech = new TextToSpeech(this, this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            String description = intent.getStringExtra("description");
            if (description != null && !description.isEmpty()) {
                Log.d(TAG, description);
                desc = description;
                onInit(TextToSpeech.SUCCESS);
            }
        }
        return START_NOT_STICKY;
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = textToSpeech.setLanguage(Locale.US);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e(TAG, "Text-to-speech language not supported.");
            } else {
                textToSpeech.speak(desc, TextToSpeech.QUEUE_FLUSH, null, null);
            }
        } else {
            Log.e(TAG, "Text-to-speech initialization failed.");
        }
    }

}
