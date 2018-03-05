package kidzania.vehiclespuzzlegame.libClass;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;

import kidzania.vehiclespuzzlegame.Finish;

import static kidzania.vehiclespuzzlegame.libClass.GlobalVar.TAG_BAN;
import static kidzania.vehiclespuzzlegame.libClass.GlobalVar.TAG_DRAG;
import static kidzania.vehiclespuzzlegame.libClass.GlobalVar.TAG_MOBIL;
import static kidzania.vehiclespuzzlegame.libClass.GlobalVar.isFinishBan;
import static kidzania.vehiclespuzzlegame.libClass.GlobalVar.isFinishBus;
import static kidzania.vehiclespuzzlegame.libClass.GlobalVar.isFinishFire;
import static kidzania.vehiclespuzzlegame.libClass.GlobalVar.isFinishHospital;
import static kidzania.vehiclespuzzlegame.libClass.GlobalVar.isFinishTaxi;

/**
 * Created by mubarik on 14/09/2017.
 */

public class DragObject implements View.OnDragListener {

    private static final String TAG = "DragObject";

    private int ban;
    private int kosong;
    private boolean isSuccess;
    private String Position;

    private static int SPLASH_TIME_OUT = 500;

    public static final String PosDepan = "DEPAN";
    public static final String PosBelakang = "BELAKANG";

    public static final String PosKiriAtas = "KIRIATAS";
    public static final String PosKiriBawah = "KIRIBAWAH";
    public static final String PosKananAtas = "KANANATAS";
    public static final String PosKananBawah = "KANANBAWAH";

    public static boolean fixDepan, fixBelakang;
    public static boolean fixKananAtas, fixKananBawah, fixKiriAtas, fixKiriBawah;
    private Context context;

    public DragObject(Context con, int Ban, int BanKosong, String Pos) {
        this.context = con;
        this.ban = Ban;
        this.kosong = BanKosong;
        this.Position = Pos;
        fixDepan = false;
        fixBelakang = false;
    }

    @Override
    public boolean onDrag(View view, DragEvent dragEvent) {
        final ImageView containerView = (ImageView) view; //yang di drag
        final ImageView draggedView = (ImageView) dragEvent.getLocalState(); //yang menerima drag

        switch (dragEvent.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                Log.d(TAG, "onDrag: ACTION_DRAG_STARTED");
                isSuccess = false; //mulai menggeser
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                Log.d(TAG, "onDrag: ACTION_DRAG_ENTERED"); //mulai kena objek
                if(TAG_MOBIL.equals("BAN")){
                    if (!fixKananAtas && !fixKananBawah && !fixKiriAtas && !fixKiriBawah ) {
                        containerView.setImageResource(kosong);
                    }
                }else {
                    if (!fixDepan && !fixBelakang) {
                        containerView.setImageResource(kosong);
                    }
                }
                break;
            case DragEvent.ACTION_DRAG_EXITED: //kena objek terus jauh
                Log.d(TAG, "onDrag: ACTION_DRAG_EXITED");
                if(TAG_MOBIL.equals("BAN")){
                    if (!fixKananAtas && !fixKananBawah && !fixKiriAtas && !fixKiriBawah ) {
                        containerView.setImageResource(kosong);
                    }
                }else {
                    if (!fixDepan && !fixBelakang) {
                        containerView.setImageResource(kosong);
                    }
                }
                break;
            case DragEvent.ACTION_DROP: //masuk ke objek
                Log.d(TAG, "onDrag: ACTION_DROP");
                if(canDrop(containerView)) {
                    isSuccess = true;
                    draggedView.post(new Runnable() {
                        @Override
                        public void run() {
                            containerView.setImageResource(kosong);
                            draggedView.setVisibility(View.GONE);
                        }
                    });
                }else{
                    return false;
                }
                break;
            case DragEvent.ACTION_DRAG_ENDED: //berhenti ngedrak
                Log.d(TAG, "onDrag: ACTION_DRAG_ENDED");
                view.setVisibility(View.VISIBLE);
                if (isSuccess) {
                    if (Position.equals(PosDepan)) {
                        fixDepan = true;
                    }
                    if (Position.equals(PosBelakang)) {
                        fixBelakang = true;
                    }
                    if (Position.equals(PosKananAtas)) {
                        fixKananAtas = true;
                    }
                    if (Position.equals(PosKananBawah)) {
                        fixKananBawah = true;
                    }
                    if (Position.equals(PosKiriAtas)) {
                        fixKiriAtas = true;
                    }
                    if (Position.equals(PosKiriBawah)) {
                        fixKiriBawah = true;
                    }
                    containerView.setImageResource(ban);
                }else{
                    draggedView.post(new Runnable() {
                        @Override
                        public void run() {
                            if (TAG_MOBIL.equals("BAN")){
                                if (cekFixBan()){
                                    if (hideDragObject(draggedView)) {
                                        draggedView.setImageResource(kosong);
                                    }
                                    draggedView.setVisibility(View.VISIBLE);

                                }else {
                                    draggedView.setImageResource(kosong);
                                    draggedView.setVisibility(View.VISIBLE);
                                    if (AllFinishBan()) {
                                        setFinishStatus();
                                        ShowComplete();
                                    }
                                }
                            }else {
                                if (cekFix()) {
                                    if (fixDepan && fixBelakang) {
                                        containerView.setImageResource(kosong);
                                    }
                                    draggedView.setVisibility(View.VISIBLE);
                                } else {
                                    draggedView.setImageResource(kosong);
                                    draggedView.setVisibility(View.VISIBLE);
                                    setFinishStatus();
                                    ShowComplete();
                                }
                            }
                        }
                    });
                }
                break;
            default:
        }
        return true;
    }

