package com.example.pipi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etText;
    private SharedPreferences sPref;
    private static final String SAVED_TEXT = "saved_text";
    private TextView edt;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button saveb = findViewById(R.id.saveb);
        Button loadb = findViewById(R.id.loadb);
        etText = findViewById(R.id.etText);
        edt = findViewById(R.id.edt);
        loadText();
        saveb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveText();
            }
        });

        loadb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadText();
            }
        });
    }

    private void saveText() {
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(SAVED_TEXT, etText.getText().toString());
        ed.apply();
        Toast.makeText(this, "Text saved", Toast.LENGTH_SHORT).show();
    }

    private void loadText() {
        sPref = getPreferences(MODE_PRIVATE);
        String savedText = sPref.getString(SAVED_TEXT, "");
        etText.setText(savedText);
        edt.setText(savedText);
        Toast.makeText(this, "Text loaded", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveText();
    }
}
