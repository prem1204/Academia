package com.example.pearl.academia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Student extends Activity implements View.OnClickListener {

    private Button mca,extc,mech,textiles,civil,it;
    private Button notice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        mca = (Button)findViewById(R.id.mca);
        extc = (Button)findViewById(R.id.extc);
        mech  = (Button)findViewById(R.id.mech);
        textiles  = (Button)findViewById(R.id.textiles);
        civil  = (Button)findViewById(R.id.civil);
        it  = (Button)findViewById(R.id.it);

        notice  = (Button)findViewById(R.id.notice);

        mca.setOnClickListener(this);
        extc.setOnClickListener(this);
        mech.setOnClickListener(this);
        textiles.setOnClickListener(this);
        civil.setOnClickListener(this);
        it.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v==mca){
            Intent intent = new Intent(Student.this, Student_Mca.class);
            startActivity(intent);
        }else if(v==notice){
            Intent intent = new Intent(Student.this, Notice.class);
            startActivity(intent);
        }else {
            Toast.makeText(Student.this,"Only MCA is Activated for Trial",Toast.LENGTH_SHORT).show();
        }
    }
}
