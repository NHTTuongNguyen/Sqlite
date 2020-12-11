package com.example.sqlite.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlite.MainActivity;
import com.example.sqlite.Model.Student;
import com.example.sqlite.R;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    Context context;
    List<Student> mangsinhvien;

    public StudentAdapter(Context context, List<Student> mangsinhvien) {
        this.context = context;
        this.mangsinhvien = mangsinhvien;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_test,parent,false);
        return new StudentViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = mangsinhvien.get(position);
        holder.txt_name.setText(student.getName());
        holder.txt_phone.setText(student.getPhone());
        holder.txt_address.setText(student.getAddress());
    }

    @Override
    public int getItemCount() {
        return mangsinhvien.size();
    }



    public class StudentViewHolder extends  RecyclerView.ViewHolder{
        public TextView txt_name,txt_phone,txt_address;
        public StudentViewHolder(@NonNull final View itemView) {
            super(itemView);
            txt_name = itemView.findViewById(R.id.txtnamesinhvientest);
            txt_phone = itemView.findViewById(R.id.txtphonesinhvientest);
            txt_address = itemView.findViewById(R.id.txtdiachisinhvientest);

//


        }
    }

}
