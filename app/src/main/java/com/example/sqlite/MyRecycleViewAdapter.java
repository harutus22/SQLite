package com.example.sqlite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewHolder> {
    private List<Student> students = new ArrayList<>();
    private OnItemClicked onItemClicked;

    private View.OnClickListener onClickListener;

    public MyRecycleViewAdapter(List<Student> students){
        this.students.addAll(students);
    }

    @NonNull
    @Override
    public MyRecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view,
                parent, false);
        MyRecycleViewHolder viewHolder = new MyRecycleViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecycleViewHolder holder, final int position) {
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClicked != null){
                    onItemClicked.onItemClick(position);
                }
            }
        };
        String id = "ID: " + students.get(position).getId();
        String name = "Name: " + students.get(position).getName();
        String surname = "Surname: " + students.get(position).getSurname();
        String mark = "Mark: " + students.get(position).getMark();

        holder.itemView.setOnClickListener(onClickListener);
        holder.getId().setText(id);
        holder.getName().setText(name);
        holder.getSurname().setText(surname);
        holder.getMark().setText(mark);
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    interface OnItemClicked{
        void onItemClick(int position);
    }

    public void setOnItemClicked(OnItemClicked onItemClicked) {
        this.onItemClicked = onItemClicked;
    }
}
