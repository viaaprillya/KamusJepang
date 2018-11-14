package kelompok6.kamusjepang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JapanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_japan);

        String indo = getIntent().getStringExtra("INDO");
        String japan = getIntent().getStringExtra("JAPAN");

        TextView wordText = (TextView) findViewById(R.id.wordtext);
        TextView japanText = (TextView) findViewById(R.id.japantext);

        wordText.setText(indo);
        japanText.setText(japan);
    }
}
