package com.pucpr.todolist.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pucpr.todolist.R;


public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder>{
    public interface OnItemClickListener { void onItemClick(View view, int position); }
    public interface ItemRemoveButtonOnClickListener { boolean onRemoveItemClick(View view, int position); }


    private static OnItemClickListener clickListener;
    private static ItemRemoveButtonOnClickListener itemRemoveButtonOnClickListener;

    public void setOnItemClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }
    public void setItemRemoveButtonOnClickListener(ItemRemoveButtonOnClickListener itemRemoveButtonOnClickListener) {
        this.itemRemoveButtonOnClickListener = itemRemoveButtonOnClickListener;
    }

    public static class  ViewHolder extends RecyclerView.ViewHolder {

        CheckBox checkBoxItem;
        TextView textViewItem;
        Button btnDeleteItem;

        public ViewHolder(View itemView) {
            super(itemView);
            checkBoxItem = itemView.findViewById(R.id.checkBoxItem);
            textViewItem = itemView.findViewById(R.id.textViewItem);
            btnDeleteItem = itemView.findViewById(R.id.btnDeleteTask);

           itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null) {
                        clickListener.onItemClick(view, getAdapterPosition());
                    }
                }
            });

           btnDeleteItem.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if (itemRemoveButtonOnClickListener != null) {
                       itemRemoveButtonOnClickListener.onRemoveItemClick(v, getAdapterPosition());
                   }
               }
           });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = inflater.inflate(
                R.layout.task,
                parent,
                false
        );
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task t = DataModel.getInstance().getTask(position);
        holder.textViewItem.setText(t.getName());
        holder.checkBoxItem.setChecked(t.isCompleted() > 0);
    }

    @Override
    public int getItemCount() {
        return DataModel.getInstance().getTaskListSize();
    }
}
