package ahn.mirim.firstiamnotme;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by 안성현 on 2017-10-19.
 */

public class MainActivity extends Activity {
    long pressedTime=0;
    MediaPlayer player;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        findViewById(R.id.play_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SurfaceViewActivity.class));
                player.stop();
                finish();
            }

        });

        findViewById(R.id.help_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Help.class));
            }
        });


        player = MediaPlayer.create(this, R.raw.atelier_rorona);
        player.setVolume(0.7f, 0.7f);
        player.setLooping(true);
        player.start();
    }


    @Override
    protected void onPause() {
        super.onPause();
        player.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        player.start();
    }


    @Override
    public void onBackPressed() {
        if ( pressedTime == 0 ) {
            Toast.makeText(MainActivity.this, "한 번 더 누르면 종료됩니다." , Toast.LENGTH_SHORT).show();
            pressedTime = System.currentTimeMillis();
        }
        else {
            int seconds = (int) (System.currentTimeMillis() - pressedTime);

            if ( seconds > 2000 ) {
                Toast.makeText(MainActivity.this, "한 번 더 누르면 종료됩니다." , Toast.LENGTH_SHORT).show();
                pressedTime = 0 ;
            }
            else {
                super.onBackPressed();
            }
        }

    }
}
