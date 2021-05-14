package com.example.mytodoroomdb.Student;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytodoroomdb.MainActivity;
import com.example.mytodoroomdb.R;
import java.util.List;

public class StudentRecycleAdapter extends RecyclerView.Adapter<StudentRecycleAdapter.StudentViewHolder> {
    Context mContext;
    List<StudentClass> studentList;

    public StudentRecycleAdapter(Context context, List<StudentClass> stList){
        this.mContext = context;
        this.studentList = stList;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.student,parent,false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {

        //set values
        StudentClass stClass = studentList.get(position);
        holder.stname.setText(studentList.get(position).getStudentName());
        holder.stmobile.setText(studentList.get(position).getMobile());
        holder.stmarks.setText(studentList.get(position).getStudentMaks());

    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    class StudentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView stname, stmobile, stmarks;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            //initialise views
            stname = itemView.findViewById(R.id.stname);
            stmobile = itemView.findViewById(R.id.stmobile);
            stmarks = itemView.findViewById(R.id.stmarks);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            //click events

            Intent intent = new Intent(mContext, MainActivity.class);
            mContext.startActivity(intent);
        }
    }
}
