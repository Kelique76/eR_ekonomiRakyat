package com.kelique.rcapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kelique on 5/10/2017.
 */

class JalurNarikAnggota extends RecyclerView.Adapter<JalurNarikAnggota.Pegangan>{
    TransaksiActivity c;
    ArrayList<Anggota2> d;
    RecyclerView e;


    public JalurNarikAnggota(TransaksiActivity transaksiActivity, ArrayList<Anggota2> data, RecyclerView recycler) {
        c = transaksiActivity;
        d = data;
        e = recycler;



    }
    View.OnClickListener listv = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            int posisi = e.getChildLayoutPosition(v);
//
//            final Dialog dialog = new Dialog(c);
//            dialog.setTitle("TRANSAKSI PEMBAYARAN");
//            dialog.setContentView(R.layout.list_item);
//
//            final Anggota2 b = d.get(posisi);
//
//            final TextView namas = (TextView) dialog.findViewById(R.id.textViewNama);
//            final TextView alamat = (TextView) dialog.findViewById(R.id.textViewlamat);
//            final TextView ids = (TextView) dialog.findViewById(R.id.textViewKTP);
//            final TextView hp = (TextView) dialog.findViewById(R.id.textViewHP);
//            final TextView modal = (TextView) dialog.findViewById(R.id.textViewModal);
//            final TextView hasil = (TextView) dialog.findViewById(R.id.textViewHasil);
//            final TextView periode = (TextView) dialog.findViewById(R.id.textViewHasil);
//
//            // ini untuk menambah tombol virtual TRANSAKSI
//            Button btn = (Button) dialog.findViewById(R.id.buttonKrm);
//            btn.setText("Lakukan Transaksi");
//
//            namas.setText(b.getNamaanggota());
//            alamat.setText(b.getLamatanggota());
//            ids.setText(b.getKtpanggota());
//            hp.setText(b.getHapeanggota());
//            modal.setText(b.getModalanggota());
//            hasil.setText(b.getHasilanggota());
//            periode.setText(b.getPeriodeanggota());
//
//            btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    DatabaseReference liat = FirebaseDatabase.getInstance().getReference("Peserta Pedagang");
//                    liat.child(b.getKeyanggota()).child("namaanggota").setValue(namas.getText().toString());
//                    liat.child(b.getKeyanggota()).child("alamatanggota").setValue(alamat.getText().toString());
//                    liat.child(b.getKeyanggota()).child("ktpanggota").setValue(ids.getText().toString());
//                    liat.child(b.getKeyanggota()).child("hpanggota").setValue(hp.getText().toString());
//                    liat.child(b.getKeyanggota()).child("modalanggota").setValue(modal.getText().toString());
//                    liat.child(b.getKeyanggota()).child("hasilanggota").setValue(hasil.getText().toString());
//                    liat.child(b.getKeyanggota()).child("periode").setValue(periode.getText().toString());
//
//                    Toast.makeText(c, "Transaksi SUKSES!", Toast.LENGTH_SHORT).show();
//                    dialog.dismiss();
//
//
//                }
//            });
//            dialog.show();




        }
    };

    @Override
    public JalurNarikAnggota.Pegangan onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater pengembang = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = pengembang.inflate(R.layout.list_item, parent,false);
        v.setOnClickListener(listv);

//        Pegangan b = new Pegangan(v);


        return new Pegangan(v);
    }

    @Override
    public void onBindViewHolder(JalurNarikAnggota.Pegangan holder, int position) {
        holder.txtnama.setText(d.get(position).getNamaanggota());
        holder.txtalamat.setText(d.get(position).getLamatanggota());
        holder.txtid.setText(d.get(position).getKtpanggota());
        holder.txthp.setText(d.get(position).getHapeanggota());
        holder.txtmodal.setText(d.get(position).getModalanggota());
        holder.txthasil.setText(d.get(position).getHasilanggota());
        holder.txtperiode.setText(d.get(position).getPeriodeanggota());



    }

    @Override
    public int getItemCount() {

        return d.size();
    }

    public class Pegangan extends RecyclerView.ViewHolder {
        TextView txtnama, txtalamat, txtid, txthp, txtmodal, txthasil, txtperiode;
        public Pegangan(View itemView) {
            super(itemView);
            txtnama =(TextView) itemView.findViewById(R.id.textView6);
            txtalamat = (TextView) itemView.findViewById(R.id.textView7);
            txtid =(TextView) itemView.findViewById(R.id.textView8);
            txthp = (TextView) itemView.findViewById(R.id.textView9);
            txtmodal=(TextView) itemView.findViewById(R.id.textView10);
            txthasil = (TextView) itemView.findViewById(R.id.textView11);
            txtperiode = (TextView) itemView.findViewById(R.id.textView12);


        }
    }
}
