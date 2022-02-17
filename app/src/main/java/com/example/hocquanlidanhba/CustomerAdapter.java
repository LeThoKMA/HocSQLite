package com.example.hocquanlidanhba;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomerAdapter extends ArrayAdapter<Customer> {

    Activity context;
    int resource;
    List<Customer> objects;
    public CustomerAdapter(@NonNull Activity context, int resource , @NonNull List<Customer> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=this.context.getLayoutInflater();
        View row=inflater.inflate(this.resource,null);
        EditText txtInf=(EditText) row.findViewById(R.id.txtInf);
        ImageButton btnDel=row.findViewById(R.id.imageButton);
        Customer cus=this.objects.get(position);
        txtInf.setText(cus.toString());
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper db=new DataBaseHelper(context);
                db.deleteOne(cus);
               CustomerAdapter.this.remove(cus);
            }
        });


        return row;
    }

    private void xuLixoa(Customer cus) {


    }
}
