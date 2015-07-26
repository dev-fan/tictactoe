package org.itstep.falaleev.tictactoe.handler;

import android.view.View;

import org.itstep.falaleev.tictactoe.Game;
import org.itstep.falaleev.tictactoe.R;
import org.itstep.falaleev.tictactoe.Setting;

public class FieldClick implements View.OnClickListener {

    private Game game;
    private Setting setting;

    public FieldClick(Game game, Setting setting) {
        this.game = game;
        this.setting = setting;
    }

    @Override
    public void onClick(View v) {
        Boolean moved = game.move(game.findPosition(v));
        if (moved) {
            game.playSound(R.raw.move);
            if (!game.isFinish() && setting.isWithComp()) {
                game.moveComp();
            }
        }
    }

}
