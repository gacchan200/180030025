package com.movies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Berita> dtBerita = new ArrayList<>();
    private RecyclerView rcCont;
    private BeritaAdapter beritaAdapter;
    private DatabaseHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcCont = findViewById(R.id.rcContBrt);
        dbHandler = new DatabaseHandler(this);
        viewBerita();
    }

    private void viewBerita(){
        dtBerita = dbHandler.fetchBerita();
        beritaAdapter = new BeritaAdapter(this, dtBerita);
        RecyclerView.LayoutManager layoutMgr = new LinearLayoutManager(MainActivity.this);
        rcCont.setLayoutManager(layoutMgr);
        rcCont.setAdapter(beritaAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menu_tambah){
            Intent formTambah = new Intent(getApplicationContext(), FormActivity.class);
            formTambah.putExtra("ACTION", "insert");
            startActivity(formTambah);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewBerita();
    }
}

