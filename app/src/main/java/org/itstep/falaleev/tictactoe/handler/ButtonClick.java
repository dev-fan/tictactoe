package org.itstep.falaleev.tictactoe.handler;

import android.util.Log;
import android.view.View;

import org.itstep.falaleev.tictactoe.Game;
import org.itstep.falaleev.tictactoe.R;

public class ButtonClick implements View.OnClickListener {

    public static final String LOG_TAG = "3T_ButtonClick";

    private Game game;

    public ButtonClick(Game game) {
        this.game = game;
    }

    @Override
    public void onClick(View v) {
        Log.d(LOG_TAG, "onClick(): " + v.getId());
        switch (v.getId()) {
            case R.id.btnPlay1:
                game.setWithComp(true);
                break;
            case R.id.btnPlay2:
                game.setWithComp(false);
                break;
        }
        game.reset();
    }
}
