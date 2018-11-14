package kelompok6.kamusjepang;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Pilihan_Bahasa extends AppCompatActivity {

   private Button buttonindo;
   private Button buttonjapan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilihan__bahasa);

        final MediaPlayer indo = MediaPlayer.create(this, R.raw.indo);
        final MediaPlayer japan = MediaPlayer.create(this, R.raw.japan);

        buttonindo = (Button) findViewById(R.id.buttonindo);
        buttonjapan = (Button) findViewById(R.id.buttonjapan);

        buttonindo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indo.start();
                openMainActivity();

            }
        });
        buttonjapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                japan.start();
                openMain2Activity();
            }
        });

        }

        public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openMain2Activity(){
        Intent intents = new Intent(this, Main2Activity.class);
        startActivity(intents);
    }


    }


