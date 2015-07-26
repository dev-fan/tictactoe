package org.itstep.falaleev.tictactoe.handler;

import android.view.View;

import org.itstep.falaleev.tictactoe.Game;
import org.itstep.falaleev.tictactoe.R;
import org.itstep.falaleev.tictactoe.Setting;

public class ButtonClick implements View.OnClickListener {

    private Game game;
    private Setting setting;

    public ButtonClick(Game game, Setting setting) {
        this.game = game;
        this.setting = setting;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPlay1:
                setting.setWithComp(true);
                break;
            case R.id.btnPlay2:
                setting.setWithComp(false);
                break;
        }
        game.reset();
    }

}
