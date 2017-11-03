package com.kelique.rcapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static android.R.attr.value;

public class TransaksiActivity extends AppCompatActivity {

    @InjectView(R.id.recycler)
    RecyclerView recycler;
    DatabaseReference dRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);
        ButterKnife.inject(this);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        dRef = FirebaseDatabase.getInstance().getReference("Peserta Pedagang");

        myRef.setValue("hello There!");

        caraNarikNama();

    }

    private void caraNarikNama() {
        dRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.d("proses", "Datanya adalah: " + value);

                ArrayList<Anggota2> data = new ArrayList<Anggota2>();
                for (DataSnapshot mmm : dataSnapshot.getChildren()) {
                    Anggota angg = mmm.getValue(Anggota.class);
                    Anggota2 angg2 = new Anggota2();

                    angg2.setKeyanggota(mmm.getKey());
                    angg2.setNamaanggota(angg.namaanggota);
                    angg2.setLamatanggota(angg.alamatanggota);
                    angg2.setKtpanggota(angg.ktpanggota);
                    angg2.setHapeanggota(angg.hpanggota);
                    angg2.setModalanggota(angg.modalanggota);
                    angg2.setHasilanggota(angg.hasilanggota);

                    data.add(angg2);


                    JalurNarikAnggota adapter = new JalurNarikAnggota(TransaksiActivity.this, data, recycler);
                    LinearLayoutManager lin = new LinearLayoutManager(TransaksiActivity.this);
                    recycler.setLayoutManager(lin);
                    recycler.setAdapter(adapter);

                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

                Log.w("proses", "Gagal membaca data yang diminta.", databaseError.toException());

            }
        });

    }
}
