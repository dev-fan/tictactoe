package org.itstep.falaleev.tictactoe.handler;

import android.view.View;

import org.itstep.falaleev.tictactoe.Game;
import org.itstep.falaleev.tictactoe.R;

public class FieldClick implements View.OnClickListener {

    public static final String LOG_TAG = "3T_FieldClick";

    private Game game;

    public FieldClick(Game game) {
        this.game = game;
    }

    @Override
    public void onClick(View v) {
        Boolean moved = game.move(game.findPosition(v));
        if (moved) {
            game.playSound(R.raw.move);
            if (!game.isFinish() && game.isWithComp()) {
                game.moveComp();
            }
        }
    }

}
