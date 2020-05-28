package com.movies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "db_beritaku";
    private static final String TABLE1 = "tb_berita";
    private static final String TABLE1_ID =  "id_berita";
    private static final String TABLE1_JDL = "jdl_berita";
    private static final String TABLE1_TGL = "tgl_berita";
    private static final String TABLE1_GBR = "gbr_berita";
    private static final String TABLE1_CPTN = "cptn_berita";
    private static final String TABLE1_AUTH = "auth_berita";
    private static final String TABLE1_ISI = "isi_berita";
    private static final String TABLE1_LINK = "link_berita";
    private SimpleDateFormat tglFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.getDefault());
    private Context context;

    public DatabaseHandler(Context ctx){
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = ctx;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE1 = "CREATE TABLE "+TABLE1
                +" ("+TABLE1_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +TABLE1_JDL+" TEXT, "
                +TABLE1_TGL+" DATE, "
                +TABLE1_GBR+" TEXT, "
                +TABLE1_CPTN+" TEXT, "
                +TABLE1_AUTH+" TEXT, "
                +TABLE1_ISI+" TEXT, "
                +TABLE1_LINK+" TEXT);";

        db.execSQL(CREATE_TABLE1);
        initBerita(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE1;
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void addBerita(Berita dataBerita){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TABLE1_JDL, dataBerita.getJdlBerita());
        cv.put(TABLE1_TGL, tglFormat.format(dataBerita.getTglBerita()));
        cv.put(TABLE1_GBR, dataBerita.getGbrBerita());
        cv.put(TABLE1_CPTN, dataBerita.getCptnBerita());
        cv.put(TABLE1_AUTH, dataBerita.getAuthBerita());
        cv.put(TABLE1_ISI, dataBerita.getIsiBerita());
        cv.put(TABLE1_LINK,dataBerita.getLinkBerita());

        db.insert(TABLE1, null, cv);
        db.close();
    }

    public void addBerita(Berita dataBerita, SQLiteDatabase db){
        ContentValues cv = new ContentValues();

        cv.put(TABLE1_JDL, dataBerita.getJdlBerita());
        cv.put(TABLE1_TGL, tglFormat.format(dataBerita.getTglBerita()));
        cv.put(TABLE1_GBR, dataBerita.getGbrBerita());
        cv.put(TABLE1_CPTN, dataBerita.getCptnBerita());
        cv.put(TABLE1_AUTH, dataBerita.getAuthBerita());
        cv.put(TABLE1_ISI, dataBerita.getIsiBerita());
        cv.put(TABLE1_LINK,dataBerita.getLinkBerita());

        db.insert(TABLE1, null, cv);
    }

    public void updBerita(Berita dataBerita){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TABLE1_JDL, dataBerita.getJdlBerita());
        cv.put(TABLE1_TGL, tglFormat.format(dataBerita.getTglBerita()));
        cv.put(TABLE1_GBR, dataBerita.getGbrBerita());
        cv.put(TABLE1_CPTN, dataBerita.getCptnBerita());
        cv.put(TABLE1_AUTH, dataBerita.getAuthBerita());
        cv.put(TABLE1_ISI, dataBerita.getIsiBerita());
        cv.put(TABLE1_LINK,dataBerita.getLinkBerita());

        db.update(TABLE1, cv, TABLE1_ID + "=?", new String[]{String.valueOf(dataBerita.getIdBerita())});
        db.close();
    }

    public void delBerita(int idBerita){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE1, TABLE1_ID + "=?", new String[]{String.valueOf(idBerita)});
        db.close();
    }

    public ArrayList<Berita> fetchBerita(){
        ArrayList<Berita> dataBerita = new ArrayList<>();
        String query = "SELECT * FROM "+TABLE1;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cr = db.rawQuery(query, null);
        if(cr.moveToFirst()){
            do {
                Date tmpDt = new Date();
                try {
                    tmpDt = tglFormat.parse(cr.getString(2));
                } catch (ParseException err){
                    err.printStackTrace();
                }

                Berita tmpBrt = new Berita(
                        cr.getInt(0),
                        cr.getString(1),
                        tmpDt,
                        cr.getString(3),
                        cr.getString(4),
                        cr.getString(5),
                        cr.getString(6),
                        cr.getString(7)
                );

                dataBerita.add(tmpBrt);
            } while (cr.moveToNext());
        }
        return dataBerita;
    }

    private String storeInitImageFile(int id){
        String loc;
        Bitmap image = BitmapFactory.decodeResource(context.getResources(), id);
        loc = FormActivity.saveImageToInternalStorage(image, context);
        return loc;
    }

    private void initBerita(SQLiteDatabase db){
        int idBerita = 0;
        Date tmpDate = new Date();

        try {
            tmpDate = tglFormat.parse("February 14, 2018 (Indonesia)");
        } catch (ParseException e){
            e.printStackTrace();
        }

        Berita berita1 = new Berita(
                idBerita,
                "Black Panther",
                tmpDate,
                storeInitImageFile(R.drawable.gbr1),
                "Action, Superhero, Science Fiction, Adventure, Fantasy",
                "Ryan Coogler",
                "After the death of his father, T'Challa returns home to the African nation of Wakanda to take his rightful place as king. When a powerful enemy suddenly reappears, T'Challa's mettle as king -- and as Black Panther -- gets tested when he's drawn into a conflict that puts the fate of Wakanda and the entire world at risk. Faced with treachery and danger, the young king must rally his allies and release the full power of Black Panther to defeat his foes and secure the safety of his people.",
                "Wikipedia"
        );
        addBerita(berita1, db);
        idBerita++;

        try {
            tmpDate = tglFormat.parse("October 3, 2018 (Indonesia)");
        } catch (ParseException e){
            e.printStackTrace();
        }

        Berita berita2 = new Berita(
                idBerita,
                "Venom",
                tmpDate,
                storeInitImageFile(R.drawable.gbr2),
                "Action, Superhero, Science Fiction, Horror, Thriller, Adventure",
                "Ruben Fleischer",
                "Journalist Eddie Brock is trying to take down Carlton Drake, the notorious and brilliant founder of the Life Foundation. While investigating one of Drake's experiments, Eddie's body merges with the alien Venom -- leaving him with superhuman strength and power. Twisted, dark and fueled by rage, Venom tries to control the new and dangerous abilities that Eddie finds so intoxicating.",
                "Wikipedia"
        );
        addBerita(berita2, db);
        idBerita++;

        try {
            tmpDate = tglFormat.parse("May 31, 2017 (Indonesia)");
        } catch (ParseException e){
            e.printStackTrace();
        }

        Berita berita3 = new Berita(
                idBerita,
                "Wonder Woman",
                tmpDate,
                storeInitImageFile(R.drawable.gbr3),
                "Action, Superhero, Science Fiction, Adventure, Fantasy, War",
                "Patty Jenkins",
                "Before she was Wonder Woman (Gal Gadot), she was Diana, princess of the Amazons, trained to be an unconquerable warrior. Raised on a sheltered island paradise, Diana meets an American pilot (Chris Pine) who tells her about the massive conflict that's raging in the outside world. Convinced that she can stop the threat, Diana leaves her home for the first time. Fighting alongside men in a war to end all wars, she finally discovers her full powers and true destiny.",
                "Wikipedia"
        );
        addBerita(berita3, db);
        idBerita++;

        try {
            tmpDate = tglFormat.parse("December 21, 2018 (Indonesia)");
        } catch (ParseException e){
            e.printStackTrace();
        }

        Berita berita4 = new Berita(
                idBerita,
                "Aquaman",
                tmpDate,
                storeInitImageFile(R.drawable.gbr4),
                "Action, Superhero, Science Fiction, Adventure, Fantasy",
                "James Wan",
                "Once home to the most advanced civilization on Earth, the city of Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people -- and then the surface world. Standing in his way is Aquaman, Orm's half-human, half-Atlantean brother and true heir to the throne. With help from royal counselor Vulko, Aquaman must retrieve the legendary Trident of Atlan and embrace his destiny as protector of the deep",
                "Wikipedia"
        );
        addBerita(berita4, db);
        idBerita++;
    }
}

