package com.example.sqlite.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sqlite.R;
import com.example.sqlite.Model.Student;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewHolder> {
    private List<Student> students = new ArrayList<>();

    private OnItemClicked onItemClicked;
    private boolean isLongClicked = false;
    private static int selectedItems = 0;

    private OnCountStart onCountStart;

    public MyRecycleViewAdapter(List<Student> students){
        this.students.addAll(students);
    }

    @NonNull
    @Override
    public MyRecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view,
                parent, false);
        return new MyRecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyRecycleViewHolder holder, final int position) {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isLongClicked) {
                    if (onItemClicked != null) {
                        onItemClicked.onItemClick(position);
                    }
                } else {
                    if (!holder.isSelected()) {
                        setViewSelected(v, holder, position);
                    } else {
                        setViewUnselected(v, holder, position);
                    }
                }
                if (selectedItems == 0 && isLongClicked) {
                    isLongClicked = false;
                }
            }
        };
        View.OnLongClickListener onLongClickListener = new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setViewSelected(v, holder, position);
                isLongClicked = true;
                return true;
            }
        };
        String id = "ID: " + students.get(position).getId();
        String name = "Name: " + students.get(position).getName();
        String surname = "Surname: " + students.get(position).getSurname();
        String mark = "Mark: " + students.get(position).getMark();

        holder.itemView.setOnClickListener(onClickListener);
        holder.itemView.setOnLongClickListener(onLongClickListener);
        holder.getId().setText(id);
        holder.getName().setText(name);
        holder.getSurname().setText(surname);
        holder.getMark().setText(mark);
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public interface OnItemClicked{
        void onItemClick(int position);
    }

    public void setOnItemClicked(OnItemClicked onItemClicked) {
        this.onItemClicked = onItemClicked;
    }

    public List<Student> getStudents() {
        return students;
    }

    private void setViewSelected(View v, MyRecycleViewHolder holder, int position){
        holder.getCardView().setCardBackgroundColor(v.getContext()
                .getResources().getColor(R.color.recycleViewClicked));
        holder.setSelected(true);
        ++selectedItems;
        onCountStart.startCount(selectedItems, true, students.get(position));
    }

    private void setViewUnselected(View v, MyRecycleViewHolder holder, int position){
        holder.getCardView().setCardBackgroundColor(v.getContext()
                .getResources().getColor(R.color.recycleViewNotClicked));
        holder.setSelected(false);
        --selectedItems;
        onCountStart.startCount(selectedItems, false, students.get(position));
    }

    public interface OnCountStart{
        void startCount(int count, boolean action, Student student);
    }

    public void setOnCountStart(OnCountStart onCountStart) {
        this.onCountStart = onCountStart;
    }

    public void setLongClicked(boolean longClicked) {
        isLongClicked = longClicked;
    }

    public boolean isLongClicked() {
        return isLongClicked;
    }

    public static void setSelectedItems(int selectedItems) {
        MyRecycleViewAdapter.selectedItems = selectedItems;
    }


}
