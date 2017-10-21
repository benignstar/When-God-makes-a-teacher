package ahn.mirim.firstiamnotme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class SurfaceViewActivity extends AppCompatActivity {
    GameSurfaceView gameSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_surface_view);
        gameSurfaceView=(GameSurfaceView)findViewById(R.id.gameSurfaceView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameSurfaceView.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameSurfaceView.setVisibility(View.VISIBLE);

    }

    @Override
    public void onBackPressed() {

    }
}
