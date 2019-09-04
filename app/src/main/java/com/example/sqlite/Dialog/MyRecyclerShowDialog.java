package com.example.sqlite.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.sqlite.Database.DBManager;
import com.example.sqlite.Model.Student;
import com.example.sqlite.R;
import com.example.sqlite.RecyclerView.MyRecycleViewAdapter;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerShowDialog extends Dialog {
    private Context context;
    private DBManager dbManager;
    private MyRecycleViewAdapter adapter;
    private TextView itemsCount;
    private ImageButton deleteBtn;
    private ArrayList<Student> items = new ArrayList<>();

    private MyRecycleViewAdapter.OnCountStart onCountStart = new MyRecycleViewAdapter.OnCountStart() {
        @Override
        public void startCount(int count, boolean action, Student student) {
            setDeleteView(count, action, student);
        }
    };


    public MyRecyclerShowDialog(@NonNull Context context, MyRecycleViewAdapter adapter, DBManager dbManager) {
        super(context);
        this.context = context;
        this.adapter = adapter;
        this.dbManager = dbManager;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_view);
        itemsCount = findViewById(R.id.dialogItemsSelectedCount);
        deleteBtn = findViewById(R.id.dialogItemsDelete);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter.setOnCountStart(onCountStart);

        recyclerView.setAdapter(adapter);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteSelected();
            }
        });
    }

    private void deleteSelected(){
        for(int i = 0; i < items.size(); ++i){
            adapter.getStudents().remove(items.get(i));
        }
        adapter.notifyDataSetChanged();
        MyRecycleViewAdapter.setSelectedItems(0);
        setDeleteView(0, true, null);
    }

    private void setDeleteView(int count, boolean action, Student student){
        if(count != 0){
            if(itemsCount.getVisibility() == View.INVISIBLE ||
                    deleteBtn.getVisibility() == View.INVISIBLE ){
                itemsCount.setVisibility(View.VISIBLE);
                deleteBtn.setVisibility(View.VISIBLE);
            }
            if(action) {
                items.add(student);
                itemsCount.setText(String.valueOf(items.size()));
            } else {
                items.remove(student);
                itemsCount.setText(String.valueOf(items.size()));
            }
        } else {
            if(itemsCount.getVisibility() == View.VISIBLE &&
                    deleteBtn.getVisibility() == View.VISIBLE ){
                itemsCount.setVisibility(View.INVISIBLE);
                deleteBtn.setVisibility(View.INVISIBLE);
                dbManager.deleteStudent(items);
                items.clear();
                adapter.setLongClicked(false);
            }
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if(adapter.isLongClicked()){
            adapter.setLongClicked(false);
            MyRecycleViewAdapter.setSelectedItems(0);
            if (!items.isEmpty()) {
                items.clear();
            }
        }
    }
}