    private boolean canDrop(View v){
        boolean drop = false;
        if (TAG_MOBIL.equals("BAN")){
            String[] separated = v.getResources().getResourceName(v.getId()).toUpperCase().split("/");
            TAG_BAN = separated[1];
        }
        if(TAG_MOBIL.equals("TAXI") && (TAG_DRAG.equals("TAXI"))) {
            drop = true;
        }
        if(TAG_MOBIL.equals("FIRE") && (TAG_DRAG.equals("FIRE"))) {
            drop = true;
        }
        if(TAG_MOBIL.equals("HOSPITAL") && (TAG_DRAG.equals("HOSPITAL"))) {
            drop = true;
        }
        if(TAG_MOBIL.equals("BUS") && (TAG_DRAG.equals("BUS"))) {
            drop = true;
        }
        if(TAG_BAN.equals("KOSONG_KIRI_ATAS") && (TAG_DRAG.equals("KIRI_ATAS"))) {
            drop = true;
        }
        if(TAG_BAN.equals("KOSONG_KANAN_ATAS") && (TAG_DRAG.equals("KANAN_ATAS"))) {
            drop = true;
        }
        if(TAG_BAN.equals("KOSONG_KIRI_BAWAH") && (TAG_DRAG.equals("KIRI_BAWAH"))) {
            drop = true;
        }
        if(TAG_BAN.equals("KOSONG_KANAN_BAWAH") && (TAG_DRAG.equals("KANAN_BAWAH"))) {
            drop = true;
        }
        return drop;
    }

    private void setFinishStatus(){
        if((TAG_MOBIL.equals("TAXI") && (TAG_DRAG.equals("TAXI")))) {
            isFinishTaxi = true;
        }
        if((TAG_MOBIL.equals("FIRE") && (TAG_DRAG.equals("FIRE")))) {
            isFinishFire = true;
        }
        if((TAG_MOBIL.equals("HOSPITAL") && (TAG_DRAG.equals("HOSPITAL")))) {
            isFinishHospital = true;
        }
        if((TAG_MOBIL.equals("BUS") && (TAG_DRAG.equals("BUS")))) {
            isFinishBus = true;
        }
        if (fixKananAtas && fixKananBawah && fixKiriAtas && fixKiriBawah ) {
            isFinishBan = true;
        }
    }

    private void ShowComplete(){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent();
                intent.setClass(context, Finish.class);
                context.startActivity(intent);
            }
        }, SPLASH_TIME_OUT);
    }

    private boolean cekFix(){
        boolean value = false;
        if ((fixDepan && !fixBelakang) ||
                (!fixDepan && fixBelakang) ||
                (!fixDepan && !fixBelakang)) {
            value = true;
        }
        return value;
    }

    private boolean cekFixBan(){
        boolean value = false;
        if ((!fixKananAtas && !fixKananBawah && !fixKiriAtas && !fixKiriBawah) ||
                (!fixKananAtas && !fixKananBawah && !fixKiriAtas && fixKiriBawah) ||
                (!fixKananAtas && !fixKananBawah && fixKiriAtas && !fixKiriBawah) ||
                (!fixKananAtas && !fixKananBawah && fixKiriAtas && fixKiriBawah) ||
                (!fixKananAtas && fixKananBawah && !fixKiriAtas && !fixKiriBawah) ||
                (!fixKananAtas && fixKananBawah && !fixKiriAtas && fixKiriBawah) ||
                (!fixKananAtas && fixKananBawah && fixKiriAtas && !fixKiriBawah) ||
                (!fixKananAtas && fixKananBawah && fixKiriAtas && fixKiriBawah) ||
                (fixKananAtas && !fixKananBawah && !fixKiriAtas && !fixKiriBawah) ||
                (fixKananAtas && !fixKananBawah && !fixKiriAtas && fixKiriBawah) ||
                (fixKananAtas && !fixKananBawah && fixKiriAtas && !fixKiriBawah) ||
                (fixKananAtas && !fixKananBawah && fixKiriAtas && fixKiriBawah) ||
                (fixKananAtas && fixKananBawah && !fixKiriAtas && !fixKiriBawah) ||
                (fixKananAtas && fixKananBawah && !fixKiriAtas && fixKiriBawah) ||
                (fixKananAtas && fixKananBawah && fixKiriAtas && !fixKiriBawah)) {
            value = true;
        }

        return value;
    }

    private boolean AllFinishBan(){
        boolean value = false;
        if (fixKananAtas && fixKananBawah && fixKiriAtas && fixKiriBawah ) {
            value = true;
        }
        return value;
    }

    private boolean hideDragObject(View v){
        boolean value = false;
        if (TAG_MOBIL.equals("BAN")){
            String[] separated = v.getResources().getResourceName(v.getId()).toUpperCase().split("/");
            TAG_BAN = separated[1];
        }
        if(TAG_BAN.equals("KIRI_ATAS") && (fixKiriAtas)) {
            value = true;
        }
        if(TAG_BAN.equals("KANAN_ATAS") && (fixKananAtas)) {
            value = true;
        }
        if(TAG_BAN.equals("KIRI_BAWAH") && (fixKiriBawah)) {
            value = true;
        }
        if(TAG_BAN.equals("KANAN_BAWAH") && (fixKananBawah)) {
            value = true;
        }
        return value;
    }
}
