package kidzania.vehiclespuzzlegame;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import static kidzania.vehiclespuzzlegame.libClass.GlobalVar.TAG_MOBIL;
import static kidzania.vehiclespuzzlegame.libClass.GlobalVar.isFinishBus;
import static kidzania.vehiclespuzzlegame.libClass.GlobalVar.isFinishFire;
import static kidzania.vehiclespuzzlegame.libClass.GlobalVar.isFinishHospital;
import static kidzania.vehiclespuzzlegame.libClass.GlobalVar.isFinishTaxi;

public class MainMenu extends AppCompatActivity {

    ImageButton btnTaxi, btnFire, btnHospital, btnBus;
    private static int SPLASH_TIME_OUT = 700;
    private static int SPLASH_TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        initialization();
        ShowSlideBan();
    }

    private void initialization(){
        btnTaxi = (ImageButton) findViewById(R.id.btnTaxi);
        btnFire = (ImageButton) findViewById(R.id.btnFire);
        btnHospital = (ImageButton) findViewById(R.id.btnHospital);
        btnBus = (ImageButton) findViewById(R.id.btnBus);
        setImageButton();
        addFuntionButton();
    }

    private void addFuntionButton(){
        btnTaxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (!isFinishTaxi) {
                            TAG_MOBIL = "TAXI";
                            startActivity(new Intent(MainMenu.this, Taxi.class));
                            finish();
                        }
                    }
                }, SPLASH_TIME_OUT);
            }
        });

        btnFire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (!isFinishFire) {
                            TAG_MOBIL = "FIRE";
                            startActivity(new Intent(MainMenu.this, Fire.class));
                            finish();
                        }
                    }
                }, SPLASH_TIME_OUT);
            }
        });

        btnHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (!isFinishHospital) {
                            TAG_MOBIL = "HOSPITAL";
                            startActivity(new Intent(MainMenu.this, Hospital.class));
                            finish();
                        }
                    }
                }, SPLASH_TIME_OUT);
            }
        });

        btnBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (!isFinishBus) {
                            TAG_MOBIL = "BUS";
                            startActivity(new Intent(MainMenu.this, Bus.class));
                            finish();
                        }
                    }
                }, SPLASH_TIME_OUT);
            }
        });
    }

    private void setImageButton(){
        if (isFinishTaxi){
            btnTaxi.setImageResource(R.drawable.taxi_finish);
        }
        if (isFinishBus){
            btnBus.setImageResource(R.drawable.bus_finish);
        }
        if (isFinishFire){
            btnFire.setImageResource(R.drawable.fire_finish);
        }
        if (isFinishHospital){
            btnHospital.setImageResource(R.drawable.hospital_finish);
        }
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(MainMenu.this, Start.class));
        finish();
    }

    private void ShowSlideBan(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isFinishTaxi && isFinishBus && isFinishFire && isFinishHospital){
                    startActivity(new Intent(MainMenu.this, SliceBan.class));
                    finish();
                }
            }
        }, SPLASH_TIME);
    }

}
