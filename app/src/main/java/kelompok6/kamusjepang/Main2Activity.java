package kelompok6.kamusjepang;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import kelompok6.kamusjepang.adapters.Word_Adapter;
import kelompok6.kamusjepang.utils.DatabaseHelper2;
import kelompok6.kamusjepang.utils.DictionaryModel;

public class Main2Activity extends AppCompatActivity {

    private RecyclerView rWord;
    private Word_Adapter word_adapter;
    private List<DictionaryModel> dictionaryModelList;
    private DatabaseHelper2 DBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        rWord= (RecyclerView) findViewById(R.id.word);
        rWord.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL);
        rWord.addItemDecoration(itemDecoration);

        DBHelper=new DatabaseHelper2(this);

        File database = getApplicationContext().getDatabasePath(DatabaseHelper2.DBNAME);
        if(!database.exists()){
            DBHelper.getReadableDatabase();
            if(copyDatabase(this)){
                Toast.makeText(getApplicationContext(),"Copy Success",Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(getApplicationContext(),"Copy Failed", Toast.LENGTH_LONG).show();
                return;
            }
        }

        dictionaryModelList=DBHelper.getListWord("");

        word_adapter= new Word_Adapter();
        word_adapter.setData(dictionaryModelList);
        rWord.setAdapter(word_adapter);

        SearchView searchView;
        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchWord(newText);
                return false;
            }
        });


    }

    private void searchWord(String wordSearch) {
        dictionaryModelList.clear();
        dictionaryModelList=DBHelper.getListWord(wordSearch);
        word_adapter.setData(dictionaryModelList);
        rWord.setAdapter(word_adapter);

    }

    private boolean copyDatabase(Context context) {
        try{
            InputStream inputStream = context.getAssets().open(DatabaseHelper2.DBNAME);
            String outFileName = DatabaseHelper2.DBLOCATION + DatabaseHelper2.DBNAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[] buff = new byte[1024];
            int length;
            while ((length=inputStream.read(buff))>0){
                outputStream.write(buff,0,length);
            }
            outputStream.flush();
            outputStream.close();
            Log.w("Database","Copy Success");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }


}