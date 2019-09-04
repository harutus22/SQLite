package com.example.sqlite.RecyclerView;

import android.view.View;
import android.widget.TextView;

import com.example.sqlite.R;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecycleViewHolder extends RecyclerView.ViewHolder {
    private TextView id;
    private TextView name;
    private TextView surname;
    private TextView mark;
    private CardView cardView;
    private boolean isSelected = false;

    public MyRecycleViewHolder(@NonNull View itemView) {
        super(itemView);
        id = itemView.findViewById(R.id.viewId);
        name = itemView.findViewById(R.id.viewName);
        surname = itemView.findViewById(R.id.viewSurname);
        mark = itemView.findViewById(R.id.viewMark);
        cardView = itemView.findViewById(R.id.cardView);
        if(isSelected){
            cardView.setCardBackgroundColor(itemView.getContext()
                    .getResources().getColor(R.color.recycleViewClicked));

        } else {
            cardView.setCardBackgroundColor(itemView.getContext()
                    .getResources().getColor(R.color.recycleViewNotClicked));
        }
    }

    public TextView getId() {
        return id;
    }

    public TextView getName() {
        return name;
    }

    public TextView getSurname() {
        return surname;
    }

    public TextView getMark() {
        return mark;
    }

    public CardView getCardView() {
        return cardView;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isSelected() {
        return isSelected;
    }
}
