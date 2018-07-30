package com.example.user.cabbooking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class completed extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed);
        Button done=(Button)findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(completed.this,"Thankyou for rating us",Toast.LENGTH_LONG).show();
                Intent intent=new Intent();
                intent.setClass(completed.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
