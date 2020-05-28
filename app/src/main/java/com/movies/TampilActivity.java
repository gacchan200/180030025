package com.movies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;

public class TampilActivity extends AppCompatActivity {

    private ImageView imgBerita;
    private TextView txJdl, txTgl, txCptn, txAuth, txIsi;
    private String linkBerita;
    private SimpleDateFormat tglFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil);

        imgBerita = findViewById(R.id.img_berita);
        txJdl = findViewById(R.id.tx_jdl);
        txTgl = findViewById(R.id.tx_tgl);
        txCptn = findViewById(R.id.tx_cptn);
        txAuth = findViewById(R.id.tx_auth);
        txIsi = findViewById(R.id.tx_isi);

        Intent getBerita = getIntent();
        txJdl.setText(getBerita.getStringExtra("JDL_BRT"));
        txTgl.setText(getBerita.getStringExtra("TGL_BRT"));
        txCptn.setText(getBerita.getStringExtra("CPTN_BRT"));
        txAuth.setText(getBerita.getStringExtra("AUTH_BRT"));
        txIsi.setText(getBerita.getStringExtra("ISI_BRT"));
        String imgLoc = getBerita.getStringExtra("GBR_BRT");

        try {
            File file = new File(imgLoc);
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            imgBerita.setImageBitmap(bitmap);
            imgBerita.setContentDescription(imgLoc);
        } catch(FileNotFoundException e){
            e.printStackTrace();
            Toast.makeText(this, "Terjadi kesalahan saat pengambilan gambar", Toast.LENGTH_SHORT).show();
        }

        linkBerita = getBerita.getStringExtra("LINK_BRT");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.view_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_share){
            Intent shareBerita = new Intent(Intent.ACTION_SEND);
            shareBerita.putExtra(Intent.EXTRA_SUBJECT, txJdl.getText().toString());
            shareBerita.putExtra(Intent.EXTRA_TEXT, linkBerita);
            shareBerita.setType("text/plain");
            startActivity(Intent.createChooser(shareBerita, "Bagikan Berita"));
        }
        return super.onOptionsItemSelected(item);
    }
}

