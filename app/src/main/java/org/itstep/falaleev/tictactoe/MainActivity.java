package org.itstep.falaleev.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.itstep.falaleev.tictactoe.handler.ButtonClick;
import org.itstep.falaleev.tictactoe.handler.FieldClick;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private final static String LOG_TAG_MAIN = "3T_Main";

    Button btnPlay1;
    Button btnPlay2;

    private Game game;
    private ArrayList<ImageView> elements = new ArrayList<>(9);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        game = new Game(this);
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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        menu.add(3, 7, 5, "Gps").setIntent(new Intent(this, GpsActivity.class));
//        // Загрузка меню из файла (Inflate the menu; this adds items to the action bar if it is present.)
//        getMenuInflater().inflate(R.menu.main, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        menu.setGroupVisible(1, chbOptMenu.isChecked());
//        return super.onPrepareOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        Intent intent;
//        switch (id) {
//            case R.id.miPreference:
//                startActivity(new Intent(this, PreferenceActivity.class));
//                break;
//        }
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            startActivity(new Intent(this, SettingsActivity.class));
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

}
