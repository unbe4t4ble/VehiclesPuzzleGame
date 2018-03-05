package kidzania.vehiclespuzzlegame;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import static kidzania.vehiclespuzzlegame.libClass.GlobalVar.TAG_MOBIL;
import static kidzania.vehiclespuzzlegame.libClass.GlobalVar.isFinishBan;

public class Finish extends AppCompatActivity {

    ImageView img_finish;
    ImageButton btn_next;
    RelativeLayout LayFinish;
    private static int SPLASH_TIME = 1000;
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        LayFinish = (RelativeLayout) findViewById(R.id.LayFinish);
        btn_next = (ImageButton) findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Finish.this, MainMenu.class));
                finish();
            }
        });
        img_finish = (ImageView) findViewById(R.id.img_finish);
        setImageButton();
        setBackgoundFinishBan();
    }

    private void setImageButton(){
        if (TAG_MOBIL.equals("TAXI")){
            img_finish.setImageResource(R.drawable.taxi_finish);
        }
        if (TAG_MOBIL.equals("BUS")){
            img_finish.setImageResource(R.drawable.bus_finish);
        }
        if (TAG_MOBIL.equals("FIRE")){
            img_finish.setImageResource(R.drawable.fire_finish);
        }
        if (TAG_MOBIL.equals("HOSPITAL")){
            img_finish.setImageResource(R.drawable.hospital_finish);
        }
        if (TAG_MOBIL.equals("BAN")){
            img_finish.setImageResource(R.drawable.ban_utuh);
        }
    }

    private void setBackgoundFinishBan(){
        if (isFinishBan){
            runTimer();
        }
    }

    private void runTimer(){
        btn_next.setVisibility(View.GONE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ChangeBackGround();
            }
        }, SPLASH_TIME);
    }

    private void ChangeBackGround(){
        img_finish.setVisibility(View.GONE);
        LayFinish.setBackgroundResource(R.drawable.finish_all);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Finish.this, Start.class));
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    @Override
    public void onBackPressed() {

    }
}
