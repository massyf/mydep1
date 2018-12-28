package com.teta_tm.newbook;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DbHelper extends SQLiteOpenHelper {
    private Context mycontext;
    private String DB_PATH;

    private static String DB_NAME = "storybook.db";
    public SQLiteDatabase myDataBase;


    public DbHelper(Context context) {
        super(context,DB_NAME,null,1);
        this.mycontext=context;
        if(android.os.Build.VERSION.SDK_INT >= 4.2){
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        } else {
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        }
        boolean dbexist = checkdatabase();
        if (dbexist) {
            opendatabase();
        } else {
            try {
                createdatabase();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void createdatabase() throws IOException {
        boolean dbexist = checkdatabase();
        if(!dbexist) {
            this.getReadableDatabase();
            try {
                copydatabase();
            } catch(IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkdatabase() {

        boolean checkdb = false;
        try {
            String myPath = DB_PATH + DB_NAME;
            File dbfile = new File(myPath);
            checkdb = dbfile.exists();
        } catch(SQLiteException e) {
            System.out.println("Database doesn't exist");
        }
        return checkdb;
    }

    private void copydatabase() throws IOException {
        //Open your local db as the input stream
        InputStream myinput = mycontext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outfilename = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myoutput = new FileOutputStream(outfilename);

        // transfer byte from inputfile to outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myinput.read(buffer))>0) {
            myoutput.write(buffer,0,length);
        }

        //Close the streams
        myoutput.flush();
        myoutput.close();
        myinput.close();
    }

    public void opendatabase() throws SQLException {
        //Open the database
        String mypath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(mypath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public synchronized void close() {
        if(myDataBase != null) {
            myDataBase.close();
        }
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public Integer shomaresh_field(String field){
        Cursor cursor = myDataBase.rawQuery("select "+field+" from tbl_book group by chapter order by ID ASC",null);
        int i = cursor.getCount();
        return i ;
    }

    public String show_field(String field, int row){
        Cursor cursor = myDataBase.rawQuery("select "+field+" from tbl_book group by chapter order by ID ASC",null);
        cursor.moveToPosition(row);
        String title = cursor.getString(0);
        return title ;
    }
    public int section_counter(String select,String where){
        Cursor cursor = myDataBase.rawQuery(
                "select "+select+" from tbl_book where " +
                        "chapter ='"+where+"'  group by "+select,null
        );
        int count = cursor.getCount();
        return count;
    }
    public String section_getter(String select,String where,int row){
        Cursor cursor = myDataBase.rawQuery(
                "select "+select+" from tbl_book where " +
                        "chapter ='"+where+"'  group by "+select+" order by ID ASC",null
        );
        cursor.moveToPosition(row);
        String section = cursor.getString(0);
        return section;
    }

    public String text_getter(String select,String where,int page){
        Cursor cursor = myDataBase.rawQuery(
                "select "+select+" from tbl_book where " +
                        "section ='"+where+"' and page='"+(page)+"'  group by "+select+" order by ID ASC",null
        );
        cursor.moveToFirst();
        String section = cursor.getString(0);
        return section;
    }

    public int page_getter(String select,String where,int row){
        Cursor cursor = myDataBase.rawQuery(
                "select "+select+" from tbl_book where " +
                        "section ='"+where+"'  group by "+select+" order by ID ASC",null
        );
        cursor.moveToPosition(row);
        int page = cursor.getInt(0);
        return page;
    }

    public int page_counter(String select,String where){
        Cursor cursor = myDataBase.rawQuery(
                "select "+select+" from tbl_book where " +
                        "section ='"+where+"'  group by "+select,null
        );
        int count = cursor.getCount();
        return count;
    }
}
