package com.example.user.cabbooking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    static String s;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Raysil Cab booking Menu");
        TextView booktxt=(TextView)findViewById(R.id.booktxt);
        booktxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent();
                intent.setClass(MainActivity.this,BookingActivity.class);
                startActivity(intent);
                final TextView recieved=(TextView)findViewById(R.id.recieved);
                recieved.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent =new Intent();
                        intent.setClass(MainActivity.this,recieved.class);
                        startActivity(intent);

                    }
                });


            }
        });
        final TextView recieved=(TextView)findViewById(R.id.recieved);
        recieved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,recieved.class);
                startActivity(intent);

            }
        });
        final TextView contact=(TextView)findViewById(R.id.contact);
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,contactus.class);
                startActivity(intent);
            }
        });




    }

    @Override
    protected void onStart() {
        super.onStart();
        if(TextUtils.isEmpty(s)) {
            s="hello";
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, login.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent= new Intent();
        intent.setClass(MainActivity.this,login.class);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id== R.id.logout)
        {
            Intent inent =new Intent();
            inent.setClass(MainActivity.this,login.class);
            startActivity(inent);
        }
        return super.onOptionsItemSelected(item);
    }
}
