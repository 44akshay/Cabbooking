package com.example.user.cabbooking;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;

public class BookingActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener  {
    Button btn;
    EditText txtname,txt1,txt2,txt3,txt4,txt5;
    TextView txtman,jobband;
    RadioGroup radio;
    RadioButton r1,r2;
    String type;
    FirebaseDatabase f;
    DatabaseReference d;
    String uniqueid,empid;
    EditText fromdate,todate;
    private String current = "";
    private String ddmmyyyy = "DDMMYYYY";
    private Calendar cal = Calendar.getInstance();
    private String curren = "";
    private Calendar cale = Calendar.getInstance();
    private EditText input;



    int a;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        getSupportActionBar().setTitle("Raysil Cab booking page");


        btn=(Button)findViewById(R.id.btn);
        txtname=(EditText) findViewById(R.id.txtname);
        txt1=(EditText) findViewById(R.id.txt1);
        txt2=(EditText) findViewById(R.id.txt2);
        txt3=(EditText) findViewById(R.id.txt3);
        txt4=(EditText) findViewById(R.id.txt4);
        txt5=(EditText) findViewById(R.id.txt5);
        radio=(RadioGroup)findViewById(R.id.radio);
        r1=(RadioButton)findViewById(R.id.r1);
        r2=(RadioButton)findViewById(R.id.r2);
        User user = SharedPrefManager.getInstance(this).getUser();
        jobband=(TextView)findViewById(R.id.jobband);
        txtman=(TextView)findViewById(R.id.txtman);
        fromdate=(EditText) findViewById(R.id.fromdate);
        todate=(EditText) findViewById(R.id.todate);

        TextWatcher tw=new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals(current)) {
                    String clean = s.toString().replaceAll("[^\\d.]|\\.", "");
                    String cleanC = current.replaceAll("[^\\d.]|\\.", "");

                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }
                    //Fix for pressing delete next to a forward slash
                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 8){
                        clean = clean + ddmmyyyy.substring(clean.length());
                    }else{
                        //This part makes sure that when we finish entering numbers
                        //the date is correct, fixing it otherwise
                        int day  = Integer.parseInt(clean.substring(0,2));
                        int mon  = Integer.parseInt(clean.substring(2,4));
                        int year = Integer.parseInt(clean.substring(4,8));

                        mon = mon < 1 ? 1 : mon > 12 ? 12 : mon;
                        cal.set(Calendar.MONTH, mon-1);
                        year = (year<1900)?1900:(year>2100)?2100:year;
                        cal.set(Calendar.YEAR, year);
                        // ^ first set year for the line below to work correctly
                        //with leap years - otherwise, date e.g. 29/02/2012
                        //would be automatically corrected to 28/02/2012

                        day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                        clean = String.format("%02d%02d%02d",day, mon, year);
                    }

                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));

                    sel = sel < 0 ? 0 : sel;
                    current = clean;
                    fromdate.setText(current);
                    fromdate.setSelection(sel < current.length() ? sel : current.length());

                }

            }
        };
        fromdate.addTextChangedListener(tw);

        TextWatcher tt=new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals(curren)) {
                    String clean = s.toString().replaceAll("[^\\d.]|\\.", "");
                    String cleanC = curren.replaceAll("[^\\d.]|\\.", "");

                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }
                    //Fix for pressing delete next to a forward slash
                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 8) {
                        clean = clean + ddmmyyyy.substring(clean.length());
                    } else {
                        //This part makes sure that when we finish entering numbers
                        //the date is correct, fixing it otherwise
                        int day = Integer.parseInt(clean.substring(0, 2));
                        int mon = Integer.parseInt(clean.substring(2, 4));
                        int year = Integer.parseInt(clean.substring(4, 8));

                        mon = mon < 1 ? 1 : mon > 12 ? 12 : mon;
                        cale.set(Calendar.MONTH, mon - 1);
                        year = (year < 1900) ? 1900 : (year > 2100) ? 2100 : year;
                        cale.set(Calendar.YEAR, year);
                        // ^ first set year for the line below to work correctly
                        //with leap years - otherwise, date e.g. 29/02/2012
                        //would be automatically corrected to 28/02/2012

                        day = (day > cale.getActualMaximum(Calendar.DATE)) ? cale.getActualMaximum(Calendar.DATE) : day;
                        clean = String.format("%02d%02d%02d", day, mon, year);
                    }

                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));

                    sel = sel < 0 ? 0 : sel;
                    curren = clean;
                    todate.setText(curren);
                    todate.setSelection(sel < curren.length() ? sel : curren.length());

                }
            }
        };
        todate.addTextChangedListener(tt);

        txtman.setText(user.getDesignation());
        txtname.setText(user.getEmp_name()+"-"+user.getEmp_id());
        txt5.setText(user.getPh_no());
        jobband.setText(user.getJobband());
        empid=user.getEmp_id();
        radio.setOnCheckedChangeListener(BookingActivity.this);


        f=FirebaseDatabase.getInstance();
        d=f.getReference();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name =txtname.getText().toString();
                String from =txt1.getText().toString();
                String to =txt2.getText().toString();
                String pick =txt3.getText().toString();
                String no =txt4.getText().toString();
                String phn =txt5.getText().toString();
                String designation= txtman.getText().toString();
                String fromdat =fromdate.getText().toString();
                String todat=todate.getText().toString();
                String jobban=jobband.getText().toString();



               sendtodb(name,from,to,pick,no,phn,type,designation,fromdat,todat,jobban,empid);
               Toast.makeText(BookingActivity.this,"You have succesfuly booked your cab",Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId)
        {
            case R.id.r1:
                type="Official";
                break;
            case R.id.r2:
                type="Personal";
                break;
        }

    }
   private void sendtodb(String name,String from,String to,String pick,String no,String  phn,String type,String designation,
                         String fromdate,String todate,String jobband,String empid )
    {

         String  uniqueid = d.push().getKey();


             booking b = new booking(name, from, to, pick, no, phn, type,designation,fromdate,todate,jobband,empid);
             d.child("employee").child(uniqueid).setValue(b);
             Intent intent=new Intent();
             intent.setClass(BookingActivity.this,congratulation.class);
             startActivity(intent);

        }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
       
    }
}

