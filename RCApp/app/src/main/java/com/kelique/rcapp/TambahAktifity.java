package com.kelique.rcapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class TambahAktifity extends AppCompatActivity implements View.OnClickListener{


    @InjectView(R.id.textView2)
    EditText textView2;
    @InjectView(R.id.textView3)
    EditText textView3;
    @InjectView(R.id.textView4)
    EditText textView4;
    @InjectView(R.id.textView5)
    EditText textView5;
    @InjectView(R.id.textView6)
    EditText textView6;
    @InjectView(R.id.textView7)
    EditText textView7;
    @InjectView(R.id.buttonKrm)
    Button buttonKrm;

    DatabaseReference dRef;
    String id;
    @InjectView(R.id.btnTotal)
    Button btnTotal;
    @InjectView(R.id.btnProid)
    Button btnProid;
    @InjectView(R.id.textCap)
    TextView textCap;
    @InjectView(R.id.textPriod)
    TextView textPriod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_aktifitas);
        ButterKnife.inject(this);

        dRef = FirebaseDatabase.getInstance().getReference("Peserta Pedagang");
        id = dRef.push().getKey();

        textView6 = (EditText) findViewById(R.id.textView6);
        textView7 = (EditText) findViewById(R.id.textView7);
        btnTotal =(Button) findViewById(R.id.btnTotal);
        btnProid = (Button) findViewById(R.id.btnProid);
        textCap = (TextView) findViewById(R.id.textCap);
        textPriod = (TextView) findViewById(R.id.textPriod);

        btnTotal.setOnClickListener(this);
        btnProid.setOnClickListener(this);


    }


    @OnClick(R.id.buttonKrm)
    public void onViewClicked() {
        String nama = textView2.getText().toString();
        String alamat = textView3.getText().toString();
        String ktp = textView4.getText().toString();
        String hp = textView5.getText().toString();
        String modal = textView6.getText().toString();
        String hasil = textView7.getText().toString();
        String kapital = textCap.getText().toString();
        String periode = textPriod.getText().toString();


        if (TextUtils.isEmpty(nama)) {
            textView2.setError("Nama Harap Diisi");
        } else if (TextUtils.isEmpty(alamat)) {
            textView3.setError("Alamat Harap Diisi");
        } else if (TextUtils.isEmpty(ktp)) {
            textView4.setError("KTP Harap Diisi");
        } else if (TextUtils.isEmpty(hp)) {
            textView5.setError("HP Harap Diisi");
        } else if (TextUtils.isEmpty(modal)) {
            textView6.setError("Jumlah modal Harap Diisi");
        } else if (TextUtils.isEmpty(hasil)) {
            textView7.setError("Jumlah Bagi hasil Harap Diisi");
        } else if(TextUtils.isEmpty(kapital)){
            textCap.setError("Jangan Lupa Tekan Tombol Hitung Total");
        } else if(TextUtils.isEmpty(periode)){
            textCap.setError("Jangan Lupa Tekan Tombol Hitung Periode");
        }

        Anggota anggota = new Anggota(nama, alamat, ktp, hp, modal, hasil, kapital, periode);
        dRef.child(id).setValue(anggota);
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        Toast.makeText(getApplicationContext(), "Penambahan Data Berhasil", Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.btnTotal, R.id.btnProid})
    public void onClick(View view) {
        String sumber = textView6.getText().toString();
        String tambah= textView7.getText().toString();
        String total = textCap.getText().toString();
        switch (view.getId()) {
            case R.id.btnTotal:

                try
                {
                    int ditambah = Integer.parseInt(sumber)+ Integer.parseInt(tambah);
                    textCap.setText(String.valueOf(ditambah));
                }

                catch (Exception e) {
                //Handle the Null Pointer Exception here.
                Log.e("Lupa mengisi", "Error occurred!, Error = "+e.toString());
            }
                break;
            case R.id.btnProid:
                try
                {
                    int dibagi = Integer.parseInt(total)/Integer.parseInt("20000");
                    textPriod.setText(String.valueOf(dibagi));
                }

                catch (Exception e) {
                    //Handle the Null Pointer Exception here.
                    Log.e("Lupa mengisi", "Error occurred!, Error = "+e.toString());
                }

                break;
        }
    }
}
