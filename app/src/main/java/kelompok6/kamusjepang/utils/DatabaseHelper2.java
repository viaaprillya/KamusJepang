package kelompok6.kamusjepang.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper2 extends SQLiteOpenHelper {

    public static final String DBNAME = "kamus.sqlite";
    @SuppressLint("SdCardPath")
    public static final String DBLOCATION = "/data/data/kelompok6.kamusjepang/databases/";
    private Context nContext;
    private SQLiteDatabase nDatabase;


    public DatabaseHelper2(Context context){
        super(context,DBNAME,null,1);
        this.nContext=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void openDatabase(){
        String dbPath = nContext.getDatabasePath(DBNAME).getPath();
        if(nDatabase!=null && nDatabase.isOpen()){
            return;
        }
        nDatabase=SQLiteDatabase.openDatabase(dbPath,null,SQLiteDatabase.OPEN_READWRITE);
    }
    private void closeDatabase(){
        if(nDatabase != null){
            nDatabase.close();
        }
    }

    public List<DictionaryModel> getListWord(String wordSearch){
        DictionaryModel dictionaryModel;
        List<DictionaryModel> dictionaryModelList= new ArrayList<>();
        openDatabase();
        String[] args ={"%"+wordSearch+"%"};
        Cursor cursor;

        cursor=nDatabase.rawQuery(" Select * From tblKata where kataJPN Like ? ", args);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            dictionaryModel = new DictionaryModel(cursor.getString(0),cursor.getString(2),cursor.getString(1));
            dictionaryModelList.add(dictionaryModel);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return dictionaryModelList;

    }
}
