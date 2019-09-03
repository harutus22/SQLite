package com.example.sqlite.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.example.sqlite.Database.DBManager;
import com.example.sqlite.Model.Student;
import com.example.sqlite.R;
import com.example.sqlite.RecyclerView.MyRecycleViewAdapter;

import androidx.annotation.NonNull;

public class MyUpdateDialog extends Dialog implements View.OnClickListener {
    private MyRecycleViewAdapter adapter;
    private DBManager dbManager;
    private Student student;
    private EditText name, surname, mark;

    public MyUpdateDialog(@NonNull Context context, MyRecycleViewAdapter adapter, Student student,
                          DBManager dbManager) {
        super(context);
        this.adapter = adapter;
        this.student = student;
        this.dbManager = dbManager;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.update_layout);

        findViewsById();

        setEditText();
    }

    private void findViewsById() {
        name = findViewById(R.id.updateName);
        surname = findViewById(R.id.updateSurname);
        mark = findViewById(R.id.updateMark);
        Button update = findViewById(R.id.startUpdate);
        Button cancel = findViewById(R.id.cancelUpdate);

        update.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    private void setEditText(){
        name.setText(student.getName());
        surname.setText(student.getSurname());
        mark.setText(String.valueOf(student.getMark()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.startUpdate:
                updateAdapterInfo();
                break;

            case R.id.cancelUpdate:
                dismiss();
                break;
        }
        dismiss();
    }

    private void updateAdapterInfo() {
        if(checkEditText()){
            String studentName = name.getText().toString();
            String studentSurname = surname.getText().toString();
            int studentMark = Integer.valueOf(mark.getText().toString());

            Student updateStudent = new Student(student.getId(),
                    studentName, studentSurname, studentMark);
            adapter.getStudents().set(student.getId() - 1, updateStudent);
            adapter.notifyDataSetChanged();
            dbManager.updateStudent(updateStudent);
        }
    }

    private boolean checkEditText(){
        return !name.getText().toString().isEmpty() &&
                !surname.getText().toString().isEmpty() &&
                !mark.getText().toString().isEmpty();
    }
}
