package org.itstep.falaleev.tictactoe;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.itstep.falaleev.tictactoe.handler.SoundCompletion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Game {

    private final static String LOG_TAG= "3T_Game";
    private final static int PLAYER_X = 1;
    private final static int PLAYER_O = 2;

    private Context cnx;

    private ArrayList<ImageView> elements;
    private Setting setting;
    private TextView tvResult;
    private int counter = 0;
    private boolean finish = false;
    private boolean isWithComp = false;
    private int[] field = new int[9];
    private int[][] win = {
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8},
            {2, 4, 6},
    };

    public Game(Context cnx, Setting setting) {
        this.cnx = cnx;
        this.setting = setting;
    }

    public void reset() {
        for (ImageView iv : elements) {
            iv.setBackgroundColor(cnx.getResources().getColor(R.color.white));
        }
        tvResult.setText("");
        Arrays.fill(field, 0);
        counter = 0;
        finish = false;
    }

    public int findPosition(View view) {
        return elements.indexOf(view);
    }

    public boolean move(int position) {
        Log.d(LOG_TAG, "move(): position=" + position);
        ImageView v = elements.get(position);
        if (!finish && !(v.getBackground() instanceof BitmapDrawable)) {
            int pic_res;
            if (counter % 2 == 0) {
                pic_res = R.drawable.x;
                field[position] = PLAYER_X;
            } else {
                pic_res = R.drawable.o;
                field[position] = PLAYER_O;
            }
            v.setBackgroundResource(pic_res);
            counter++;
            check();
            return true;
        }
        return false;
    }

    public void moveComp() {
        Log.d(LOG_TAG, "moveComp()");
        ArrayList<Integer> empty_field = new ArrayList<>();
        ArrayList<Integer> empty_field_priority = new ArrayList<>();
        int position = -1;
        for (int i = 0; i < field.length; i++) {
            if (field[i] == 0) {
                empty_field.add(i);
                if (i % 2 == 0) {
                    if (i == 4) {
                        position = 4;
                    }
                    empty_field_priority.add(i);
                }
            }
        }
        if (!empty_field.isEmpty()) {
            Random rnd = new Random();
            if (position < 0 && !empty_field_priority.isEmpty()) {
                position = empty_field_priority.get(rnd.nextInt(empty_field_priority.size()));
            }
            if (position < 0) {
                position = empty_field.get(rnd.nextInt(empty_field.size()));
            }
            for (int c[] : win) {
                if (field[c[0]] == PLAYER_O && field[c[2]] == 0 && field[c[0]] == field[c[1]]) {
                    position = c[2];
                    break;
                } else if (field[c[0]] == PLAYER_O && field[c[1]] == 0 && field[c[0]] == field[c[2]]) {
                    position = c[1];
                    break;
                } else if (field[c[1]] == PLAYER_O && field[c[0]] == 0 && field[c[1]] == field[c[2]]) {
                    position = c[0];
                    break;
                } else if (field[c[0]] > 0 && field[c[2]] == 0 && field[c[0]] == field[c[1]]) {
                    position = c[2];
                } else if (field[c[0]] > 0 && field[c[1]] == 0 && field[c[0]] == field[c[2]]) {
                    position = c[1];
                } else if (field[c[1]] > 0 && field[c[0]] == 0 && field[c[1]] == field[c[2]]) {
                    position = c[0];
                }
            }
            move(position);
        }
    }

    private void check() {
        // Проверка наличия победителя
        for (int c[] : win) {
            if (field[c[0]] > 0 && field[c[0]] == field[c[1]] && field[c[1]] == field[c[2]]) {
                finish = true;
                String tpl = cnx.getResources().getString(R.string.player_win);
                tvResult.setText(String.format(tpl, (field[c[0]] == PLAYER_X ? "X" : "O")));
                playSound((field[c[0]] == PLAYER_X ? R.raw.x : (isWithComp ? R.raw.fail : R.raw.o)));
                return;
            }
        }
        // Проверка наличия пустых полей
        if (counter > 8) {
            finish = true;
            tvResult.setText(cnx.getResources().getString(R.string.draw_game));
        }
    }

    public void playSound(int fileId) {
        if (setting.isSound()) {
            MediaPlayer mp = MediaPlayer.create(cnx, fileId);
            mp.setOnCompletionListener(new SoundCompletion());
            mp.start();
            mp.setVolume(0.25f, 0.25f);
        }
    }

    // Getter/Setter

    public void setTvResult(TextView tvResult) {
        this.tvResult = tvResult;
    }

    public void setElements(ArrayList<ImageView> elements) {
        this.elements = elements;
    }

    public void setWithComp(boolean isWithComp) {
        this.isWithComp = isWithComp;
    }

    public boolean isWithComp() {
        return isWithComp;
    }

    public boolean isFinish() {
        return finish;
    }

}
