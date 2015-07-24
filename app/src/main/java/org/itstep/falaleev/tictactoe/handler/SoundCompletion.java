package org.itstep.falaleev.tictactoe.handler;

import android.media.MediaPlayer;
import android.util.Log;

public class SoundCompletion implements MediaPlayer.OnCompletionListener {

    public static final String LOG_TAG = "3T_SoundCompletion";

    @Override
    public void onCompletion(MediaPlayer mp) {
        try {
            mp.release();
            mp = null;
        } catch (Exception e) {
            Log.d(LOG_TAG, e.getMessage());
        }
    }

}
