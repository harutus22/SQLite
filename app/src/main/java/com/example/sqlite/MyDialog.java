package com.example.sqlite;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyDialog extends Dialog {
    private Context context;
    private MyRecycleViewAdapter adapter;

    public MyDialog(@NonNull Context context, MyRecycleViewAdapter adapter) {
        super(context);
        this.context = context;
        this.adapter = adapter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_view);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        recyclerView.setAdapter(adapter);

    }
}
