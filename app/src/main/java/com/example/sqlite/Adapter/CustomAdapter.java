package com.example.sqlite.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sqlite.Database.DatabaseHelper;
import com.example.sqlite.MainActivity;
import com.example.sqlite.Model.Student;
import com.example.sqlite.R;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
   private Context context;

   private List<Student> listStudent;
    TextView tv_id;
    TextView tvName;
    TextView tvPhone;
    TextView tvAddress;
    public CustomAdapter(Context context, List<Student> listStudent) {
        this.context = context;
        this.listStudent = listStudent;
    }

    @Override
    public int getCount() {
        return listStudent.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inf = ((MainActivity)context).getLayoutInflater();
        convertView=inf.inflate(R.layout.item_listview,null);
         tv_id =  convertView.findViewById(R.id.txt_idstudent);
         tvName=convertView.findViewById(R.id.txt_namestudent);
         tvPhone=convertView.findViewById(R.id.txt_phonestudent);
         tvAddress=convertView.findViewById(R.id.txt_addressstudent);
        ImageView imgdelete = convertView.findViewById(R.id.imgdelete);
        ImageView imgedit = convertView.findViewById(R.id.imgedit);

        imgdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Student student =listStudent.get(position);
                String id = student.getId();
                DatabaseHelper databaseHelper = new DatabaseHelper(context);
                databaseHelper.deleteStudent(id);
                ((MainActivity)context).setAdapter();
                Toast.makeText(context, "xoa roi: "+listStudent.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });

        imgedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student =listStudent.get(position);
                ((MainActivity)context).editText_Name.setText(student.getName());
                ((MainActivity)context).editText_Phone.setText(student.getPhone());
                ((MainActivity)context).editText_Address.setText(student.getAddress());
            }
        });

        Student student= listStudent.get(position);
        tv_id.setText(student.getId()+"");
        tvName.setText(student.getName());
        tvPhone.setText(student.getPhone());
        tvAddress.setText(student.getAddress());

        return convertView;
    }
}
