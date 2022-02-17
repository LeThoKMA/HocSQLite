package com.example.hocquanlidanhba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lvCus;
    Button btnAdd, btnViewAll;
    EditText txtName, txtAge;
    Switch swichActive;
    List<Customer> customers;
    CustomerAdapter adapter;
    DataBaseHelper db=new DataBaseHelper(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        addControl();
        addEvent();


    }

    private void addEvent() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Customer customer = null;
                        try {
                            customer = new Customer(-1, txtName.getText().toString(), Integer.parseInt(txtAge.getText().toString()), swichActive.isChecked());
                        }
                        catch (Exception e)
                        {
                            customer=new Customer();
                        }

               boolean b= db.addOne(customer);
               Toast.makeText(MainActivity.this,"success "+b,Toast.LENGTH_LONG).show();
//               db.getAll();
                adapter=new CustomerAdapter(MainActivity.this, R.layout.item,db.getAll());
                lvCus.setAdapter(adapter);
           //    adapter.notifyDataSetChanged();
            }
        });
        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //DataBaseHelper dataBaseHelper=new DataBaseHelper(MainActivity.this);

               // customers=db.getAll();
                adapter=new CustomerAdapter(MainActivity.this, R.layout.item,db.getAll());
                lvCus.setAdapter(adapter);

                //adapter.notifyDataSetChanged();
            }
        });

    }


    private void addControl() {
      lvCus=findViewById(R.id.lvCustomer);
      btnAdd=findViewById(R.id.btnAdd);
      btnViewAll=findViewById(R.id.btnView);
      txtName=findViewById(R.id.txtName);
      txtAge=findViewById(R.id.txtAge);
      swichActive=findViewById(R.id.btnAct);

    }

}