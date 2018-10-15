package com.example.vishal_pc.solartracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class DisplayValues extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_values);
        TextView tv=(TextView)findViewById(R.id.textView2);
        DataBaseHandler db=new DataBaseHandler(getApplicationContext());
        ArrayList<Values> ar= db.getValues();
        int i;
        String st="";

        for(i=0;i<ar.size();i++)
        {
            String s1= ar.get(i).getS1();
            String s2= ar.get(i).getS2();
            String s3= ar.get(i).getS3();
            String s4= ar.get(i).getS4();
            String m1= ar.get(i).getM1();
            String m2= ar.get(i).getM2();
            String date= ar.get(i).getRecordDate();
            String solar= ar.get(i).getSolar();
            int mid=ar.get(i).getId();

            st=st+mid+'-'+s1+','+s2+','+s3+','+s4+','+m1+','+m2+','+solar+','+date+"\r\n";
        }
        tv.setText(st);
    }

    public void home(View view)
    {
        Intent intent=new Intent(this,DeviceListActivity.class);
        startActivity(intent);
    }

}
