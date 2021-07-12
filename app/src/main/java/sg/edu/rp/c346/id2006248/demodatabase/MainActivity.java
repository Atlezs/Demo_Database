package sg.edu.rp.c346.id2006248.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert;
    Button btnGetTasks;
    TextView tvResults;
    ListView lv;
    EditText etTask, etDate;

    boolean asc = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnGetTasks = findViewById(R.id.btnGetTask);
        tvResults = findViewById(R.id.tvResults);
        lv = findViewById(R.id.lv);
        etTask = findViewById(R.id.editTextTask);
        etDate = findViewById(R.id.editTextDate);



        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strTask = etTask.getText().toString();
                String strDate = etDate.getText().toString();


                DBHelper db = new DBHelper(MainActivity.this);

                db.insertTask(strTask, strDate);
            }
        });

        btnGetTasks.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                ArrayList<String> data = db.getTaskContent();
                db.close();

                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database Content", i +". "+data.get(i));
                    txt += i + ". " + data.get(i) + "\n";
                }
                tvResults.setText(txt);

                DBHelper db2 = new DBHelper(MainActivity.this);

                ArrayList<Task> al;

                if(asc) {
                    al = db2.getTasks();
                    asc = false;
                }
                else{
                    al = db2.getTasks2();
                    asc = true;
                }
                db2.close();

                ArrayAdapter<Task> aa = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, al);
                lv.setAdapter(aa);
            }
        });

    }

}



