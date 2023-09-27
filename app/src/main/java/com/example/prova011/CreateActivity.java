package com.example.prova011;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CreateActivity extends Activity {

    private EditText editText;
    private RadioGroup radioGroup;

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        dbHelper = new DatabaseHelper(this);

        editText = findViewById(R.id.edit_text);
        radioGroup = findViewById(R.id.radio_group);

        Button btnCancel = findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(v -> finish());

        Button btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(v -> {
            if (isTextValid() && isColorSelected()) {
                saveDataAndFinish();
            } else {
                if (!isTextValid()) {
                    showToast("Digite algum texto!");
                } else {
                    showToast("Obrigatório escolher uma cor!");
                }
            }
        });
    }

    private void saveDataAndFinish() {
        String insertedText = editText.getText().toString();
        int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        int selectedColor = Color.BLACK;

        if (checkedRadioButtonId == R.id.radio_red) {
            selectedColor = Color.RED;
        } else if (checkedRadioButtonId == R.id.radio_green) {
            selectedColor = Color.GREEN;
        } else if (checkedRadioButtonId == R.id.radio_blue) {
            selectedColor = Color.BLUE;
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("texto", insertedText);
        values.put("cor", selectedColor);
        long newRowId = db.insert("itens", null, values);

        if (newRowId != -1) {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("TEXTO_INSERIDO", insertedText);
            resultIntent.putExtra("COR_SELECIONADA", selectedColor);
            setResult(RESULT_OK, resultIntent);
            finish();
        }
    }

    private boolean isTextValid() {
        String insertedText = editText.getText().toString();
        return !insertedText.isEmpty();
    }

    private boolean isColorSelected() {
        int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        return checkedRadioButtonId != -1;
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

