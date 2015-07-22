package org.itstep.falaleev.tictactoe;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private final static String LOG_TAG_MAIN = "TTT_Main";
    private final static String LOG_TAG_LISTENER = "TTT_Listener";
    private final static int PLAYER_X = 1;
    private final static int PLAYER_O = 2;

    Button btnPlay1;
    Button btnPlay2;
    TextView tvWin;

    private int counter = 0;
    private boolean finish = false;
    private boolean with_comp = false;
    private ArrayList<ImageView> elements = new ArrayList<>(9);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay1 = (Button) findViewById(R.id.btnPlay1);
        btnPlay2 = (Button) findViewById(R.id.btnPlay2);
        tvWin = (TextView) findViewById(R.id.tvWin);

        elements.add(0, (ImageView) findViewById(R.id.imv0));
        elements.add(1, (ImageView) findViewById(R.id.imv1));
        elements.add(2, (ImageView) findViewById(R.id.imv2));
        elements.add(3, (ImageView) findViewById(R.id.imv3));
        elements.add(4, (ImageView) findViewById(R.id.imv4));
        elements.add(5, (ImageView) findViewById(R.id.imv5));
        elements.add(6, (ImageView) findViewById(R.id.imv6));
        elements.add(7, (ImageView) findViewById(R.id.imv7));
        elements.add(8, (ImageView) findViewById(R.id.imv8));
    }

    private void reset() {
        for (ImageView iv : elements) {
            iv.setBackgroundColor(getResources().getColor(R.color.white));
        }
        tvWin.setText("");
        Arrays.fill(field, 0);
        counter = 0;
        finish = false;
    }

    public boolean move(int position) {
        Log.d(LOG_TAG_MAIN, "move(): position=" + position);
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
            checkGame();
            return true;
        }
        return false;
    }

    private void moveComp() {
        Log.d(LOG_TAG_MAIN, "moveComp()");
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

    private void checkGame() {
        // Проверка наличия победителя
        for (int c[] : win) {
            if (field[c[0]] > 0
                    && field[c[0]] == field[c[1]] && field[c[1]] == field[c[2]]) {
                finish = true;
                tvWin.setText(
                        String.format(getResources().getString(R.string.player_win)
                                , (field[c[0]] == PLAYER_X ? "X" : "O"))
                );
                return;
            }
        }
        // Проверка наличия пустых полей
        if (counter > 8) {
            finish = true;
            tvWin.setText(getResources().getString(R.string.draw_game));
        }
    }

    // Handlers

    public void onMove(View v) {
        Log.d(LOG_TAG_LISTENER, "onMove(): " + v.getId());
        int position = elements.indexOf(v);
        if (move(position) && !finish && with_comp) {
            moveComp();
        }
    }

    public void onReset(View v) {
        Log.d(LOG_TAG_LISTENER, "onReset(): " + v.getId());
        switch (v.getId()) {
            case R.id.btnPlay1:
                with_comp = true;
                break;
            case R.id.btnPlay2:
                with_comp = false;
                break;
        }
        reset();
    }

}
