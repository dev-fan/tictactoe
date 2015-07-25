package org.itstep.falaleev.tictactoe;

import android.content.SharedPreferences;

public class Statistic {

    public static final String KEY = "ttt_statistics";
    public static final String KEY_USER = "stat_user";
    public static final String KEY_COMP = "stat_comp";
    public static final String KEY_DRAW = "stat_draw";

    private SharedPreferences preferences;

    public Statistic(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public void reset() {
        preferences.edit()
                .putInt(KEY_USER, 0)
                .putInt(KEY_COMP, 0)
                .putInt(KEY_DRAW, 0)
                .apply();
    }

    public void addWinCount(String key) {
        preferences.edit().putInt(key, (preferences.getInt(key, 0) + 1)).apply();
    }

    public int getWinCount(String key) {
        return preferences.getInt(key, 0);
    }

}
