package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Student> students = new ArrayList<>();
    private DBManager dbManager = new DBManager(this);
    private MyRecycleViewAdapter myRecycleViewAdapter;
    private MyDialog dialog;
    private MyRecycleViewAdapter.OnItemClicked onItemClicked = new MyRecycleViewAdapter.OnItemClicked() {
        @Override
        public void onItemClick(int position) {

        }
    };

    private EditText editTextName, editTextSurname, editTextMarks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewsById();
    }

    private void findViewsById() {
        editTextName = findViewById(R.id.editTextName);
        editTextSurname = findViewById(R.id.editTextSurname);
        editTextMarks = findViewById(R.id.editTextMarks);
    }

    public void saveData(View view){
        if(checkEditTextsIsEmpty()) {
            String name = editTextName.getText().toString();
            String surname = editTextSurname.getText().toString();
            int mark = Integer.valueOf(editTextMarks.getText().toString());
            dbManager.createStudentInfo(name, surname, mark);
            emptyEditText();
        } else {
            Toast.makeText(getApplicationContext(), "All fields must be entered",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void showData(View view){
        students.addAll(dbManager.getAllInfo());
//        RecyclerView recyclerView = findViewsById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myRecycleViewAdapter = new MyRecycleViewAdapter(students);
        myRecycleViewAdapter.setOnItemClicked(onItemClicked);
        dialog = new MyDialog(this, myRecycleViewAdapter);
        dialog.show();

//        recyclerView.setAdapter(myRecycleViewAdapter);
    }

    private boolean checkEditTextsIsEmpty(){
        return !editTextName.getText().toString().isEmpty() &&
                !editTextSurname.getText().toString().isEmpty() &&
                !editTextMarks.getText().toString().isEmpty();
    }

    private void emptyEditText(){
        editTextName.setText("");
        editTextSurname.setText("");
        editTextMarks.setText("");
    }
}
