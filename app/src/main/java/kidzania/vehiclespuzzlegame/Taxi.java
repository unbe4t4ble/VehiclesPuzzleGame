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

public class Taxi extends AppCompatActivity {

    TextView textView;
    CountDownAnimation countDownAnimation;
    int StartTimer = 3;
    private static int SPLASH_TIME_OUT = 500;
    CountDownAnimation.CountDownListener mListener;

    ImageView taxi_mobil, taxi_depan, taxi_belakang;
    ImageView ban_taxi, ban_sepeda, ban_bus, ban_traktor, ban_fire;
    RelativeLayout layBanTaxi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taxi);

        textView = (TextView) findViewById(R.id.textView);
        initialization();
        setDragComponent();
    }

    private void initialization(){
        textView = (TextView) findViewById(R.id.textView);
        taxi_mobil = (ImageView) findViewById(R.id.taxi_mobil);
        taxi_depan = (ImageView) findViewById(R.id.taxi_depan);
        taxi_belakang = (ImageView) findViewById(R.id.taxi_belakang);
        ban_taxi = (ImageView) findViewById(R.id.ban_taxi);
        ban_sepeda = (ImageView) findViewById(R.id.ban_sepeda);
        ban_bus = (ImageView) findViewById(R.id.ban_bus);
        ban_traktor = (ImageView) findViewById(R.id.ban_traktor);
        ban_fire = (ImageView) findViewById(R.id.ban_fire);
        layBanTaxi = (RelativeLayout) findViewById(R.id.layBanTaxi);
    }

    private void setDragComponent(){
        taxi_depan.setOnDragListener(new DragObject(
                Taxi.this,
                R.drawable.taxi_ban,
                R.drawable.taxi_ban_kosong,
                PosDepan));

        taxi_belakang.setOnDragListener(new DragObject(
                Taxi.this,
                R.drawable.taxi_ban,
                R.drawable.taxi_ban_kosong,
                PosBelakang));

        taxi_depan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fixDepan = false;
                ban_taxi.setVisibility(View.VISIBLE);
                taxi_depan.setImageResource(R.drawable.taxi_ban_kosong);
            }
        });

        taxi_belakang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fixBelakang = false;
                ban_taxi.setVisibility(View.VISIBLE);
                taxi_belakang.setImageResource(R.drawable.taxi_ban_kosong);
            }
        });

        ban_taxi.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                TAG_DRAG = "TAXI";
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
