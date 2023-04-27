package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.myapplication.dal.SQLiteHelper;
import com.example.myapplication.model.Item;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText eName, eDate, eCoutries;
    private RadioGroup rgOption1;
    private RadioButton rb1, rb2;
    private CheckBox cb1, cb2, cb3;
    private Button btUpdate, btCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
        btCancel.setOnClickListener(this);
        btUpdate.setOnClickListener(this);
        eDate.setOnClickListener(this);
    }

    private void initView(){
        eName = findViewById(R.id.eName);
        eCoutries = findViewById(R.id.eCountries);
        eDate = findViewById(R.id.eDate);
        btCancel = findViewById(R.id.btCancel);
        btUpdate = findViewById(R.id.btUpdate);
        rgOption1 = findViewById(R.id.radio_group1);
        rb1 = findViewById(R.id.option1);
        rb2 = findViewById(R.id.option2);
        cb1 = findViewById(R.id.checkbox1);
        cb2 = findViewById(R.id.checkbox2);
        cb3 = findViewById(R.id.checkbox3);
    }

    @Override
    public void onClick(View view) {
        if(view==eDate ){
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    String date = "";
                    if(month > 8 ){
                        date = dayOfMonth + "/" + (month+1) + "/" + year;
                    }else{
                        date = dayOfMonth + "/0" + (month+1) + "/" + year;

                    }
                    eDate.setText(date);
                }
            }, year, month, day);
            dialog.show();
        }
        if(view == btCancel){
            finish();
        }
        if(view == btUpdate){
            String name = eName.getText().toString();
            int c = Integer.parseInt(eCoutries.getText().toString());
            String d = eDate.getText().toString();
            String s = "";
            if (rb1.isChecked()){
                s = rb1.getText().toString();
            }else if (rb2.isChecked()){
                s = rb2.getText().toString();
            }
            StringBuilder sb = new StringBuilder();
            if (cb1.isChecked()){
                sb.append(cb1.getText().toString() + "; ");
            }
            if (cb2.isChecked()){
                sb.append(cb2.getText().toString() + "; ");
            }
            if (cb3.isChecked()){
                sb.append(cb3.getText().toString() + "; ");
            }
            String ct = sb.toString();
            if(!name.isEmpty()){
                Item i = new Item(name, ct,d,s,c);
                SQLiteHelper db = new SQLiteHelper(this);
                db.insertItem(i);
                finish();
            }
        }
    }
}