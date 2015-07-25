package org.itstep.falaleev.tictactoe;

import android.content.SharedPreferences;

public class Setting {

    private static final String SETT_SOUND = "sound";
    private static final String SETT_LEVEL = "level";

    private SharedPreferences preferences;

    public Setting(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public boolean isSound() {
        return preferences.getBoolean(SETT_SOUND, false);
    }

    public void setSound(boolean sound) {
        SharedPreferences.Editor ed = preferences.edit();
        ed.putBoolean(SETT_SOUND, sound);
        ed.apply();
    }

    public int getLevel() {
        return preferences.getInt(SETT_LEVEL, 1);
    }

    public void setLevel(int level) {
        SharedPreferences.Editor ed = preferences.edit();
        ed.putInt(SETT_LEVEL, level);
        ed.apply();
    }
}
