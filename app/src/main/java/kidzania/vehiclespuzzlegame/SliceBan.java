package kidzania.vehiclespuzzlegame;

import android.content.ClipData;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import kidzania.vehiclespuzzlegame.libClass.CountDownAnimation;
import kidzania.vehiclespuzzlegame.libClass.DragObject;

import static kidzania.vehiclespuzzlegame.libClass.DragObject.PosKananAtas;
import static kidzania.vehiclespuzzlegame.libClass.DragObject.PosKananBawah;
import static kidzania.vehiclespuzzlegame.libClass.DragObject.PosKiriAtas;
import static kidzania.vehiclespuzzlegame.libClass.DragObject.PosKiriBawah;
import static kidzania.vehiclespuzzlegame.libClass.DragObject.fixKananAtas;
import static kidzania.vehiclespuzzlegame.libClass.DragObject.fixKananBawah;
import static kidzania.vehiclespuzzlegame.libClass.DragObject.fixKiriAtas;
import static kidzania.vehiclespuzzlegame.libClass.DragObject.fixKiriBawah;
import static kidzania.vehiclespuzzlegame.libClass.GlobalVar.TAG_DRAG;
import static kidzania.vehiclespuzzlegame.libClass.GlobalVar.TAG_MOBIL;

public class SliceBan extends AppCompatActivity {

    TextView textView;
    CountDownAnimation countDownAnimation;
    int StartTimer = 3;
    private static int SPLASH_TIME_OUT = 500;
    CountDownAnimation.CountDownListener mListener;

    ImageView ban_belum_utuh, kanan_atas, kiri_bawah, kiri_atas, kanan_bawah;
    ImageView kosong_kiri_atas, kosong_kanan_atas, kosong_kiri_bawah, kosong_kanan_bawah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slice_ban);
        TAG_MOBIL = "BAN";
        textView = (TextView) findViewById(R.id.textView);
        initialization();
        setDragComponent();
    }

    private void initialization(){
        ban_belum_utuh = (ImageView) findViewById(R.id.ban_belum_utuh);
        kanan_atas = (ImageView) findViewById(R.id.kanan_atas);
        kiri_bawah = (ImageView) findViewById(R.id.kiri_bawah);
        kiri_atas = (ImageView) findViewById(R.id.kiri_atas);
        kanan_bawah = (ImageView) findViewById(R.id.kanan_bawah);
        kosong_kiri_atas = (ImageView) findViewById(R.id.kosong_kiri_atas);
        kosong_kanan_atas = (ImageView) findViewById(R.id.kosong_kanan_atas);
        kosong_kiri_bawah = (ImageView) findViewById(R.id.kosong_kiri_bawah);
        kosong_kanan_bawah = (ImageView) findViewById(R.id.kosong_kanan_bawah);
    }

    private void setDragComponent(){
        kosong_kanan_atas.setOnDragListener(new DragObject(
                SliceBan.this,
                R.drawable.kanan_atas,
                R.drawable.kosong_kanan_atas,
                PosKananAtas));

        kosong_kanan_bawah.setOnDragListener(new DragObject(
                SliceBan.this,
                R.drawable.kanan_bawah,
                R.drawable.kosong_kanan_bawah,
                PosKananBawah));

        kosong_kiri_atas.setOnDragListener(new DragObject(
                SliceBan.this,
                R.drawable.kiri_atas,
                R.drawable.kosong_kiri_atas,
                PosKiriAtas));

        kosong_kiri_bawah.setOnDragListener(new DragObject(
                SliceBan.this,
                R.drawable.kiri_bawah,
                R.drawable.kosong_kiri_bawah,
                PosKiriBawah));

        kosong_kanan_atas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fixKananAtas = false;
                kanan_atas.setVisibility(View.VISIBLE);
                kosong_kanan_atas.setImageResource(R.drawable.kosong_kanan_atas);
            }
        });

        kosong_kanan_bawah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fixKananBawah = false;
                kanan_bawah.setVisibility(View.VISIBLE);
                kosong_kanan_bawah.setImageResource(R.drawable.kosong_kanan_bawah);
            }
        });

        kosong_kiri_atas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fixKiriAtas = false;
                kiri_atas.setVisibility(View.VISIBLE);
                kosong_kiri_atas.setImageResource(R.drawable.kosong_kiri_atas);
            }
        });

        kosong_kiri_bawah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fixKiriBawah = false;
                kiri_bawah.setVisibility(View.VISIBLE);
                kosong_kiri_bawah.setImageResource(R.drawable.kosong_kiri_bawah);
            }
        });

        kanan_atas.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                TAG_DRAG = "KANAN_ATAS";
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
                return true;
            }
        });

        kanan_bawah.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                TAG_DRAG = "KANAN_BAWAH";
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
                return true;
            }
        });
        kiri_atas.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                TAG_DRAG = "KIRI_ATAS";
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
                return true;
            }
        });
        kiri_bawah.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                TAG_DRAG = "KIRI_BAWAH";
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
