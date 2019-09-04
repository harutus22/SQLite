package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlite.Database.DBManager;
import com.example.sqlite.Dialog.MyRecyclerShowDialog;
import com.example.sqlite.Dialog.MyUpdateDialog;
import com.example.sqlite.Model.Student;
import com.example.sqlite.RecyclerView.MyRecycleViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName, editTextSurname, editTextMarks;

    private DBManager dbManager = new DBManager(this);
    private MyRecycleViewAdapter myRecycleViewAdapter;
    private MyRecyclerShowDialog dialog;
    private static int ids = 0;
    private MyRecycleViewAdapter.OnItemClicked onItemClicked = new MyRecycleViewAdapter.OnItemClicked() {
        @Override
        public void onItemClick(int position) {
            setDataToUpdate(position);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewsById();
        List<Student> students = new ArrayList<>();
        if(!dbManager.getAllInfo().isEmpty()) {
            students = dbManager.getAllInfo();
            ids = students.get(students.size() - 1).getId();
        }
        myRecycleViewAdapter = new MyRecycleViewAdapter(students);
        myRecycleViewAdapter.setOnItemClicked(onItemClicked);
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
            myRecycleViewAdapter.getStudents().add(new Student(++ids,
                    name, surname, mark));
            myRecycleViewAdapter.notifyDataSetChanged();
            emptyEditText();
        } else {
            Toast.makeText(getApplicationContext(), "All fields must be entered",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void deleteAll(View view){
        dbManager.deleteAll();
        myRecycleViewAdapter.getStudents().clear();
        myRecycleViewAdapter.notifyDataSetChanged();
        MainActivity.ids = 0;
    }

    public void showData(View view){
        dialog = new MyRecyclerShowDialog(this, myRecycleViewAdapter, dbManager);
        dialog.show();
    }

    private void setDataToUpdate(int position){
        MyUpdateDialog myUpdateDialog = new MyUpdateDialog(this,
                myRecycleViewAdapter, myRecycleViewAdapter.getStudents().get(position), dbManager);
        myUpdateDialog.show();
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
