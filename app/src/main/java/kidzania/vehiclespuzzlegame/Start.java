package kidzania.vehiclespuzzlegame;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import static kidzania.vehiclespuzzlegame.libClass.GlobalVar.isFinishBus;
import static kidzania.vehiclespuzzlegame.libClass.GlobalVar.isFinishFire;
import static kidzania.vehiclespuzzlegame.libClass.GlobalVar.isFinishHospital;
import static kidzania.vehiclespuzzlegame.libClass.GlobalVar.isFinishTaxi;

public class Start extends AppCompatActivity {

    Thread splashTread;
    ImageButton btnStart;

    private static int SPLASH_TIME_OUT = 700;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        btnStart = (ImageButton) findViewById(R.id.btnStart);
        setAllNoFinish();
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(Start.this, MainMenu.class));
                        finish();
                    }
                }, SPLASH_TIME_OUT);

            }
        });
        StartAnimations();
    }

    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        RelativeLayout LaySplashScreen=(RelativeLayout) findViewById(R.id.LaySplashScreen);
        LaySplashScreen.clearAnimation();
        LaySplashScreen.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();

        btnStart.clearAnimation();
        btnStart.startAnimation(anim);

        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while (waited < 4500) {
                        sleep(400);
                        waited += 400;
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //finish();
                }

            }
        };
        splashTread.start();
    }

    private void setAllNoFinish(){
        isFinishTaxi = false;
        isFinishBus = false;
        isFinishFire = false;
        isFinishHospital = false;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        finishAffinity();
        System.exit(0);
    }
}
