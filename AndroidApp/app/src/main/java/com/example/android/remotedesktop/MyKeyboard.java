package com.example.android.remotedesktop;

import android.content.Context;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

public class MyKeyboard extends LinearLayout implements View.OnClickListener {

    private Button num0;
    private Button num1;
    private Button num2;
    private Button num3;
    private Button num4;
    private Button num5;
    private Button num6;
    private Button num7;
    private Button num8;
    private Button num9;
    private Button alphaq;
    private Button alphaw;
    private Button alphae;
    private Button alphar;
    private Button alphat;
    private Button alphay;
    private Button alphau;
    private Button alphai;
    private Button alphao;
    private Button alphap;
    private Button alphaa;
    private Button alphas;
    private Button alphad;
    private Button alphaf;
    private Button alphag;
    private Button alphah;
    private Button alphaj;
    private Button alphak;
    private Button alphal;
    private Button alphahash;
    private Button alphacl;
    private Button alphaz;
    private Button alphax;
    private Button alphac;
    private Button alphav;
    private Button alphab;
    private Button alphan;
    private Button alpham;
    private Button alphadot;
    private Button alphaques;
    private Button alphacom;
    private Button alphaat;
    private Button alphaexcl;
    private Button alphacol;
    private Button alphaslash;
    private Button alphaspace;
    private Button alphadel;
    private Button alphaenter;

    SparseArray<Integer> keyCodes=new SparseArray<Integer>();

    public MyKeyboard(Context context) {
        super(context);
        init(context,null);
    }

