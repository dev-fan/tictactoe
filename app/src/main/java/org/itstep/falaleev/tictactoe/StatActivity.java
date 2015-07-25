package org.itstep.falaleev.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


public class StatActivity extends AppCompatActivity {

    Statistic stat;
    TextView tvWinUser;
    TextView tvWinComp;
    TextView tvDraw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);

        tvWinUser = (TextView) findViewById(R.id.tvWinUser);
        tvWinComp = (TextView) findViewById(R.id.tvWinComp);
        tvDraw = (TextView) findViewById(R.id.tvDraw);
        stat = new Statistic(getSharedPreferences(Statistic.KEY, MODE_PRIVATE));
        showStat();
    }

    public void showStat() {
        tvWinUser.setText("" + stat.getWinCount(Statistic.KEY_USER));
        tvWinComp.setText("" + stat.getWinCount(Statistic.KEY_COMP));
        tvDraw.setText("" + stat.getWinCount(Statistic.KEY_DRAW));
    }

    public void resetStat(View v) {
        stat.reset();
        showStat();
    }

}
