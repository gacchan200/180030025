package com.movies;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class BeritaAdapter extends RecyclerView.Adapter<BeritaAdapter.BeritaViewHolder> {

    private Context context;
    private ArrayList<Berita> dtBerita;
    private SimpleDateFormat tglFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.getDefault());

    public BeritaAdapter(Context context, ArrayList<Berita> dtBerita) {
        this.context = context;
        this.dtBerita = dtBerita;
    }

    @NonNull
    @Override
    public BeritaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_berita, parent, false);
        return new BeritaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BeritaViewHolder holder, int position) {
        Berita tmpBerita = dtBerita.get(position);
        holder.idBerita = tmpBerita.getIdBerita();
        holder.txJdlHead.setText(tmpBerita.getJdlBerita());
        holder.txHeadline.setText(tmpBerita.getIsiBerita());
        holder.tglBerita = tglFormat.format(tmpBerita.getTglBerita());
        holder.gbrBerita = tmpBerita.getGbrBerita();
        holder.cptnBerita = tmpBerita.getCptnBerita();
        holder.authBerita = tmpBerita.getAuthBerita();
        holder.linkBerita = tmpBerita.getLinkBerita();

        try {
            File file = new File(holder.gbrBerita);
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            holder.tmbBerita.setImageBitmap(bitmap);
            holder.tmbBerita.setContentDescription(holder.gbrBerita);
        } catch(FileNotFoundException e){
            e.printStackTrace();
            Toast.makeText(context, "Terjadi kesalahan saat pengambilan gambar", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return dtBerita.size();
    }

    public class BeritaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private ImageView tmbBerita;
        private TextView txJdlHead, txHeadline;
        private int idBerita;
        private String tglBerita, gbrBerita, cptnBerita, authBerita, linkBerita;

        public BeritaViewHolder(@NonNull View itemView) {
            super(itemView);

            tmbBerita = itemView.findViewById(R.id.tmb_berita);
            txJdlHead = itemView.findViewById(R.id.tx_jdl_head);
            txHeadline = itemView.findViewById(R.id.tx_headline);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent readBerita = new Intent(context,  TampilActivity.class);
            readBerita.putExtra("ID_BRT", idBerita);
            readBerita.putExtra("JDL_BRT", txJdlHead.getText().toString());
            readBerita.putExtra("TGL_BRT", tglBerita);
            readBerita.putExtra("GBR_BRT", gbrBerita);
            readBerita.putExtra("CPTN_BRT", cptnBerita);
            readBerita.putExtra("AUTH_BRT", authBerita);
            readBerita.putExtra("ISI_BRT",txHeadline.getText().toString());
            readBerita.putExtra("LINK_BRT", linkBerita);
            context.startActivity(readBerita);
        }

        @Override
        public boolean onLongClick(View v) {
            Intent formEdit = new Intent(context, FormActivity.class);
            formEdit.putExtra("ACTION", "update");
            formEdit.putExtra("ID_BRT", idBerita);
            formEdit.putExtra("JDL_BRT", txJdlHead.getText().toString());
            formEdit.putExtra("TGL_BRT", tglBerita);
            formEdit.putExtra("GBR_BRT", gbrBerita);
            formEdit.putExtra("CPTN_BRT", cptnBerita);
            formEdit.putExtra("AUTH_BRT", authBerita);
            formEdit.putExtra("ISI_BRT",txHeadline.getText().toString());
            formEdit.putExtra("LINK_BRT", linkBerita);
            context.startActivity(formEdit);
            return true;
        }
    }
}

