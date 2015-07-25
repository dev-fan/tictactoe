package org.itstep.falaleev.tictactoe;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.itstep.falaleev.tictactoe.handler.ButtonClick;
import org.itstep.falaleev.tictactoe.handler.FieldClick;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private final static String LOG_TAG = "3T_Main";

    Button btnPlay1;
    Button btnPlay2;

    private Game game;
    private Setting setting;
    private ArrayList<ImageView> elements = new ArrayList<>(9);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setting = new Setting(getPreferences(MODE_PRIVATE));
        game = new Game(this, setting);
        ButtonClick BtnHandler = new ButtonClick(game);
        FieldClick fldHandler = new FieldClick(game);

        btnPlay1 = (Button) findViewById(R.id.btnPlay1);
        btnPlay1.setOnClickListener(BtnHandler);
        btnPlay2 = (Button) findViewById(R.id.btnPlay2);
        btnPlay2.setOnClickListener(BtnHandler);

        elements.add(0, (ImageView) findViewById(R.id.imv0));
        elements.add(1, (ImageView) findViewById(R.id.imv1));
        elements.add(2, (ImageView) findViewById(R.id.imv2));
        elements.add(3, (ImageView) findViewById(R.id.imv3));
        elements.add(4, (ImageView) findViewById(R.id.imv4));
        elements.add(5, (ImageView) findViewById(R.id.imv5));
        elements.add(6, (ImageView) findViewById(R.id.imv6));
        elements.add(7, (ImageView) findViewById(R.id.imv7));
        elements.add(8, (ImageView) findViewById(R.id.imv8));
        for (ImageView iv : elements) {
            iv.setOnClickListener(fldHandler);
        }
        game.setElements(elements);
        game.setTvResult((TextView) findViewById(R.id.tvWin));
    }

    // Menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem mnSound = menu.findItem(R.id.menu_sound);
        mnSound.setCheckable(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Log.d("MENU", "onPrepareOptionsMenu()");
        MenuItem mnSound = menu.findItem(R.id.menu_sound);
        mnSound.setChecked(setting.isSound());
        mnSound.setIcon(setting.isSound()
                ? android.R.drawable.ic_lock_silent_mode_off
                : android.R.drawable.ic_lock_silent_mode);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("MENU", "onOptionsItemSelected()");
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_sound:
                setting.setSound(!setting.isSound());
                item.setChecked(setting.isSound());
                item.setIcon(setting.isSound()
                        ? android.R.drawable.ic_lock_silent_mode_off
                        : android.R.drawable.ic_lock_silent_mode);
                break;
        }
        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            startActivity(new Intent(this, SettingsActivity.class));
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }

}