    public MyKeyboard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public MyKeyboard(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    public void init(Context context,AttributeSet attrs)
    {
        LayoutInflater.from(context).inflate(R.layout.keyboard,this,true);
        num1=(Button)findViewById(R.id.btn_1);
        num2=(Button)findViewById(R.id.btn_2);
        num3=(Button)findViewById(R.id.btn_3);
        num4=(Button)findViewById(R.id.btn_4);
        num5=(Button)findViewById(R.id.btn_5);
        num6=(Button)findViewById(R.id.btn_6);
        num7=(Button)findViewById(R.id.btn_7);
        num8=(Button)findViewById(R.id.btn_8);
        num9=(Button)findViewById(R.id.btn_9);
        num0=(Button)findViewById(R.id.btn_0);
        alphaa=(Button)findViewById(R.id.btn_a);
        alphab=(Button)findViewById(R.id.btn_b);
        alphac=(Button)findViewById(R.id.btn_c);
        alphad=(Button)findViewById(R.id.btn_d);
        alphae=(Button)findViewById(R.id.btn_e);
        alphaf=(Button)findViewById(R.id.btn_f);
        alphag=(Button)findViewById(R.id.btn_g);
        alphah=(Button)findViewById(R.id.btn_h);
        alphai=(Button)findViewById(R.id.btn_i);
        alphaj=(Button)findViewById(R.id.btn_j);
        alphak=(Button)findViewById(R.id.btn_k);
        alphal=(Button)findViewById(R.id.btn_l);
        alpham=(Button)findViewById(R.id.btn_m);
        alphan=(Button)findViewById(R.id.btn_n);
        alphao=(Button)findViewById(R.id.btn_o);
        alphap=(Button)findViewById(R.id.btn_p);
        alphaq=(Button)findViewById(R.id.btn_q);
        alphar=(Button)findViewById(R.id.btn_r);
        alphas=(Button)findViewById(R.id.btn_s);
        alphat=(Button)findViewById(R.id.btn_t);
        alphau=(Button)findViewById(R.id.btn_u);
        alphav=(Button)findViewById(R.id.btn_v);
        alphaw=(Button)findViewById(R.id.btn_w);
        alphax=(Button)findViewById(R.id.btn_x);
        alphay=(Button)findViewById(R.id.btn_y);
        alphaz=(Button)findViewById(R.id.btn_z);
        alphahash=(Button)findViewById(R.id.btn_hash);
        alphaslash=(Button)findViewById(R.id.btn_forsl);
        alphacl=(Button)findViewById(R.id.btn_CL);
        alphadot=(Button)findViewById(R.id.btn_dot);
        alphaques=(Button)findViewById(R.id.btn_qm);
        alphacom=(Button)findViewById(R.id.btn_com);
        alphaat=(Button)findViewById(R.id.btn_at);
        alphaexcl=(Button)findViewById(R.id.btn_exc);
        alphacol=(Button)findViewById(R.id.btn_col);
        alphaspace=(Button)findViewById(R.id.btn_sp);
        alphadel=(Button)findViewById(R.id.btn_del);
        alphaenter=(Button)findViewById(R.id.btn_ent);

        num1.setOnClickListener(this);
        num2.setOnClickListener(this);
        num3.setOnClickListener(this);
        num4.setOnClickListener(this);
        num5.setOnClickListener(this);
        num6.setOnClickListener(this);
        num7.setOnClickListener(this);
        num8.setOnClickListener(this);
        num9.setOnClickListener(this);
        num0.setOnClickListener(this);
        alphaa.setOnClickListener(this);
        alphab.setOnClickListener(this);
        alphac.setOnClickListener(this);
        alphad.setOnClickListener(this);
        alphae.setOnClickListener(this);
        alphaf.setOnClickListener(this);
        alphag.setOnClickListener(this);
        alphah.setOnClickListener(this);
        alphai.setOnClickListener(this);
        alphaj.setOnClickListener(this);
        alphak.setOnClickListener(this);
        alphal.setOnClickListener(this);
        alpham.setOnClickListener(this);
        alphan.setOnClickListener(this);
        alphao.setOnClickListener(this);
        alphap.setOnClickListener(this);
        alphaq.setOnClickListener(this);
        alphar.setOnClickListener(this);
        alphas.setOnClickListener(this);
        alphat.setOnClickListener(this);
        alphau.setOnClickListener(this);
        alphav.setOnClickListener(this);
        alphaw.setOnClickListener(this);
        alphax.setOnClickListener(this);
        alphay.setOnClickListener(this);
        alphaz.setOnClickListener(this);
        alphacl.setOnClickListener(this);

        alphahash.setOnClickListener(this);
        alphadot.setOnClickListener(this);
        alphaques.setOnClickListener(this);
        alphacom.setOnClickListener(this);
        alphaat.setOnClickListener(this);
        alphaexcl.setOnClickListener(this);
        alphacol.setOnClickListener(this);
        alphaspace.setOnClickListener(this);
        alphadel.setOnClickListener(this);
        alphaenter.setOnClickListener(this);
        alphaslash.setOnClickListener(this);

        keyCodes.put(R.id.btn_0,48);
        keyCodes.put(R.id.btn_1,49);
        keyCodes.put(R.id.btn_2,50);
        keyCodes.put(R.id.btn_3,51);
        keyCodes.put(R.id.btn_4,52);
        keyCodes.put(R.id.btn_5,53);
        keyCodes.put(R.id.btn_6,54);
        keyCodes.put(R.id.btn_7,55);
        keyCodes.put(R.id.btn_8,56);
        keyCodes.put(R.id.btn_9,57);
        keyCodes.put(R.id.btn_a,65);
        keyCodes.put(R.id.btn_b,66);
        keyCodes.put(R.id.btn_c,67);
        keyCodes.put(R.id.btn_d,68);
        keyCodes.put(R.id.btn_e,69);
        keyCodes.put(R.id.btn_f,70);
        keyCodes.put(R.id.btn_g,71);
        keyCodes.put(R.id.btn_h,72);
        keyCodes.put(R.id.btn_i,73);
        keyCodes.put(R.id.btn_j,74);
        keyCodes.put(R.id.btn_k,75);
        keyCodes.put(R.id.btn_l,76);
        keyCodes.put(R.id.btn_m,77);
        keyCodes.put(R.id.btn_n,78);
        keyCodes.put(R.id.btn_o,79);
        keyCodes.put(R.id.btn_p,80);
        keyCodes.put(R.id.btn_q,81);
        keyCodes.put(R.id.btn_r,82);
        keyCodes.put(R.id.btn_s,83);
        keyCodes.put(R.id.btn_t,84);
        keyCodes.put(R.id.btn_u,85);
        keyCodes.put(R.id.btn_v,86);
        keyCodes.put(R.id.btn_w,87);
        keyCodes.put(R.id.btn_x,88);
        keyCodes.put(R.id.btn_y,89);
        keyCodes.put(R.id.btn_z,90);
        keyCodes.put(R.id.btn_sp,32);
        keyCodes.put(R.id.btn_CL,20);
        keyCodes.put(R.id.btn_ent,10);
        keyCodes.put(R.id.btn_hash,91);
        keyCodes.put(R.id.btn_qm,93);
        keyCodes.put(R.id.btn_exc,186);
        keyCodes.put(R.id.btn_col,222);
        keyCodes.put(R.id.btn_del,8);
        keyCodes.put(R.id.btn_com,44);
        keyCodes.put(R.id.btn_dot,110);
        keyCodes.put(R.id.btn_at,192);
        keyCodes.put(R.id.btn_forsl,47);
    }


    @Override
    public void onClick(View v) {
        Button btn=(Button) v;
        Integer sendInt=keyCodes.get(v.getId());
        Log.d("pressed", String.valueOf(sendInt));
        netUtils.send(sendInt);
    }
}



