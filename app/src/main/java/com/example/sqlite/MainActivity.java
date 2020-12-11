package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sqlite.Adapter.CustomAdapter;
import com.example.sqlite.Adapter.StudentAdapter;
import com.example.sqlite.Database.DatabaseHelper;
import com.example.sqlite.Model.Product;
import com.example.sqlite.Model.Student;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    DatabaseHelper databaseHelper;
    TextView textView;
    RecyclerView recyclerView;
    public EditText editText_Name,editText_Phone,editText_Address;
    Button button_Insert,btnUpdate;
    LinearLayoutManager linearLayoutManager;
    StudentAdapter studentAdapter;
    ListView lvStudent;
    CustomAdapter customAdapter;
    List<Student> studentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        recyclerView = findViewById(R.id.recyclerView_Student);
        editText_Address = findViewById(R.id.edt_Address);
        editText_Name = findViewById(R.id.edt_Name);
        editText_Phone = findViewById(R.id.edt_Phone);
        lvStudent = findViewById(R.id.lv_student);
        databaseHelper = new DatabaseHelper(this);
        //init

        setAdapter();

        btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = editText_Name.getText().toString();
                String phone = editText_Phone.getText().toString();
                String address = editText_Address.getText().toString();
                Student student= new Student(name,phone,address);
                databaseHelper.updateStudent(student);
                setAdapter();
            }
        });

        button_Insert = findViewById(R.id.btn_insertStudent);


//
//        studentAdapter = new StudentAdapter(this,studentArrayList);
//        linearLayoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setAdapter(studentAdapter);
//        studentAdapter.notifyDataSetChanged();


        button_Insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertDataStudent();
            }
        });
        Button button = findViewById(R.id.btnnext);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,TestActivity.class);
                startActivity(intent);
            }
        });
    }
    private void insertDataStudent() {

        String name = editText_Name.getText().toString();
        String phone = editText_Phone.getText().toString();
        String address = editText_Address.getText().toString();


        Student student= new Student(name,phone,address);
        databaseHelper.insertStudent(student);
        editText_Name.setText(student.name);
        editText_Phone.setText(student.phone);
        editText_Address.setText(student.address);
        ///
//        studentList.clear();

        studentList.addAll(databaseHelper.getAllStudents());
        setAdapter();





    }

    public void setAdapter() {
        studentList = databaseHelper.getAllStudents();
        customAdapter = new CustomAdapter(this,studentList);
        customAdapter.notifyDataSetChanged();
        lvStudent.setAdapter(customAdapter);
//        lvStudent.setSelection(customAdapter.getCount()-1);


    }




    @Override
    protected void onDestroy() {
        databaseHelper.close();
        super.onDestroy();
    }

}
