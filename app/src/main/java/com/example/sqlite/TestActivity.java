package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sqlite.Adapter.CustomAdapter;
import com.example.sqlite.Adapter.StudentAdapter;
import com.example.sqlite.Database.DatabaseHelper;
import com.example.sqlite.Model.Student;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    DatabaseHelper databaseHelper;
    TextView textView;
    EditText editText_Name,editText_Phone,editText_Address;
    Button button_Insert;
    LinearLayoutManager linearLayoutManager;
    StudentAdapter studentAdapter;
    ListView lvStudent;

    List<Student> studentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        databaseHelper = new DatabaseHelper(this);
        recyclerView = findViewById(R.id.recyclerView_Test);
        setAdapter();



    }
    private void setAdapter() {
        studentList = databaseHelper.getAllStudents();
        studentAdapter = new StudentAdapter(this, studentList);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(studentAdapter);
        studentAdapter.notifyDataSetChanged();


    }

}