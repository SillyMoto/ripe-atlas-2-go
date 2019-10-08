package de.beuth.master.ripeatlas2go;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        TextView view = findViewById(R.id.textView1);
        view.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
        view = findViewById(R.id.textView2);
        view.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);

    }
}
