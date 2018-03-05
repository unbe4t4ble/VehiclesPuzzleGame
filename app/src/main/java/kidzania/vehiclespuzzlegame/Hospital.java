package kidzania.vehiclespuzzlegame;

import android.content.ClipData;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import kidzania.vehiclespuzzlegame.libClass.CountDownAnimation;
import kidzania.vehiclespuzzlegame.libClass.DragObject;

import static kidzania.vehiclespuzzlegame.libClass.DragObject.PosBelakang;
import static kidzania.vehiclespuzzlegame.libClass.DragObject.PosDepan;
import static kidzania.vehiclespuzzlegame.libClass.DragObject.fixBelakang;
import static kidzania.vehiclespuzzlegame.libClass.DragObject.fixDepan;
import static kidzania.vehiclespuzzlegame.libClass.GlobalVar.TAG_DRAG;

public class Hospital extends AppCompatActivity {

    TextView textView;
    CountDownAnimation countDownAnimation;
    int StartTimer = 3;
    private static int SPLASH_TIME_OUT = 500;
    CountDownAnimation.CountDownListener mListener;

    ImageView hospital_mobil, hospital_depan, hospital_belakang;
    ImageView ban_sepeda, ban_fire, ban_bus, ban_hospital, ban_traktor;
    RelativeLayout layBanHospital;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);

        textView = (TextView) findViewById(R.id.textView);
        initialization();
        setDragComponent();
    }

    private void initialization(){
        textView = (TextView) findViewById(R.id.textView);
        hospital_mobil = (ImageView) findViewById(R.id.hospital_mobil);
        hospital_depan = (ImageView) findViewById(R.id.hospital_depan);
        hospital_belakang = (ImageView) findViewById(R.id.hospital_belakang);
        ban_fire = (ImageView) findViewById(R.id.ban_fire);
        ban_sepeda = (ImageView) findViewById(R.id.ban_sepeda);
        ban_bus = (ImageView) findViewById(R.id.ban_bus);
        ban_traktor = (ImageView) findViewById(R.id.ban_traktor);
        ban_hospital = (ImageView) findViewById(R.id.ban_hospital);
        layBanHospital = (RelativeLayout) findViewById(R.id.layBanHospital);
    }


    private void setDragComponent(){
        hospital_depan.setOnDragListener(new DragObject(
                Hospital.this,
                R.drawable.hospital_ban,
                R.drawable.hospital_ban_kosong,
                PosDepan));

        hospital_belakang.setOnDragListener(new DragObject(
                Hospital.this,
                R.drawable.hospital_ban,
                R.drawable.hospital_ban_kosong,
                PosBelakang));

        hospital_depan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fixDepan = false;
                ban_hospital.setVisibility(View.VISIBLE);
                hospital_depan.setImageResource(R.drawable.hospital_ban_kosong);
            }
        });

        hospital_belakang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fixBelakang = false;
                ban_hospital.setVisibility(View.VISIBLE);
                hospital_belakang.setImageResource(R.drawable.hospital_ban_kosong);
            }
        });

        ban_fire.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                TAG_DRAG = "FIRE";
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
                return true;
            }
        });

        ban_sepeda.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                TAG_DRAG = "SEPEDA";
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
                return true;
            }
        });
        ban_hospital.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                TAG_DRAG = "HOSPITAL";
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
                return true;
            }
        });
        ban_traktor.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                TAG_DRAG = "TRAKTOR";
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
                return true;
            }
        });

        ban_bus.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                TAG_DRAG = "BUS";
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}
