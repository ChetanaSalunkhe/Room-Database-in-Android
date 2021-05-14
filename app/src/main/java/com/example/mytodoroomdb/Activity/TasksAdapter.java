package com.example.mytodoroomdb.Activity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mytodoroomdb.R;
import com.example.mytodoroomdb.Task;
import java.util.List;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TaskViewHolder> {
    public Context mContext;
    List<Task> taskList;

    public TasksAdapter(Context context,List<Task> listTask){
        this.mContext=context;
        this.taskList=listTask;
    }


    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_tasks,parent,false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TasksAdapter.TaskViewHolder holder, int position) {
        Task t = taskList.get(position);
        holder.textViewDesc.setText(taskList.get(position).getDesc());
        holder.textViewTask.setText(taskList.get(position).getTask());
        holder.textViewFinishBy.setText(taskList.get(position).getFinishBy());

        if(t.isFinished()){
            holder.textViewStatus.setText("Completed");
        }else {
            holder.textViewStatus.setText("Not Completed");
        }
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewStatus,textViewTask,textViewDesc,textViewFinishBy;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewDesc = itemView.findViewById(R.id.textViewDesc);
            textViewStatus = itemView.findViewById(R.id.textViewStatus);
            textViewTask = itemView.findViewById(R.id.textViewTask);
            textViewFinishBy = itemView.findViewById(R.id.textViewFinishBy);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Task task = taskList.get(getAdapterPosition());

            Intent intent = new Intent(mContext,UpdateTaskActivity.class);
            intent.putExtra("task",task);
            mContext.startActivity(intent);
        }
    }
}
