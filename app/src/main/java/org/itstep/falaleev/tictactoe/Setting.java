package org.itstep.falaleev.tictactoe;

import android.content.SharedPreferences;

public class Setting {

    public static final String KEY = "ttt_settings";
    private static final String SETT_SOUND = "sound";
    private static final String SETT_WITH_COMP = "with_comp";
    private static final String SETT_LEVEL = "level";

    private SharedPreferences preferences;

    public Setting(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public boolean isSound() {
        return preferences.getBoolean(SETT_SOUND, false);
    }

    public void setSound(boolean sound) {
        preferences.edit().putBoolean(SETT_SOUND, sound).apply();
    }

    public boolean isWithComp() {
        return preferences.getBoolean(SETT_WITH_COMP, false);
    }

    public void setWithComp(boolean withComp) {
        preferences.edit().putBoolean(SETT_WITH_COMP, withComp).apply();
    }

    public int getLevel() {
        return preferences.getInt(SETT_LEVEL, Game.LEVEL_1);
    }

    public void setLevel(int level) {
        preferences.edit().putInt(SETT_LEVEL, level).apply();
    }

}
