package kidzania.vehiclespuzzlegame;

import android.content.ClipData;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import kidzania.vehiclespuzzlegame.libClass.DragObject;

import static kidzania.vehiclespuzzlegame.libClass.DragObject.PosBelakang;
import static kidzania.vehiclespuzzlegame.libClass.DragObject.PosDepan;
import static kidzania.vehiclespuzzlegame.libClass.DragObject.fixBelakang;
import static kidzania.vehiclespuzzlegame.libClass.DragObject.fixDepan;
import static kidzania.vehiclespuzzlegame.libClass.GlobalVar.TAG_DRAG;

public class Bus extends AppCompatActivity {

    TextView textView;

    ImageView bus_mobil, bus_depan, bus_belakang;
    ImageView ban_sepeda, ban_fire, ban_bus, ban_hospital, ban_traktor;
    RelativeLayout layBanBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);

        textView = (TextView) findViewById(R.id.textView);
        initialization();
        setDragComponent();
    }

    private void initialization(){
        textView = (TextView) findViewById(R.id.textView);
        bus_mobil = (ImageView) findViewById(R.id.bus_mobil);
        bus_depan = (ImageView) findViewById(R.id.bus_depan);
        bus_belakang = (ImageView) findViewById(R.id.bus_belakang);
        ban_fire = (ImageView) findViewById(R.id.ban_fire);
        ban_sepeda = (ImageView) findViewById(R.id.ban_sepeda);
        ban_bus = (ImageView) findViewById(R.id.ban_bus);
        ban_traktor = (ImageView) findViewById(R.id.ban_traktor);
        ban_hospital = (ImageView) findViewById(R.id.ban_hospital);
        layBanBus = (RelativeLayout) findViewById(R.id.layBanBus);
    }


    private void setDragComponent(){
        bus_depan.setOnDragListener(new DragObject(
                Bus.this,
                R.drawable.bus_ban,
                R.drawable.bus_ban_kosong,
                PosDepan));

        bus_belakang.setOnDragListener(new DragObject(
                Bus.this,
                R.drawable.bus_ban,
                R.drawable.bus_ban_kosong,
                PosBelakang));

        bus_depan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fixDepan = false;
                ban_bus.setVisibility(View.VISIBLE);
                bus_depan.setImageResource(R.drawable.hospital_ban_kosong);
            }
        });

        bus_belakang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fixBelakang = false;
                ban_bus.setVisibility(View.VISIBLE);
                bus_belakang.setImageResource(R.drawable.bus_ban_kosong);
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
