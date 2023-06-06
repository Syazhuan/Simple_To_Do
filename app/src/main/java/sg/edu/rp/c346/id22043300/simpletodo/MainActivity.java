package sg.edu.rp.c346.id22043300.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spnOPTION;
    EditText etInfo;
    Button btnAdd;
    Button btnDel;
    Button btnClr;
    ListView lvTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spnOPTION = findViewById(R.id.spinner);
        etInfo = findViewById(R.id.editTextIndex);
        btnAdd = findViewById(R.id.btnADD);
        btnDel = findViewById(R.id.btnDEL);
        btnClr = findViewById(R.id.btnCLR);
        lvTask = findViewById(R.id.listViewTask);

        ArrayList<String> alTask = new ArrayList<String>();
        ArrayAdapter aaTask = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alTask);
        lvTask.setAdapter(aaTask);

        spnOPTION.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        // Add
                        etInfo.setHint(getResources().getString(R.string.type_task1));
                        btnAdd.setEnabled(true);
                        btnClr.setEnabled(true);
                        btnDel.setEnabled(false);
                        break;
                    case 1:
                        // Remove
                        etInfo.setHint(getResources().getString(R.string.type_task2));
                        btnAdd.setEnabled(false);
                        btnClr.setEnabled(true);
                        btnDel.setEnabled(true);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnClr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etInfo.setText("");
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Task = etInfo.getText().toString();
                alTask.add(Task);
                aaTask.notifyDataSetChanged();
                etInfo.setText("");
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = etInfo.getText().toString();

                if (input.isEmpty() && alTask.isEmpty()) {
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.error_1), Toast.LENGTH_SHORT).show();
                } else {
                    int pos = Integer.parseInt(input);

                    if (pos < 0 || pos >= alTask.size()) {
                        Toast.makeText(MainActivity.this, getResources().getString(R.string.error_2), Toast.LENGTH_SHORT).show();
                    } else {
                        alTask.remove(pos);
                        aaTask.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, getResources().getString(R.string.del_success), Toast.LENGTH_SHORT).show();
                    }
                }
                etInfo.setText("");
            }
        });
    }
}